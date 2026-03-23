# MySqlMetadataVisitor

## Visit 回调方法调用栈详解

*ANTLR4 Visitor 模式深度解析 — antlr-demo01 项目技术文档*

---

## 1. ANTLR Visitor 核心机制

ANTLR4 的 Visitor 模式是一种基于访问者设计模式的语法树遍历方法。与 Listener 模式不同，Visitor 不会自动遍历所有子节点，需要开发者显式调用 `visitChildren(ctx)` 来控制遍历流程。

### 1.1 核心调用链路

```
AbstractParseTreeVisitor.visit(tree)       ← 入口
  └── tree.accept(this)                    ← ParseTree 节点调用 accept
        └── visitor.visitXxx(this)          ← 分发到具体的 visitXxx 方法
              └── visitChildren(ctx)        ← 手动递归遍历所有子节点
                    └── 对每个子节点重复 child.accept(this)
```

### 1.2 关键原理

> **ANTLR 的 Visitor 模式不会自动递归，需要每个 visitXxx 方法末尾调用 `return visitChildren(ctx)` 才能继续向下遍历。**

- `MySqlParserBaseVisitor` 中所有方法的默认实现都是 `return visitChildren(ctx)`（透传遍历）。
- `MySqlMetadataVisitor` 只 override 了其中 **17 个** 方法来提取元数据，未 override 的中间节点由 BaseVisitor 自动透传。

### 1.3 三层调用栈模型

| 层级 | 节点类型 | 行为 | 示例 |
|---|---|---|---|
| **第 1 层** | `root` → `sqlStatements` → `sqlStatement` → `ddl/dmlStatement` | BaseVisitor 默认 `visitChildren` 透传，不做任何事 | 4 个节点自动穿越 |
| **第 2 层** | `selectStatement`, `insertStatement`, `createTable` 等 | ★ 已 override：设置 `statementType`，提取表名/列名 | 共 14 个 override 方法 |
| **第 3 层** | `tableSource`, `joinClause`, `whereClause`, `columnRefExpression` 等 | ★ 已 override：收集表名、列名、别名、函数、JOIN 条件等 | 共 7 个 override 方法 |

---

## 2. 语法规则层次结构

下面是 MySqlParser.g4 定义的语法规则树结构，展示了从 root 到叶子节点的完整层次：

```
root
  └─ sqlStatements
       └─ sqlStatement
            ├─ ddlStatement
            │    ├─ createDatabase     ├─ createTable
            │    ├─ dropDatabase       ├─ dropTable
            │    ├─ alterTable         ├─ truncateTable
            │    ├─ createIndex        └─ dropIndex
            ├─ dmlStatement
            │    ├─ selectStatement
            │    │    ├─ selectElements → selectElement → expression
            │    │    ├─ tableSource (FROM)
            │    │    ├─ joinClause  → tableSource + ON expression
            │    │    ├─ whereClause → expression
            │    │    ├─ groupByClause → expressionList
            │    │    ├─ orderByClause → orderByElement
            │    │    └─ limitClause
            │    ├─ insertStatement → columnNameList + VALUES/SELECT
            │    ├─ updateStatement → updateAssignment + WHERE
            │    └─ deleteStatement → WHERE
            ├─ showStatement
            ├─ useStatement
            └─ describeStatement
```

expression 规则的子类型（通过 ANTLR 标签生成不同的 Context 类）：

```
expression
  ├─ columnRefExpression    (columnRef 列引用)
  ├─ functionCallExpression (functionCall 函数调用)
  ├─ comparisonExpression   (a > b, a = b 等)
  ├─ andExpression / orExpression
  ├─ inExpression           (IN 子查询)
  ├─ betweenExpression / likeExpression
  ├─ isNullExpression / isNotNullExpression
  ├─ parenExpression        (括号表达式)
  ├─ literalExpression      (字面量)
  ├─ mulDivModExpression / addSubExpression
  ├─ caseExpr              (CASE 表达式)
  └─ starExpression        (SELECT *)
```

---

## 3. SELECT 语句完整调用栈

以 `SELECT DISTINCT u.name, COUNT(o.id) FROM users u JOIN orders o ON u.id = o.user_id WHERE o.amount > 100 GROUP BY u.name ORDER BY u.name ASC` 为例：

```
visitor.visit(tree)
│
├── visitRoot(RootContext)                                    [BaseVisitor 默认透传]
│   └── visitSqlStatements(SqlStatementsContext)              [BaseVisitor 默认透传]
│       └── visitSqlStatement(SqlStatementContext)            [BaseVisitor 默认透传]
│           └── visitDmlStatement(DmlStatementContext)        [BaseVisitor 默认透传]
│
│               └── ★ visitSelectStatement(SelectStatementContext)  ← L140
│                      ✔ statementType = "SELECT"
│                      ✔ hasDistinct = true
│                      调用 visitChildren(ctx) 继续向下 ↓
```

