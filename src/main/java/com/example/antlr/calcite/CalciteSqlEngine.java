package com.example.antlr.calcite;

import com.google.common.base.Preconditions;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.config.Lex;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.rel.externalize.RelWriterImpl;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeField;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.dialect.*;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorUtil;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;
import org.apache.calcite.sql.util.SqlVisitor;
import org.apache.calcite.tools.*;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.sql.validate.SqlValidatorWithHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Apache Calcite SQL 引擎
 * <p>
 * 基于 Calcite 提供以下能力：
 * <ul>
 *     <li>SQL 解析（Parse） — 将 SQL 字符串解析为 AST（SqlNode）</li>
 *     <li>SQL 验证（Validate） — 基于 schema 进行类型检查和语义验证</li>
 *     <li>方言转换（Dialect Conversion） — 在 MySQL / PostgreSQL / SparkSQL / ANSI 等方言间转换</li>
 *     <li>SQL 格式化（Pretty Print） — 美化 SQL 输出</li>
 *     <li>关系代数转换（Rel Algebra） — 转为 RelNode 逻辑计划</li>
 *     <li>AST 遍历分析（AST Analysis） — 提取表名、列名、函数等元数据</li>
 * </ul>
 */
public class CalciteSqlEngine {

    private static final Logger log = LoggerFactory.getLogger(CalciteSqlEngine.class);

    // ================================================================
    // Calcite 方言枚举
    // ================================================================

    /**
     * Calcite 支持的 SQL 方言
     */
    public enum CalciteDialect {
        ANSI("ANSI SQL", SqlDialect.DatabaseProduct.UNKNOWN),
        MYSQL("MySQL", SqlDialect.DatabaseProduct.MYSQL),
        POSTGRESQL("PostgreSQL", SqlDialect.DatabaseProduct.POSTGRESQL),
        ORACLE("Oracle", SqlDialect.DatabaseProduct.ORACLE),
        MSSQL("MS SQL Server", SqlDialect.DatabaseProduct.MSSQL),
        HIVE("Hive", SqlDialect.DatabaseProduct.HIVE),
        SPARK("Spark", SqlDialect.DatabaseProduct.SPARK),
        BIG_QUERY("BigQuery", SqlDialect.DatabaseProduct.BIG_QUERY),
        PRESTO("Presto", SqlDialect.DatabaseProduct.PRESTO),
        CLICKHOUSE("ClickHouse", SqlDialect.DatabaseProduct.CLICKHOUSE);

        private final String displayName;
        private final SqlDialect.DatabaseProduct product;

        CalciteDialect(String displayName, SqlDialect.DatabaseProduct product) {
            this.displayName = displayName;
            this.product = product;
        }

        public String getDisplayName() { return displayName; }
        public SqlDialect.DatabaseProduct getProduct() { return product; }

        /**
         * 获取对应的 Calcite SqlDialect 实例
         */
        public SqlDialect toSqlDialect() {
            switch (this) {
                case MYSQL: return MysqlSqlDialect.DEFAULT;
                case POSTGRESQL: return PostgresqlSqlDialect.DEFAULT;
                case ORACLE: return OracleSqlDialect.DEFAULT;
                case MSSQL: return MssqlSqlDialect.DEFAULT;
                case HIVE: return HiveSqlDialect.DEFAULT;
                case SPARK: return SparkSqlDialect.DEFAULT;
                case BIG_QUERY: return BigQuerySqlDialect.DEFAULT;
                case PRESTO: return PrestoSqlDialect.DEFAULT;
                case CLICKHOUSE: return ClickHouseSqlDialect.DEFAULT;
                case ANSI:
                default:
                    return AnsiSqlDialect.DEFAULT;
            }
        }

        /**
         * 从字符串解析方言（大小写不敏感）
         */
        public static CalciteDialect fromString(String dialect) {
            Preconditions.checkNotNull(dialect, "dialect must not be null");
            switch (dialect.trim().toLowerCase()) {
                case "mysql": return MYSQL;
                case "postgresql": case "postgres": case "pg": return POSTGRESQL;
                case "oracle": return ORACLE;
                case "mssql": case "sqlserver": return MSSQL;
                case "hive": return HIVE;
                case "spark": case "sparksql": return SPARK;
                case "bigquery": case "bq": return BIG_QUERY;
                case "presto": case "trino": return PRESTO;
                case "clickhouse": case "ck": return CLICKHOUSE;
                case "ansi": return ANSI;
                default:
                    throw new IllegalArgumentException("Unsupported dialect: " + dialect);
            }
        }
    }

