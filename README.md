# ANTLR SQL Parser

一个基于 ANTLR4 的多 SQL 方言解析器，支持 **MySQL**、**PostgreSQL** 和 **SparkSQL** 三种语法。

## 项目结构

```
antlr-demo01/
├── pom.xml                              # Maven 构建配置
├── src/
│   ├── main/
│   │   ├── antlr4/
│   │   │   ├── mysql/                   # MySQL 语法
│   │   │   │   ├── MySqlLexer.g4
│   │   │   │   └── MySqlParser.g4
│   │   │   ├── postgresql/              # PostgreSQL 语法
│   │   │   │   ├── PostgreSqlLexer.g4
│   │   │   │   └── PostgreSqlParser.g4
│   │   │   └── sparksql/               # SparkSQL 语法
│   │   │       ├── SparkSqlLexer.g4
│   │   │       └── SparkSqlParser.g4
│   │   └── java/com/example/antlr/
│   │       ├── SqlDialect.java          # SQL 方言枚举
│   │       ├── SqlParserEngine.java     # 统一解析引擎
│   │       ├── SqlParseResult.java      # 解析结果封装
│   │       ├── SqlErrorListener.java    # 错误监听器
│   │       └── SqlParserApp.java        # 主程序入口
│   └── test/java/com/example/antlr/
│       └── SqlParserEngineTest.java     # 单元测试
```

## 支持的语法特性

### MySQL
- DDL: `CREATE TABLE`, `DROP TABLE`, `ALTER TABLE`, `CREATE INDEX` 等
- DML: `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- MySQL 特有: `AUTO_INCREMENT`, `ENGINE`, `CHARSET`, `BACKTICK` 引用, `SHOW DATABASES/TABLES`, `USE`, `DESCRIBE`
- 数据类型: `TINYINT`, `SMALLINT`, `INT`, `BIGINT`, `VARCHAR`, `TEXT`, `DATETIME`, `ENUM` 等

### PostgreSQL
- DDL: `CREATE TABLE`, `DROP TABLE`, `ALTER TABLE`, `CREATE INDEX`, `CREATE SCHEMA`
- DML: `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- PG 特有: `SERIAL`/`BIGSERIAL`, `ILIKE`, `::` 类型转换, `||` 连接, `JSONB`, `RETURNING`, `CTE WITH`, `FULL OUTER JOIN`, `NULLS FIRST/LAST`
- 数据类型: `SERIAL`, `JSONB`, `UUID`, `BYTEA`, `INET`, `CIDR`, `INTERVAL`, `MONEY` 等

### SparkSQL
- DDL: `CREATE TABLE`, `CREATE VIEW`, `ALTER TABLE`, 分区操作
- DML: `SELECT`, `INSERT INTO`, `INSERT OVERWRITE`
- Spark 特有: `USING <format>`, `PARTITIONED BY`, `CLUSTERED BY`, `RLIKE`/`REGEXP`, `LEFT SEMI JOIN`, `LEFT ANTI JOIN`, `LATERAL VIEW`, `WINDOW`, `DISTRIBUTE BY`, `SORT BY`, `TABLESAMPLE`, `EXPLAIN`, `CACHE TABLE`
- 数据类型: `STRING`, `ARRAY<T>`, `MAP<K,V>`, `STRUCT<...>` 等复合类型

## 快速开始

### 环境要求
- JDK 11+
- Maven 3.6+

### 编译项目

```bash
mvn clean compile
```

### 运行测试

```bash
mvn test
```

### 命令行使用

```bash
# 编译打包
mvn clean package -DskipTests

# 命令行模式
java -jar target/antlr-sql-parser-1.0-SNAPSHOT.jar mysql "SELECT * FROM users WHERE id = 1"
java -jar target/antlr-sql-parser-1.0-SNAPSHOT.jar pg "SELECT * FROM users WHERE email ILIKE '%@test.com'"
java -jar target/antlr-sql-parser-1.0-SNAPSHOT.jar spark "SHOW DATABASES"

# 交互模式
java -jar target/antlr-sql-parser-1.0-SNAPSHOT.jar
```

### 代码调用

```java
import com.example.antlr.*;

SqlParserEngine engine = new SqlParserEngine();

// 解析 MySQL
SqlParseResult result = engine.parse("SELECT * FROM users", SqlDialect.MYSQL);
System.out.println(result.isSuccess());    // true
System.out.println(result.getParseTree()); // 语法树

// 解析 PostgreSQL
result = engine.parse("SELECT data->>'name' FROM users", SqlDialect.POSTGRESQL);

// 解析 SparkSQL
result = engine.parse("SHOW DATABASES", SqlDialect.SPARKSQL);

// 验证 SQL 是否合法
boolean valid = engine.validate("SELECT 1", SqlDialect.MYSQL);
```

## 方言对比

| 特性 | MySQL | PostgreSQL | SparkSQL |
|------|-------|------------|----------|
| 引号标识符 | \`backtick\` | "double_quote" | \`backtick\` |
| 字符串连接 | CONCAT() | \|\| | \|\| |
| 正则匹配 | LIKE/REGEXP | LIKE/ILIKE/~ | LIKE/RLIKE/REGEXP |
| 自增主键 | AUTO_INCREMENT | SERIAL | - |
| JSON 类型 | JSON | JSON/JSONB | - |
| 复合类型 | - | ARRAY | ARRAY/MAP/STRUCT |
| 分区表 | - | PARTITION BY | PARTITIONED BY |
| RETURNING | - | ✅ | - |
| 窗口函数 | ✅ | ✅ | ✅ (OVER) |
# antlr-demo01