### 3.1 SELECT 元素分支

```
├── visitSelectElements(...)                                  [BaseVisitor 透传]
│   │
│   ├── ★ visitSelectElement(...)                             ← L266  → 检查别名
│   │   └── ★ visitColumnRefExpression(...)                   ← L250  ✔ columns.add("u.name")
│   │
│   └── ★ visitSelectElement(...)                             ← L266
│       └── visitFunctionCallExpression(...)                   [BaseVisitor 透传]
│           └── ★ visitFunctionCall(...)                       ← L308  ✔ functions.add("COUNT")
│               └── ★ visitColumnRefExpression(...)            ← L250  ✔ columns.add("o.id")
```

### 3.2 FROM / JOIN 分支

```
├── ★ visitTableSource(TableSourceContext)                     ← L218
│      ✔ tables.add("users")
│      ✔ aliases.put("u", "users")
│
├── ★ visitJoinClause(JoinClauseContext)                       ← L237
│      ✔ joinConditions.add("INNER JOIN orders")
│   │
│   ├── ★ visitTableSource(...)                                ✔ tables.add("orders"), aliases.put("o", "orders")
│   │
│   └── visitComparisonExpression (→ ON 条件)                  [BaseVisitor 透传]
│       ├── ★ visitColumnRefExpression → columns.add("u.id")
│       └── ★ visitColumnRefExpression → columns.add("o.user_id")
```

### 3.3 WHERE 分支

```
├── ★ visitWhereClause(WhereClauseContext)                     ← L278
│      ✔ inWhereClause = true                                  ← 设置上下文标记
│   │
│   └── visitComparisonExpression(...)                          [BaseVisitor 透传]
│       ├── ★ visitColumnRefExpression(...)
│       │      ✔ columns.add("o.amount")
│       │      ✔ whereColumns.add("o.amount")                  ← 因为 inWhereClause=true
│       └── visitLiteralExpression(...)                         [值 100]
│
│      ✔ inWhereClause = false                                 ← 还原上下文标记
```

### 3.4 GROUP BY / ORDER BY 分支

```
├── ★ visitGroupByClause(GroupByClauseContext)                  ← L288
│      ✔ groupByColumns.add("u.name")
│
└── visitOrderByClause(...)                                     [BaseVisitor 透传]
    └── ★ visitOrderByElement(OrderByElementContext)             ← L298
           ✔ orderByColumns.add("u.name ASC")
```

---

## 4. INSERT 语句调用栈

以 `INSERT INTO users (name, age) VALUES ('Tom', 25)` 为例：

```
visitor.visit(tree)
│
├── visitRoot → visitSqlStatements → visitSqlStatement → visitDmlStatement  [透传]
│
│   └── ★ visitInsertStatement(InsertStatementContext)          ← L165
│          ✔ statementType = "INSERT"
│          ✔ tables.add("users")
│          ✔ columns.add("name"), columns.add("age")           ← 从 columnNameList 提取
│          调用 visitChildren(ctx) ↓
│
│       └── visitExpressionList(...)                             [BaseVisitor 透传]
│           ├── visitLiteralExpression ("Tom")                   [叶子节点]
│           └── visitLiteralExpression (25)                      [叶子节点]
```

---

## 5. CREATE TABLE 语句调用栈

以 `CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL)` 为例：

```
visitor.visit(tree)
│
├── visitRoot → visitSqlStatements → visitSqlStatement → visitDdlStatement  [透传]
│
│   └── ★ visitCreateTable(CreateTableContext)                  ← L79
│          ✔ statementType = "CREATE_TABLE"
│          ✔ tables.add("users")
│          ✔ columns.add("id"), columns.add("name")            ← 从 columnDefinition 列表提取
│          调用 visitChildren(ctx) ↓
│
│       ├── visitColumnDefinition(...)                           [BaseVisitor 透传]
│       │   ├── visitDataType (INT)                              [叶子节点]
│       │   └── visitColumnConstraint (PRIMARY KEY)              [叶子节点]
│       │
│       └── visitColumnDefinition(...)                           [BaseVisitor 透传]
│           ├── visitDataType (VARCHAR(100))                      [叶子节点]
│           └── visitColumnConstraint (NOT NULL)                  [叶子节点]
```

---

## 6. 已 Override 的 17 个 Visit 方法一览