    // ================================================================
    // SQL 解析
    // ================================================================

    /**
     * 解析 SQL 语句为 Calcite AST（SqlNode）
     *
     * @param sql     SQL 语句
     * @param dialect 源方言（影响词法规则，如引号风格）
     * @return SqlNode AST 根节点
     * @throws SqlParseException 解析失败时抛出
     */
    public SqlNode parse(String sql, CalciteDialect dialect) throws SqlParseException {
        Preconditions.checkNotNull(sql, "sql must not be null");
        Preconditions.checkNotNull(dialect, "dialect must not be null");

        SqlParser.Config config = buildParserConfig(dialect);
        SqlParser parser = SqlParser.create(sql, config);
        return parser.parseStmt();
    }

    /**
     * 解析多条 SQL 语句
     *
     * @param sql     多条 SQL（分号分隔）
     * @param dialect 源方言
     * @return SqlNode 列表
     * @throws SqlParseException 解析失败时抛出
     */
    public SqlNodeList parseMulti(String sql, CalciteDialect dialect) throws SqlParseException {
        Preconditions.checkNotNull(sql, "sql must not be null");
        SqlParser.Config config = buildParserConfig(dialect);
        SqlParser parser = SqlParser.create(sql, config);
        return parser.parseStmtList();
    }

    /**
     * 尝试解析 SQL，返回是否合法
     *
     * @param sql     SQL 语句
     * @param dialect 方言
     * @return true 如果语法合法
     */
    public boolean tryParse(String sql, CalciteDialect dialect) {
        try {
            parse(sql, dialect);
            return true;
        } catch (SqlParseException e) {
            log.debug("Parse failed for dialect {}: {}", dialect, e.getMessage());
            return false;
        }
    }

    // ================================================================
    // SQL 验证（基于 Schema）
    // ================================================================

    /**
     * 在给定 Schema 上验证 SQL
     *
     * @param sql        SQL 语句
     * @param dialect    方言
     * @param schemaInfo 表定义 Map: tableName -> [columnName -> SqlTypeName]
     * @return 验证后的 SqlNode（含类型信息）
     * @throws SqlParseException 解析失败
     * @throws ValidationException 验证失败（表/列不存在等）
     */
    public SqlNode validate(String sql, CalciteDialect dialect,
                            Map<String, Map<String, SqlTypeName>> schemaInfo)
            throws SqlParseException, ValidationException {
        Preconditions.checkNotNull(schemaInfo, "schemaInfo must not be null");

        SqlNode parsed = parse(sql, dialect);

        // 构建 schema
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        RelDataTypeFactory typeFactory = new org.apache.calcite.sql.type.SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);

        for (Map.Entry<String, Map<String, SqlTypeName>> tableEntry : schemaInfo.entrySet()) {
            String tableName = tableEntry.getKey();
            Map<String, SqlTypeName> columns = tableEntry.getValue();
            rootSchema.add(tableName, new SimpleTable(typeFactory, columns));
        }

        // 构建 validator
        CalciteSchema calciteSchema = CalciteSchema.from(rootSchema);
        SqlValidator.Config validatorConfig = SqlValidator.Config.DEFAULT
                .withIdentifierExpansion(true);

        Properties props = new Properties();
        props.setProperty("caseSensitive", "false");
        CalciteConnectionConfigImpl connConfig = new CalciteConnectionConfigImpl(props);

        CalciteCatalogReader catalogReader = new CalciteCatalogReader(
                calciteSchema,
                Collections.singletonList(""),
                typeFactory,
                connConfig
        );

        SqlValidator validator = SqlValidatorUtil.newValidator(
                SqlStdOperatorTable.instance(),
                catalogReader,
                typeFactory,
                validatorConfig
        );

