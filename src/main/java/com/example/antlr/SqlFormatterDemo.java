package com.example.antlr;

/**
 * SQL 格式化/美化器演示
 *
 * 展示 MySqlFormatter（Visitor 模式）对各种 SQL 语句的格式化效果
 */
public class SqlFormatterDemo {

    private static final SqlParserEngine engine = new SqlParserEngine();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║          MySQL SQL 格式化/美化器 演示                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        demo1_SimpleSelect();
        demo2_ComplexSelect();
        demo3_JoinQuery();
        demo4_SubQuery();
        demo5_Insert();
        demo6_Update();
        demo7_Delete();
        demo8_CreateTable();
        demo9_CaseExpression();
        demo10_Union();
        demo11_LowercaseMode();
    }

    // ----------------------------------------------------------
    // 1. 简单 SELECT
    // ----------------------------------------------------------
    private static void demo1_SimpleSelect() {
        printHeader("1. 简单 SELECT 格式化");

        String sql = "select id,name,age from users where age>18 order by name limit 10";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 2. 复杂 SELECT（多列 + 函数 + GROUP BY + HAVING）
    // ----------------------------------------------------------
    private static void demo2_ComplexSelect() {
        printHeader("2. 复杂 SELECT（函数 + GROUP BY + HAVING）");

        String sql = "select department,count(*) as cnt,avg(salary) as avg_salary from employees where status='active' group by department having count(*)>5 order by avg_salary desc limit 20";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 3. 多表 JOIN
    // ----------------------------------------------------------
    private static void demo3_JoinQuery() {
        printHeader("3. 多表 JOIN");

        String sql = "select u.name,o.order_id,o.amount,p.product_name from users u inner join orders o on u.id=o.user_id left join products p on o.product_id=p.id where o.amount>100 and u.status='active' order by o.amount desc";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 4. 子查询
    // ----------------------------------------------------------
    private static void demo4_SubQuery() {
        printHeader("4. 子查询（FROM + IN）");

        String sql = "select name,salary from employees where department_id in (select id from departments where name='Engineering') and salary>(select avg(salary) from employees)";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 5. INSERT
    // ----------------------------------------------------------
    private static void demo5_Insert() {
        printHeader("5. INSERT 语句");

        String sql = "insert into users (name,email,age,status) values ('Alice','alice@example.com',25,'active'),('Bob','bob@example.com',30,'active'),('Charlie','charlie@example.com',35,'inactive')";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 6. UPDATE
    // ----------------------------------------------------------
    private static void demo6_Update() {
        printHeader("6. UPDATE 语句");

        String sql = "update employees set salary=salary*1.1,updated_at='2024-01-01',status='promoted' where department='Engineering' and performance_score>90";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 7. DELETE
    // ----------------------------------------------------------
    private static void demo7_Delete() {
        printHeader("7. DELETE 语句");

        String sql = "delete from logs where created_at<'2023-01-01' and level='DEBUG'";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 8. CREATE TABLE
    // ----------------------------------------------------------
    private static void demo8_CreateTable() {
        printHeader("8. CREATE TABLE");

        String sql = "create table if not exists orders (id bigint unsigned not null auto_increment,user_id int not null,product_name varchar(200) not null,amount decimal(10,2) default 0.00,status tinyint default 1 comment '状态',created_at datetime not null,primary key (id),index idx_user_id (user_id),foreign key (user_id) references users (id))";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 9. CASE 表达式
    // ----------------------------------------------------------
    private static void demo9_CaseExpression() {
        printHeader("9. CASE 表达式");

        String sql = "select name,salary,case when salary>10000 then 'high' when salary>5000 then 'medium' else 'low' end as salary_level from employees order by salary desc";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 10. UNION
    // ----------------------------------------------------------
    private static void demo10_Union() {
        printHeader("10. UNION 查询");

        String sql = "select name,email from customers where status='active' union all select name,email from suppliers where status='active'";
        printFormatted(sql);
    }

    // ----------------------------------------------------------
    // 11. 小写模式
    // ----------------------------------------------------------
    private static void demo11_LowercaseMode() {
        printHeader("11. 小写关键字模式");

        String sql = "SELECT u.name, COUNT(*) AS cnt FROM users u WHERE u.age > 18 GROUP BY u.name HAVING COUNT(*) > 1 ORDER BY cnt DESC LIMIT 10";

        System.out.println("  原始 SQL:");
        System.out.println("  " + sql);
        System.out.println();
        System.out.println("  小写关键字格式化:");
        System.out.println("  ┌─────────────────────────────────────────────");
        String formatted = engine.formatMySql(sql, 4, false);
        for (String line : formatted.split("\n")) {
            System.out.println("  │ " + line);
        }
        System.out.println("  └─────────────────────────────────────────────");
        System.out.println();
    }

    // ============================================================
    // 辅助方法
    // ============================================================

    private static void printHeader(String title) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  " + title);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    private static void printFormatted(String sql) {
        System.out.println("  原始 SQL:");
        System.out.println("  " + sql);
        System.out.println();

        String formatted = engine.formatMySql(sql);

        System.out.println("  格式化后:");
        System.out.println("  ┌─────────────────────────────────────────────");
        for (String line : formatted.split("\n")) {
            System.out.println("  │ " + line);
        }
        System.out.println("  └─────────────────────────────────────────────");
        System.out.println();
    }
}