下表列出了 `MySqlMetadataVisitor` 中所有已重写的 visit 方法及其作用：

### 6.1 DDL 语句级方法（8 个）

| 方法 | 作用 | 行号 | 提取表名 |
|---|---|:---:|:---:|
| `visitCreateDatabase` | `statementType = "CREATE_DATABASE"` | L67 | — |
| `visitDropDatabase` | `statementType = "DROP_DATABASE"` | L73 | — |
| `visitCreateTable` | 设类型 + 提取表名、列定义 | L79 | ✔ |
| `visitDropTable` | 设类型 + 提取表名 | L93 | ✔ |
| `visitAlterTable` | 设类型 + 提取表名 | L102 | ✔ |
| `visitTruncateTable` | 设类型 + 提取表名 | L111 | ✔ |
| `visitCreateIndex` | 设类型 + 提取表名 | L120 | ✔ |
| `visitDropIndex` | 设类型 + 提取表名 | L129 | ✔ |

### 6.2 DML 语句级方法（7 个）

| 方法 | 作用 | 行号 |
|---|---|:---:|
| `visitSelectStatement` | 设类型 + 检测 DISTINCT/UNION/子查询 | L140 |
| `visitInsertStatement` | 设类型 + 提取表名、列名 | L165 |
| `visitUpdateStatement` | 设类型 + 提取表名 | L180 |
| `visitDeleteStatement` | 设类型 + 提取表名 | L189 |
| `visitShowStatement` | `statementType = "SHOW"` | L198 |
| `visitUseStatement` | `statementType = "USE"` | L204 |
| `visitDescribeStatement` | `statementType = "DESCRIBE"` | L210 |

### 6.3 子句/元素级方法（9 个）

| 方法 | 作用 | 行号 |
|---|---|:---:|
| `visitTableSource` | 提取 FROM 表名 + 别名 | L218 |
| `visitJoinClause` | 收集 JOIN 条件 | L237 |
| `visitColumnRefExpression` | 收集列名（+ WHERE 上下文判断） | L250 |
| `visitSelectElement` | 收集 SELECT 列别名 | L266 |
| `visitWhereClause` | 设置/还原 `inWhereClause` 上下文标记 | L278 |
| `visitGroupByClause` | 收集 GROUP BY 列 | L288 |
| `visitOrderByElement` | 收集 ORDER BY 列 + 排序方向 | L298 |
| `visitFunctionCall` | 收集函数名 | L308 |
| `visitUpdateAssignment` | 收集 UPDATE SET 的列 | L318 |

---

## 7. 数据流向总结

整个调用栈的数据流向可以总结为：

> **`visit()` → `accept()` → BaseVisitor 透传 4 层 → 语句级 override 提取类型/表名 → `visitChildren()` → 子句级 override 提取列名/函数/条件 → `visitChildren()` → 直到叶子节点返回**

### 7.1 元数据收集结果

遍历结束后，以下成员变量中将包含完整的元数据：

| 字段 | 类型 | 描述 |
|---|---|---|
| `statementType` | `String` | SQL 语句类型（SELECT/INSERT/UPDATE/DELETE/CREATE_TABLE 等） |
| `tables` | `Set<String>` | 引用到的表名集合 |
| `columns` | `Set<String>` | 引用到的列名集合 |
| `aliases` | `Map<String,String>` | 别名映射 alias → 原始名 |
| `functions` | `List<String>` | 函数调用列表 |
| `whereColumns` | `Set<String>` | WHERE 条件中引用的列 |
| `joinConditions` | `List<String>` | JOIN 条件列表 |
| `orderByColumns` | `List<String>` | ORDER BY 列列表 |
| `groupByColumns` | `List<String>` | GROUP BY 列列表 |
| `hasSubQuery` | `boolean` | 是否包含子查询 |
| `hasDistinct` | `boolean` | 是否包含 DISTINCT |
| `hasUnion` | `boolean` | 是否包含 UNION |

### 7.2 WHERE 上下文标记机制

`MySqlMetadataVisitor` 使用了一个巧妙的上下文标记机制来区分 WHERE 子句中的列和其他位置的列：

```java
visitWhereClause(ctx) {
    inWhereClause = true;       // ① 进入 WHERE 前设置标记
    visitChildren(ctx);          // ② 遍历 WHERE 内的所有子节点
    inWhereClause = false;       // ③ 离开 WHERE 后还原标记
    return null;
}

visitColumnRefExpression(ctx) {
    columns.add(colText);        // 总是添加到全局 columns
    if (inWhereClause) {
        whereColumns.add(colText); // 仅在 WHERE 上下文中额外添加
    }
}
```
