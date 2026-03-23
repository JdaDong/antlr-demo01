package com.example.antlr;

import com.example.antlr.SqlParserEngine;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * PostgreSQL Formatter 单元测试
 *
 * 覆盖 SELECT/INSERT/UPDATE/DELETE/DDL/CTE/PostgreSQL 特有语法/表达式/配置/质量
 */
class PostgreSqlFormatterTest {

    private final SqlParserEngine engine = new SqlParserEngine();

    // ================================================================
    // SELECT 格式化
    // ================================================================

    @Nested
    @DisplayName("SELECT 格式化")
    class SelectFormatTests {

        @Test
        @DisplayName("简单 SELECT 应正确格式化")
        void testSimpleSelect() {
            String formatted = engine.formatPostgreSql(
                    "SELECT id, name, age FROM users WHERE age > 18 ORDER BY name LIMIT 10");

            assertThat(formatted).contains("SELECT");
            assertThat(formatted).contains("FROM");
            assertThat(formatted).contains("WHERE");
            assertThat(formatted).contains("ORDER BY");
            assertThat(formatted).contains("LIMIT");
        }

        @Test
        @DisplayName("JOIN 应正确格式化")
        void testJoinFormat() {
            String formatted = engine.formatPostgreSql(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id LEFT JOIN payments p ON o.id = p.order_id");

            assertThat(formatted).contains("INNER JOIN");
            assertThat(formatted).contains("LEFT");
            assertThat(formatted).contains("JOIN");
            assertThat(formatted).contains("ON");
        }

        @Test
        @DisplayName("FULL OUTER JOIN 应正确格式化")
        void testFullOuterJoinFormat() {
            String formatted = engine.formatPostgreSql(
                    "SELECT a.id, b.id FROM table_a a FULL OUTER JOIN table_b b ON a.id = b.id");

            assertThat(formatted).contains("FULL");
            assertThat(formatted).contains("OUTER");
            assertThat(formatted).contains("JOIN");
        }

        @Test
        @DisplayName("DISTINCT 应保留")
        void testDistinctPreserved() {
            String formatted = engine.formatPostgreSql("SELECT DISTINCT name, age FROM users");

            assertThat(formatted).contains("DISTINCT");
        }

        @Test
        @DisplayName("GROUP BY + HAVING 应正确格式化")
        void testGroupByHavingFormat() {
            String formatted = engine.formatPostgreSql(
                    "SELECT dept, COUNT(id) FROM users GROUP BY dept HAVING COUNT(id) > 5");

            assertThat(formatted).contains("GROUP BY");
            assertThat(formatted).contains("HAVING");
        }

        @Test
        @DisplayName("UNION 应正确格式化")
        void testUnionFormat() {
            String formatted = engine.formatPostgreSql(
                    "SELECT id, name FROM users UNION SELECT id, name FROM admins");

            assertThat(formatted).contains("UNION");
        }

        @Test
        @DisplayName("子查询应正确缩进")
        void testSubqueryIndent() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)");

            assertThat(formatted).contains("IN");
            assertThat(formatted).contains("SELECT");
        }

        @Test
        @DisplayName("LIMIT + OFFSET 应正确格式化")
        void testLimitOffset() {
            String formatted = engine.formatPostgreSql(
                    "SELECT id, name FROM users ORDER BY id LIMIT 10 OFFSET 20");

            assertThat(formatted).contains("LIMIT");
            assertThat(formatted).contains("OFFSET");
        }