        return validator.validate(parsed);
    }

    /**
     * 尝试验证 SQL，返回验证结果
     */
    public ValidationResult tryValidate(String sql, CalciteDialect dialect,
                                        Map<String, Map<String, SqlTypeName>> schemaInfo) {
        try {
            validate(sql, dialect, schemaInfo);
            return new ValidationResult(true, null);
        } catch (SqlParseException e) {
            return new ValidationResult(false, "Parse error: " + e.getMessage());
        } catch (ValidationException e) {
            return new ValidationResult(false, "Validation error: " + e.getMessage());
        } catch (Exception e) {
            return new ValidationResult(false, "Error: " + e.getMessage());
        }
    }

    // ================================================================
    // 方言转换
    // ================================================================

    /**
     * 将 SQL 从一种方言转换为另一种方言
     *
     * @param sql         原始 SQL
     * @param fromDialect 源方言
     * @param toDialect   目标方言
     * @return 转换后的 SQL 字符串
     * @throws SqlParseException 解析失败时抛出
     */
    public String convert(String sql, CalciteDialect fromDialect, CalciteDialect toDialect)
            throws SqlParseException {
        Preconditions.checkNotNull(toDialect, "toDialect must not be null");

        SqlNode node = parse(sql, fromDialect);
        SqlDialect targetDialect = toDialect.toSqlDialect();
        return node.toSqlString(targetDialect).getSql();
    }

    /**
     * 批量方言转换：将 SQL 转为多种目标方言
     *
     * @param sql         原始 SQL
     * @param fromDialect 源方言
     * @param toDialects  目标方言列表
     * @return Map: 目标方言 -> 转换后的 SQL
     */
    public Map<CalciteDialect, String> convertToMultiple(String sql, CalciteDialect fromDialect,
                                                         CalciteDialect... toDialects)
            throws SqlParseException {
        SqlNode node = parse(sql, fromDialect);
        Map<CalciteDialect, String> results = new LinkedHashMap<>();
        for (CalciteDialect target : toDialects) {
            results.put(target, node.toSqlString(target.toSqlDialect()).getSql());
        }
        return results;
    }

    // ================================================================
    // SQL 格式化 (Pretty Print)
    // ================================================================

    /**
     * 使用 Calcite 格式化 SQL
     *
     * @param sql     SQL 语句
     * @param dialect 方言（影响解析和输出风格）
     * @return 格式化后的 SQL
     * @throws SqlParseException 解析失败
     */
    public String format(String sql, CalciteDialect dialect) throws SqlParseException {
        return format(sql, dialect, 4, true);
    }

    /**
     * 使用 Calcite 格式化 SQL（自定义参数）
     *
     * @param sql               SQL 语句
     * @param dialect           方言
     * @param indentSize        缩进空格数
     * @param uppercaseKeywords 关键字是否大写
     * @return 格式化后的 SQL
     * @throws SqlParseException 解析失败
     */
    public String format(String sql, CalciteDialect dialect, int indentSize, boolean uppercaseKeywords)
            throws SqlParseException {
        SqlNode node = parse(sql, dialect);

        SqlWriterConfig writerConfig = SqlPrettyWriter.config()
                .withIndentation(indentSize)
                .withKeywordsLowerCase(!uppercaseKeywords)
                .withSelectListItemsOnSeparateLines(true)
                .withWhereListItemsOnSeparateLines(true)
                .withFromFolding(SqlWriterConfig.LineFolding.TALL)
                .withClauseStartsLine(true);

        SqlPrettyWriter writer = new SqlPrettyWriter(writerConfig);
        return writer.format(node);
    }

    // ================================================================
    // AST 分析（元数据提取）
    // ================================================================

    /**
     * 从 SQL 中提取表名
     *
     * @param sql     SQL 语句
     * @param dialect 方言
     * @return 表名集合
     * @throws SqlParseException 解析失败
     */
    public Set<String> extractTableNames(String sql, CalciteDialect dialect) throws SqlParseException {
        SqlNode node = parse(sql, dialect);
        Set<String> tables = new LinkedHashSet<>();
        collectTableNames(node, tables);
        return tables;
    }

    /**
     * 从 SQL 中提取列引用
     *
     * @param sql     SQL 语句
     * @param dialect 方言
     * @return 列引用列表（格式可能为 "table.column" 或 "column"）
     * @throws SqlParseException 解析失败
     */
    public List<String> extractColumnRefs(String sql, CalciteDialect dialect) throws SqlParseException {
        SqlNode node = parse(sql, dialect);
        List<String> columns = new ArrayList<>();
        collectColumnRefs(node, columns);
        return columns;
    }

    /**
     * 获取 SQL 语句的类型（SELECT / INSERT / UPDATE / DELETE / CREATE ...）
     *
     * @param sql     SQL 语句
     * @param dialect 方言
     * @return SQL 类型字符串
     * @throws SqlParseException 解析失败
     */
    public String getStatementType(String sql, CalciteDialect dialect) throws SqlParseException {
        SqlNode node = parse(sql, dialect);
        return node.getKind().name();
    }

    /**
     * 获取 AST 的字符串表示（调试用）
     *
     * @param sql     SQL 语句
     * @param dialect 方言
     * @return AST dump 字符串
     * @throws SqlParseException 解析失败
     */
    public String dumpAst(String sql, CalciteDialect dialect) throws SqlParseException {
        SqlNode node = parse(sql, dialect);
        return node.toString();
    }

    // ================================================================
    // 关系代数转换
    // ================================================================

    /**
     * 将 SQL 转换为关系代数逻辑计划（RelNode）
     *
     * @param sql        SQL 语句
     * @param dialect    方言
     * @param schemaInfo 表定义
     * @return RelRoot（含 RelNode 根节点和输出行类型）
     * @throws SqlParseException   解析失败
     * @throws ValidationException 验证失败
     * @throws RelConversionException 关系代数转换失败
     */
    public RelRoot toRelNode(String sql, CalciteDialect dialect,
                             Map<String, Map<String, SqlTypeName>> schemaInfo)
            throws SqlParseException, ValidationException, RelConversionException {
        Preconditions.checkNotNull(schemaInfo, "schemaInfo must not be null");

        // 构建 schema
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        RelDataTypeFactory typeFactory = new org.apache.calcite.sql.type.SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);

        for (Map.Entry<String, Map<String, SqlTypeName>> tableEntry : schemaInfo.entrySet()) {
            rootSchema.add(tableEntry.getKey(), new SimpleTable(typeFactory, tableEntry.getValue()));
        }

        // 构建 Planner
        SqlParser.Config parserConfig = buildParserConfig(dialect);
        FrameworkConfig frameworkConfig = Frameworks.newConfigBuilder()
                .defaultSchema(rootSchema)
                .parserConfig(parserConfig)
                .build();

        Planner planner = Frameworks.getPlanner(frameworkConfig);
        SqlNode parsed = planner.parse(sql);
        SqlNode validated = planner.validate(parsed);
        return planner.rel(validated);
    }

    /**
     * 获取逻辑计划的可读文本（EXPLAIN 格式）
     *
     * @param sql        SQL 语句
     * @param dialect    方言
     * @param schemaInfo 表定义
     * @return 逻辑计划文本
     */
    public String explain(String sql, CalciteDialect dialect,
                          Map<String, Map<String, SqlTypeName>> schemaInfo)
            throws SqlParseException, ValidationException, RelConversionException {
        RelRoot relRoot = toRelNode(sql, dialect, schemaInfo);
        return RelOptUtil.toString(relRoot.rel);
    }

    // ================================================================
    // 辅助方法
    // ================================================================

    /**
     * 构建 Parser 配置（根据方言调整词法规则）
     */
    private SqlParser.Config buildParserConfig(CalciteDialect dialect) {
        SqlParser.Config config;
        switch (dialect) {
            case MYSQL:
                config = SqlParser.config()
                        .withLex(Lex.MYSQL)
                        .withCaseSensitive(false);
                break;
            case ORACLE:
                config = SqlParser.config()
                        .withLex(Lex.ORACLE)
                        .withCaseSensitive(false);
                break;
            case BIG_QUERY:
                config = SqlParser.config()
                        .withLex(Lex.BIG_QUERY)
                        .withCaseSensitive(false);
                break;
            case SPARK:
            case HIVE:
                config = SqlParser.config()
                        .withLex(Lex.JAVA)
                        .withCaseSensitive(false);
                break;
            default:
                config = SqlParser.config()
                        .withLex(Lex.ORACLE)
                        .withCaseSensitive(false)
                        .withQuoting(Quoting.DOUBLE_QUOTE)
                        .withUnquotedCasing(Casing.TO_UPPER)
                        .withQuotedCasing(Casing.UNCHANGED);
                break;
        }
        return config;
    }

    /**
     * 递归遍历 AST，收集表名
     */
    private void collectTableNames(SqlNode node, Set<String> tables) {
        if (node == null) return;

        if (node instanceof SqlIdentifier) {
            // 单独出现的标识符可能不是表名，由调用方上下文确定
            return;
        }

        if (node instanceof SqlJoin) {
            SqlJoin join = (SqlJoin) node;
            collectTableNames(join.getLeft(), tables);
            collectTableNames(join.getRight(), tables);
            collectTableNames(join.getCondition(), tables);
            return;
        }

        if (node instanceof SqlSelect) {
            SqlSelect select = (SqlSelect) node;
            collectTableNamesFromSource(select.getFrom(), tables);
            collectTableNames(select.getWhere(), tables);
            if (select.getHaving() != null) {
                collectTableNames(select.getHaving(), tables);
            }
            if (select.getSelectList() != null) {
                for (SqlNode item : select.getSelectList()) {
                    collectTableNames(item, tables);
                }
            }
            return;
        }

        if (node instanceof SqlInsert) {
            SqlInsert insert = (SqlInsert) node;
            collectTableNamesFromSource(insert.getTargetTable(), tables);
            collectTableNames(insert.getSource(), tables);
            return;
        }

        if (node instanceof SqlUpdate) {
            SqlUpdate update = (SqlUpdate) node;
            collectTableNamesFromSource(update.getTargetTable(), tables);
            collectTableNames(update.getCondition(), tables);
            return;
        }

        if (node instanceof SqlDelete) {
            SqlDelete delete = (SqlDelete) node;
            collectTableNamesFromSource(delete.getTargetTable(), tables);
            collectTableNames(delete.getCondition(), tables);
            return;
        }

        if (node instanceof SqlOrderBy) {
            SqlOrderBy orderBy = (SqlOrderBy) node;
            collectTableNames(orderBy.query, tables);
            return;
        }

        if (node instanceof SqlBasicCall) {
            SqlBasicCall call = (SqlBasicCall) node;
            if (call.getOperator().getKind() == SqlKind.AS) {
                // AS 别名：左侧是真实来源
                collectTableNamesFromSource(call.operand(0), tables);
            } else {
                for (SqlNode operand : call.getOperandList()) {
                    collectTableNames(operand, tables);
                }
            }
            return;
        }

        if (node instanceof SqlNodeList) {
            for (SqlNode child : (SqlNodeList) node) {
                collectTableNames(child, tables);
            }
        }
    }

    /**
     * 从 FROM 子句中收集表名
     */
    private void collectTableNamesFromSource(SqlNode node, Set<String> tables) {
        if (node == null) return;

        if (node instanceof SqlIdentifier) {
            tables.add(((SqlIdentifier) node).toString());
            return;
        }

        if (node instanceof SqlJoin) {
            SqlJoin join = (SqlJoin) node;
            collectTableNamesFromSource(join.getLeft(), tables);
            collectTableNamesFromSource(join.getRight(), tables);
            return;
        }

        if (node instanceof SqlBasicCall) {
            SqlBasicCall call = (SqlBasicCall) node;
            if (call.getOperator().getKind() == SqlKind.AS) {
                collectTableNamesFromSource(call.operand(0), tables);
            } else {
                collectTableNames(node, tables);
            }
            return;
        }

        // 子查询
        if (node instanceof SqlSelect || node instanceof SqlOrderBy) {
            collectTableNames(node, tables);
        }
    }

    /**
     * 递归遍历 AST，收集列引用
     */
    private void collectColumnRefs(SqlNode node, List<String> columns) {
        if (node == null) return;

        if (node instanceof SqlIdentifier) {
            SqlIdentifier id = (SqlIdentifier) node;
            // 只收集非星号标识符
            if (!id.isStar()) {
                columns.add(id.toString());
            }
            return;
        }

        if (node instanceof SqlSelect) {
            SqlSelect select = (SqlSelect) node;
            if (select.getSelectList() != null) {
                for (SqlNode item : select.getSelectList()) {
                    collectColumnRefs(item, columns);
                }
            }
            return;
        }

        if (node instanceof SqlBasicCall) {
            for (SqlNode operand : ((SqlBasicCall) node).getOperandList()) {
                collectColumnRefs(operand, columns);
            }
            return;
        }

        if (node instanceof SqlNodeList) {
            for (SqlNode child : (SqlNodeList) node) {
                collectColumnRefs(child, columns);
            }
        }
    }

    // ================================================================
    // 内部类
    // ================================================================

    /**
     * 验证结果
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;

        public ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }

        public boolean isValid() { return valid; }
        public String getErrorMessage() { return errorMessage; }

        @Override
        public String toString() {
            return valid ? "VALID" : "INVALID: " + errorMessage;
        }
    }

    /**
     * 简单表定义（用于构建 Calcite Schema）
     */
    private static class SimpleTable extends AbstractTable {
        private final RelDataTypeFactory typeFactory;
        private final Map<String, SqlTypeName> columns;

        SimpleTable(RelDataTypeFactory typeFactory, Map<String, SqlTypeName> columns) {
            this.typeFactory = typeFactory;
            this.columns = columns;
        }

        @Override
        public RelDataType getRowType(RelDataTypeFactory factory) {
            RelDataTypeFactory.Builder builder = factory.builder();
            for (Map.Entry<String, SqlTypeName> col : columns.entrySet()) {
                builder.add(col.getKey(), col.getValue());
            }
            return builder.build();
        }
    }
}