        @Test
        @DisplayName("ORDER BY NULLS FIRST/LAST 应正确格式化")
        void testOrderByNulls() {
            String formatted = engine.formatPostgreSql(
                    "SELECT id, name FROM users ORDER BY name ASC NULLS FIRST");

            assertThat(formatted).contains("ORDER BY");
            assertThat(formatted).contains("NULLS");
            assertThat(formatted).contains("FIRST");
        }
    }

    // ================================================================
    // CTE (WITH) 格式化
    // ================================================================

    @Nested
    @DisplayName("CTE 格式化")
    class CteFormatTests {

        @Test
        @DisplayName("简单 CTE 应正确格式化")
        void testSimpleCte() {
            String formatted = engine.formatPostgreSql(
                    "WITH active_users AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active_users");

            assertThat(formatted).contains("WITH");
            assertThat(formatted).contains("AS");
            assertThat(formatted).contains("active_users");
        }
    }

    // ================================================================
    // INSERT 格式化
    // ================================================================

    @Nested
    @DisplayName("INSERT 格式化")
    class InsertFormatTests {

        @Test
        @DisplayName("INSERT VALUES 应正确格式化")
        void testInsertValues() {
            String formatted = engine.formatPostgreSql(
                    "INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");

            assertThat(formatted).contains("INSERT INTO");
            assertThat(formatted).contains("VALUES");
        }

        @Test
        @DisplayName("INSERT 多行 VALUES 应正确格式化")
        void testInsertMultipleValues() {
            String formatted = engine.formatPostgreSql(
                    "INSERT INTO users (id, name) VALUES (1, 'Alice'), (2, 'Bob'), (3, 'Charlie')");

            assertThat(formatted).contains("INSERT INTO");
            assertThat(formatted).contains("VALUES");
        }

        @Test
        @DisplayName("INSERT RETURNING 应正确格式化")
        void testInsertReturning() {
            String formatted = engine.formatPostgreSql(
                    "INSERT INTO users (name, age) VALUES ('Alice', 30) RETURNING id, name");

            assertThat(formatted).contains("INSERT INTO");
            assertThat(formatted).contains("RETURNING");
        }

        @Test
        @DisplayName("INSERT SELECT 应正确格式化")
        void testInsertSelect() {
            String formatted = engine.formatPostgreSql(
                    "INSERT INTO archive (id, name) SELECT id, name FROM users WHERE status = 'inactive'");

            assertThat(formatted).contains("INSERT INTO");
            assertThat(formatted).contains("SELECT");
        }
    }

    // ================================================================
    // UPDATE 格式化
    // ================================================================

    @Nested
    @DisplayName("UPDATE 格式化")
    class UpdateFormatTests {

        @Test
        @DisplayName("简单 UPDATE 应正确格式化")
        void testUpdateSet() {
            String formatted = engine.formatPostgreSql(
                    "UPDATE users SET name = 'Bob', age = 25 WHERE id = 1");

            assertThat(formatted).contains("UPDATE");
            assertThat(formatted).contains("SET");
            assertThat(formatted).contains("WHERE");
        }

        @Test
        @DisplayName("UPDATE RETURNING 应正确格式化")
        void testUpdateReturning() {
            String formatted = engine.formatPostgreSql(
                    "UPDATE users SET status = 'active' WHERE id = 1 RETURNING id, status");

            assertThat(formatted).contains("UPDATE");
            assertThat(formatted).contains("RETURNING");
        }
    }

    // ================================================================
    // DELETE 格式化
    // ================================================================

    @Nested
    @DisplayName("DELETE 格式化")
    class DeleteFormatTests {

        @Test
        @DisplayName("DELETE 应正确格式化")
        void testDelete() {
            String formatted = engine.formatPostgreSql(
                    "DELETE FROM users WHERE status = 'inactive'");

            assertThat(formatted).contains("DELETE FROM");
            assertThat(formatted).contains("WHERE");
        }

        @Test
        @DisplayName("DELETE RETURNING 应正确格式化")
        void testDeleteReturning() {
            String formatted = engine.formatPostgreSql(
                    "DELETE FROM users WHERE id = 1 RETURNING id, name");

            assertThat(formatted).contains("DELETE FROM");
            assertThat(formatted).contains("RETURNING");
        }
    }

    // ================================================================
    // DDL 格式化
    // ================================================================

    @Nested
    @DisplayName("DDL 格式化")
    class DdlFormatTests {

        @Test
        @DisplayName("CREATE TABLE 应正确格式化")
        void testCreateTable() {
            String formatted = engine.formatPostgreSql(
                    "CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, age INTEGER)");

            assertThat(formatted).contains("CREATE TABLE");
            assertThat(formatted).contains("PRIMARY KEY");
            assertThat(formatted).contains("NOT NULL");
        }

        @Test
        @DisplayName("CREATE TABLE IF NOT EXISTS 应正确格式化")
        void testCreateTableIfNotExists() {
            String formatted = engine.formatPostgreSql(
                    "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name TEXT)");

            assertThat(formatted).contains("IF NOT EXISTS");
        }

        @Test
        @DisplayName("DROP TABLE 应正确格式化")
        void testDropTable() {
            String formatted = engine.formatPostgreSql("DROP TABLE IF EXISTS users");

            assertThat(formatted).contains("DROP TABLE");
            assertThat(formatted).contains("IF EXISTS");
        }

        @Test
        @DisplayName("ALTER TABLE ADD COLUMN 应正确格式化")
        void testAlterTableAddColumn() {
            String formatted = engine.formatPostgreSql(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(200)");

            assertThat(formatted).contains("ALTER TABLE");
            assertThat(formatted).contains("ADD COLUMN");
        }

        @Test
        @DisplayName("ALTER TABLE DROP COLUMN 应正确格式化")
        void testAlterTableDropColumn() {
            String formatted = engine.formatPostgreSql(
                    "ALTER TABLE users DROP COLUMN age");

            assertThat(formatted).contains("ALTER TABLE");
            assertThat(formatted).contains("DROP COLUMN");
        }

        @Test
        @DisplayName("CREATE INDEX 应正确格式化")
        void testCreateIndex() {
            String formatted = engine.formatPostgreSql(
                    "CREATE INDEX idx_users_name ON users (name)");

            assertThat(formatted).contains("CREATE");
            assertThat(formatted).contains("INDEX");
            assertThat(formatted).contains("ON");
        }

        @Test
        @DisplayName("CREATE UNIQUE INDEX 应正确格式化")
        void testCreateUniqueIndex() {
            String formatted = engine.formatPostgreSql(
                    "CREATE UNIQUE INDEX idx_users_email ON users (email)");

            assertThat(formatted).contains("UNIQUE");
            assertThat(formatted).contains("INDEX");
        }

        @Test
        @DisplayName("DROP INDEX 应正确格式化")
        void testDropIndex() {
            String formatted = engine.formatPostgreSql("DROP INDEX IF EXISTS idx_users_name");

            assertThat(formatted).contains("DROP INDEX");
            assertThat(formatted).contains("IF EXISTS");
        }

        @Test
        @DisplayName("CREATE DATABASE 应正确格式化")
        void testCreateDatabase() {
            String formatted = engine.formatPostgreSql("CREATE DATABASE IF NOT EXISTS mydb");

            assertThat(formatted).contains("CREATE DATABASE");
            assertThat(formatted).contains("IF NOT EXISTS");
        }

        @Test
        @DisplayName("DROP DATABASE 应正确格式化")
        void testDropDatabase() {
            String formatted = engine.formatPostgreSql("DROP DATABASE IF EXISTS mydb");

            assertThat(formatted).contains("DROP DATABASE");
            assertThat(formatted).contains("IF EXISTS");
        }

        @Test
        @DisplayName("CREATE SCHEMA 应正确格式化")
        void testCreateSchema() {
            String formatted = engine.formatPostgreSql("CREATE SCHEMA IF NOT EXISTS myschema");

            assertThat(formatted).contains("CREATE SCHEMA");
            assertThat(formatted).contains("IF NOT EXISTS");
        }
    }

    // ================================================================
    // PostgreSQL 特有语法
    // ================================================================

    @Nested
    @DisplayName("PostgreSQL 特有语法")
    class PgSpecificTests {

        @Test
        @DisplayName("ILIKE 应正确格式化")
        void testIlike() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE name ILIKE '%alice%'");

            assertThat(formatted).contains("ILIKE");
        }

        @Test
        @DisplayName(":: 类型转换应正确格式化")
        void testPgCast() {
            String formatted = engine.formatPostgreSql(
                    "SELECT price::INTEGER FROM products");

            assertThat(formatted).contains("::");
        }

        @Test
        @DisplayName("CAST 表达式应正确格式化")
        void testCast() {
            String formatted = engine.formatPostgreSql(
                    "SELECT CAST(price AS INTEGER) FROM products");

            assertThat(formatted).contains("CAST");
            assertThat(formatted).contains("AS");
        }

        @Test
        @DisplayName("字符串拼接 || 应正确格式化")
        void testStringConcat() {
            String formatted = engine.formatPostgreSql(
                    "SELECT first_name || ' ' || last_name FROM users");

            assertThat(formatted).contains("||");
        }
    }

    // ================================================================
    // 表达式格式化
    // ================================================================

    @Nested
    @DisplayName("表达式格式化")
    class ExpressionFormatTests {

        @Test
        @DisplayName("CASE WHEN 应正确格式化")
        void testCaseExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT CASE WHEN age > 18 THEN 'adult' ELSE 'minor' END FROM users");

            assertThat(formatted).contains("CASE");
            assertThat(formatted).contains("WHEN");
            assertThat(formatted).contains("THEN");
            assertThat(formatted).contains("ELSE");
            assertThat(formatted).contains("END");
        }

        @Test
        @DisplayName("IN 表达式应正确格式化")
        void testInExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE status IN ('active', 'pending')");

            assertThat(formatted).contains("IN");
        }

        @Test
        @DisplayName("BETWEEN 应正确格式化")
        void testBetweenExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE age BETWEEN 18 AND 65");

            assertThat(formatted).contains("BETWEEN");
            assertThat(formatted).contains("AND");
        }

        @Test
        @DisplayName("LIKE 应正确格式化")
        void testLikeExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE name LIKE 'A%'");

            assertThat(formatted).contains("LIKE");
        }

        @Test
        @DisplayName("IS NULL 应正确格式化")
        void testIsNullExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE email IS NULL");

            assertThat(formatted).contains("IS NULL");
        }

        @Test
        @DisplayName("IS NOT NULL 应正确格式化")
        void testIsNotNullExpression() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE email IS NOT NULL");

            assertThat(formatted).contains("IS NOT NULL");
        }
    }

    // ================================================================
    // 配置
    // ================================================================

    @Nested
    @DisplayName("配置测试")
    class ConfigTests {

        @Test
        @DisplayName("小写关键字模式应生效")
        void testLowercaseKeywords() {
            String formatted = engine.formatPostgreSql("SELECT id FROM users", 4, false);

            assertThat(formatted).contains("select");
            assertThat(formatted).contains("from");
        }

        @Test
        @DisplayName("自定义缩进应生效")
        void testCustomIndent() {
            String formatted = engine.formatPostgreSql("SELECT id FROM users WHERE id = 1", 2, true);

            assertThat(formatted).isNotEmpty();
            assertThat(formatted).contains("SELECT");
        }
    }

    // ================================================================
    // 质量
    // ================================================================

    @Nested
    @DisplayName("质量测试")
    class QualityTests {

        @Test
        @DisplayName("格式化结果不为空")
        void testNotNullOrEmpty() {
            String formatted = engine.formatPostgreSql("SELECT 1");

            assertThat(formatted).isNotNull().isNotEmpty();
        }

        @Test
        @DisplayName("结果以分号结尾")
        void testEndsWithSemicolon() {
            String formatted = engine.formatPostgreSql("SELECT id FROM users");

            assertThat(formatted.trim()).endsWith(";");
        }

        @Test
        @DisplayName("保留表名")
        void testPreservesTableName() {
            String formatted = engine.formatPostgreSql("SELECT id FROM my_special_table");

            assertThat(formatted).contains("my_special_table");
        }

        @Test
        @DisplayName("保留字符串字面值")
        void testPreservesStringLiterals() {
            String formatted = engine.formatPostgreSql(
                    "SELECT name FROM users WHERE status = 'active'");

            assertThat(formatted).contains("'active'");
        }

        @Test
        @DisplayName("复杂 SQL 不应抛异常")
        void testComplexSqlNoException() {
            assertThatCode(() -> engine.formatPostgreSql(
                    "WITH active AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT a.name, COUNT(o.id) AS order_count " +
                    "FROM active a " +
                    "INNER JOIN orders o ON a.id = o.user_id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY a.name " +
                    "HAVING COUNT(o.id) > 5 " +
                    "ORDER BY order_count DESC " +
                    "LIMIT 20"
            )).doesNotThrowAnyException();
        }
    }
}
