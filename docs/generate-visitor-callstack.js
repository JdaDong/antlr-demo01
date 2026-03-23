const fs = require("fs");
const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  HeadingLevel, AlignmentType, BorderStyle, WidthType, ShadingType,
  LevelFormat, PageNumber, Header, Footer, PageBreak
} = require("docx");

// ===== 颜色常量 =====
const COLOR_PRIMARY = "1B4F72";
const COLOR_SECONDARY = "2E86C1";
const COLOR_ACCENT = "27AE60";
const COLOR_WARN = "E74C3C";
const COLOR_LIGHT_BG = "EBF5FB";
const COLOR_HEADER_BG = "1B4F72";
const COLOR_CODE_BG = "F4F6F7";
const COLOR_WHITE = "FFFFFF";
const COLOR_BLACK = "000000";
const COLOR_GRAY = "666666";
const COLOR_LIGHT_GRAY = "F8F9FA";

// ===== 通用边框 =====
const thinBorder = { style: BorderStyle.SINGLE, size: 1, color: "BDC3C7" };
const borders = { top: thinBorder, bottom: thinBorder, left: thinBorder, right: thinBorder };
const noBorder = { style: BorderStyle.NONE, size: 0 };
const noBorders = { top: noBorder, bottom: noBorder, left: noBorder, right: noBorder };

// ===== 工具函数 =====
function headerCell(text, width) {
  return new TableCell({
    borders,
    width: { size: width, type: WidthType.DXA },
    shading: { fill: COLOR_HEADER_BG, type: ShadingType.CLEAR },
    verticalAlign: "center",
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    children: [new Paragraph({
      alignment: AlignmentType.CENTER,
      children: [new TextRun({ text, bold: true, color: COLOR_WHITE, font: "Arial", size: 20 })]
    })]
  });
}

function dataCell(text, width, opts = {}) {
  const runs = [];
  if (opts.code) {
    runs.push(new TextRun({ text, font: "Courier New", size: 18, color: opts.color || COLOR_BLACK }));
  } else {
    runs.push(new TextRun({ text, font: "Arial", size: 20, color: opts.color || COLOR_BLACK, bold: opts.bold || false }));
  }
  return new TableCell({
    borders,
    width: { size: width, type: WidthType.DXA },
    shading: opts.bg ? { fill: opts.bg, type: ShadingType.CLEAR } : undefined,
    verticalAlign: "center",
    margins: { top: 60, bottom: 60, left: 120, right: 120 },
    children: [new Paragraph({
      alignment: opts.center ? AlignmentType.CENTER : AlignmentType.LEFT,
      children: runs
    })]
  });
}

function codeLine(text, indent = 0) {
  return new Paragraph({
    spacing: { before: 20, after: 20 },
    indent: { left: 240 + indent * 240 },
    children: [new TextRun({ text, font: "Courier New", size: 18, color: COLOR_BLACK })]
  });
}

function codeLineColored(parts, indent = 0) {
  return new Paragraph({
    spacing: { before: 20, after: 20 },
    indent: { left: 240 + indent * 240 },
    children: parts.map(p => new TextRun({ text: p.text, font: "Courier New", size: 18, color: p.color || COLOR_BLACK, bold: p.bold || false }))
  });
}

function treeNode(text, indent, isOverride = false, extraParts = []) {
  const prefix = indent === 0 ? "" : "│   ".repeat(indent - 1) + "├── ";
  const parts = [
    new TextRun({ text: prefix, font: "Courier New", size: 18, color: COLOR_GRAY })
  ];
  if (isOverride) {
    parts.push(new TextRun({ text: "★ ", font: "Arial", size: 18, color: COLOR_WARN }));
  }
  parts.push(new TextRun({
    text: text,
    font: "Courier New", size: 18,
    color: isOverride ? COLOR_WARN : COLOR_BLACK,
    bold: isOverride
  }));
  for (const ep of extraParts) {
    parts.push(new TextRun({ text: ep.text, font: "Courier New", size: 18, color: ep.color || COLOR_ACCENT, bold: ep.bold || false }));
  }
  return new Paragraph({
    spacing: { before: 10, after: 10 },
    indent: { left: 360 },
    children: parts
  });
}

function sectionTitle(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_1,
    spacing: { before: 360, after: 200 },
    children: [new TextRun({ text, font: "Arial", size: 32, bold: true, color: COLOR_PRIMARY })]
  });
}

function subTitle(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_2,
    spacing: { before: 280, after: 160 },
    children: [new TextRun({ text, font: "Arial", size: 26, bold: true, color: COLOR_SECONDARY })]
  });
}

function subSubTitle(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_3,
    spacing: { before: 200, after: 120 },
    children: [new TextRun({ text, font: "Arial", size: 22, bold: true, color: COLOR_SECONDARY })]
  });
}

function bodyText(text) {
  return new Paragraph({
    spacing: { before: 80, after: 80 },
    children: [new TextRun({ text, font: "Arial", size: 20 })]
  });
}

function bodyRichText(parts) {
  return new Paragraph({
    spacing: { before: 80, after: 80 },
    children: parts.map(p => new TextRun({
      text: p.text,
      font: p.code ? "Courier New" : "Arial",
      size: p.code ? 18 : 20,
      color: p.color || COLOR_BLACK,
      bold: p.bold || false,
      italics: p.italic || false
    }))
  });
}

function emptyLine() {
  return new Paragraph({ spacing: { before: 40, after: 40 }, children: [] });
}

function codeBlock(lines) {
  const children = [];
  for (const line of lines) {
    if (typeof line === "string") {
      children.push(codeLine(line));
    } else {
      children.push(codeLineColored(line.parts, line.indent || 0));
    }
  }
  return new Table({
    width: { size: 9360, type: WidthType.DXA },
    columnWidths: [9360],
    rows: [new TableRow({
      children: [new TableCell({
        borders: { top: { style: BorderStyle.SINGLE, size: 1, color: "D5DBDB" }, bottom: { style: BorderStyle.SINGLE, size: 1, color: "D5DBDB" }, left: { style: BorderStyle.SINGLE, size: 4, color: COLOR_SECONDARY }, right: { style: BorderStyle.SINGLE, size: 1, color: "D5DBDB" } },
        width: { size: 9360, type: WidthType.DXA },
        shading: { fill: COLOR_CODE_BG, type: ShadingType.CLEAR },
        margins: { top: 120, bottom: 120, left: 200, right: 200 },
        children
      })]
    })]
  });
}

function highlightBox(text, color) {
  return new Table({
    width: { size: 9360, type: WidthType.DXA },
    columnWidths: [9360],
    rows: [new TableRow({
      children: [new TableCell({
        borders: { top: noBorder, bottom: noBorder, left: { style: BorderStyle.SINGLE, size: 6, color }, right: noBorder },
        width: { size: 9360, type: WidthType.DXA },
        shading: { fill: COLOR_LIGHT_BG, type: ShadingType.CLEAR },
        margins: { top: 100, bottom: 100, left: 200, right: 200 },
        children: [new Paragraph({ children: [new TextRun({ text, font: "Arial", size: 20, color: COLOR_PRIMARY })] })]
      })]
    })]
  });
}

// ===== 构建文档 =====
const doc = new Document({
  styles: {
    default: { document: { run: { font: "Arial", size: 20 } } },
    paragraphStyles: [
      { id: "Heading1", name: "Heading 1", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 32, bold: true, font: "Arial", color: COLOR_PRIMARY },
        paragraph: { spacing: { before: 360, after: 200 }, outlineLevel: 0 } },
      { id: "Heading2", name: "Heading 2", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 26, bold: true, font: "Arial", color: COLOR_SECONDARY },
        paragraph: { spacing: { before: 280, after: 160 }, outlineLevel: 1 } },
      { id: "Heading3", name: "Heading 3", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 22, bold: true, font: "Arial", color: COLOR_SECONDARY },
        paragraph: { spacing: { before: 200, after: 120 }, outlineLevel: 2 } },
    ]
  },
  numbering: {
    config: [{
      reference: "bullets",
      levels: [
        { level: 0, format: LevelFormat.BULLET, text: "\u2022", alignment: AlignmentType.LEFT, style: { paragraph: { indent: { left: 720, hanging: 360 } } } },
        { level: 1, format: LevelFormat.BULLET, text: "\u25E6", alignment: AlignmentType.LEFT, style: { paragraph: { indent: { left: 1440, hanging: 360 } } } },
      ]
    }]
  },
  sections: [{
    properties: {
      page: {
        size: { width: 12240, height: 15840 },
        margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 }
      }
    },
    headers: {
      default: new Header({
        children: [new Paragraph({
          alignment: AlignmentType.RIGHT,
          children: [new TextRun({ text: "MySqlMetadataVisitor Visit \u56DE\u8C03\u65B9\u6CD5\u8C03\u7528\u6808", font: "Arial", size: 16, color: COLOR_GRAY, italics: true })]
        })]
      })
    },
    footers: {
      default: new Footer({
        children: [new Paragraph({
          alignment: AlignmentType.CENTER,
          children: [
            new TextRun({ text: "Page ", font: "Arial", size: 16, color: COLOR_GRAY }),
            new TextRun({ children: [PageNumber.CURRENT], font: "Arial", size: 16, color: COLOR_GRAY })
          ]
        })]
      })
    },
    children: [
      // ===== 封面标题 =====
      new Paragraph({ spacing: { before: 2400 }, children: [] }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { after: 200 },
        children: [new TextRun({ text: "MySqlMetadataVisitor", font: "Arial", size: 48, bold: true, color: COLOR_PRIMARY })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { after: 120 },
        children: [new TextRun({ text: "Visit \u56DE\u8C03\u65B9\u6CD5\u8C03\u7528\u6808\u8BE6\u89E3", font: "Arial", size: 36, bold: true, color: COLOR_SECONDARY })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { after: 600 },
        children: [new TextRun({ text: "ANTLR4 Visitor \u6A21\u5F0F\u6DF1\u5EA6\u89E3\u6790", font: "Arial", size: 24, color: COLOR_GRAY })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        children: [new TextRun({ text: "antlr-demo01 \u9879\u76EE\u6280\u672F\u6587\u6863", font: "Arial", size: 20, color: COLOR_GRAY })]
      }),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第一章 =====
      sectionTitle("1. ANTLR Visitor \u6838\u5FC3\u673A\u5236"),
      bodyText("ANTLR4 \u7684 Visitor \u6A21\u5F0F\u662F\u4E00\u79CD\u57FA\u4E8E\u8BBF\u95EE\u8005\u8BBE\u8BA1\u6A21\u5F0F\u7684\u8BED\u6CD5\u6811\u904D\u5386\u65B9\u6CD5\u3002\u4E0E Listener \u6A21\u5F0F\u4E0D\u540C\uFF0CVisitor \u4E0D\u4F1A\u81EA\u52A8\u904D\u5386\u6240\u6709\u5B50\u8282\u70B9\uFF0C\u9700\u8981\u5F00\u53D1\u8005\u663E\u5F0F\u8C03\u7528 visitChildren(ctx) \u6765\u63A7\u5236\u904D\u5386\u6D41\u7A0B\u3002"),
      emptyLine(),
      subTitle("1.1 \u6838\u5FC3\u8C03\u7528\u94FE\u8DEF"),
      bodyText("\u4ECE visitor.visit(tree) \u5165\u53E3\u5F00\u59CB\uFF0CANTLR \u7684\u904D\u5386\u8FC7\u7A0B\u5982\u4E0B\uFF1A"),
      emptyLine(),
      codeBlock([
        "AbstractParseTreeVisitor.visit(tree)       \u2190 \u5165\u53E3",
        "  \u2514\u2500\u2500 tree.accept(this)                    \u2190 ParseTree \u8282\u70B9\u8C03\u7528 accept",
        "        \u2514\u2500\u2500 visitor.visitXxx(this)          \u2190 \u5206\u53D1\u5230\u5177\u4F53\u7684 visitXxx \u65B9\u6CD5",
        "              \u2514\u2500\u2500 visitChildren(ctx)        \u2190 \u624B\u52A8\u9012\u5F52\u904D\u5386\u6240\u6709\u5B50\u8282\u70B9",
        "                    \u2514\u2500\u2500 \u5BF9\u6BCF\u4E2A\u5B50\u8282\u70B9\u91CD\u590D child.accept(this)",
      ]),
      emptyLine(),

      subTitle("1.2 \u5173\u952E\u539F\u7406"),
      highlightBox("ANTLR \u7684 Visitor \u6A21\u5F0F\u4E0D\u4F1A\u81EA\u52A8\u9012\u5F52\uFF0C\u9700\u8981\u6BCF\u4E2A visitXxx \u65B9\u6CD5\u672B\u5C3E\u8C03\u7528 return visitChildren(ctx) \u624D\u80FD\u7EE7\u7EED\u5411\u4E0B\u904D\u5386\u3002", COLOR_SECONDARY),
      emptyLine(),
      bodyRichText([
        { text: "MySqlParserBaseVisitor", code: true, color: COLOR_SECONDARY },
        { text: " \u4E2D\u6240\u6709\u65B9\u6CD5\u7684\u9ED8\u8BA4\u5B9E\u73B0\u90FD\u662F " },
        { text: "return visitChildren(ctx)", code: true, color: COLOR_ACCENT },
        { text: "\uFF08\u900F\u4F20\u904D\u5386\uFF09\u3002" }
      ]),
      bodyRichText([
        { text: "MySqlMetadataVisitor", code: true, color: COLOR_WARN },
        { text: " \u53EA override \u4E86\u5176\u4E2D " },
        { text: "17 \u4E2A", bold: true, color: COLOR_WARN },
        { text: " \u65B9\u6CD5\u6765\u63D0\u53D6\u5143\u6570\u636E\uFF0C\u672A override \u7684\u4E2D\u95F4\u8282\u70B9\u7531 BaseVisitor \u81EA\u52A8\u900F\u4F20\u3002" }
      ]),
      emptyLine(),

      subTitle("1.3 \u4E09\u5C42\u8C03\u7528\u6808\u6A21\u578B"),
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [1200, 2800, 2800, 2560],
        rows: [
          new TableRow({ children: [
            headerCell("\u5C42\u7EA7", 1200),
            headerCell("\u8282\u70B9\u7C7B\u578B", 2800),
            headerCell("\u884C\u4E3A", 2800),
            headerCell("\u793A\u4F8B", 2560),
          ]}),
          new TableRow({ children: [
            dataCell("\u7B2C 1 \u5C42", 1200, { center: true, bold: true }),
            dataCell("root \u2192 sqlStatements \u2192 sqlStatement \u2192 ddl/dmlStatement", 2800, { code: true }),
            dataCell("BaseVisitor \u9ED8\u8BA4 visitChildren \u900F\u4F20\uFF0C\u4E0D\u505A\u4EFB\u4F55\u4E8B", 2800),
            dataCell("4 \u4E2A\u8282\u70B9\u81EA\u52A8\u7A7F\u8D8A", 2560),
          ]}),
          new TableRow({ children: [
            dataCell("\u7B2C 2 \u5C42", 1200, { center: true, bold: true, bg: COLOR_LIGHT_BG }),
            dataCell("selectStatement, insertStatement, createTable \u7B49", 2800, { code: true, bg: COLOR_LIGHT_BG }),
            dataCell("\u2605 \u5DF2 override\uFF1A\u8BBE\u7F6E statementType\uFF0C\u63D0\u53D6\u8868\u540D/\u5217\u540D", 2800, { color: COLOR_WARN, bg: COLOR_LIGHT_BG }),
            dataCell("\u5171 14 \u4E2A override \u65B9\u6CD5", 2560, { bg: COLOR_LIGHT_BG }),
          ]}),
          new TableRow({ children: [
            dataCell("\u7B2C 3 \u5C42", 1200, { center: true, bold: true }),
            dataCell("tableSource, joinClause, whereClause, columnRefExpression \u7B49", 2800, { code: true }),
            dataCell("\u2605 \u5DF2 override\uFF1A\u6536\u96C6\u8868\u540D\u3001\u5217\u540D\u3001\u522B\u540D\u3001\u51FD\u6570\u3001JOIN \u6761\u4EF6\u7B49", 2800, { color: COLOR_WARN }),
            dataCell("\u5171 7 \u4E2A override \u65B9\u6CD5", 2560),
          ]}),
        ]
      }),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第二章 =====
      sectionTitle("2. \u8BED\u6CD5\u89C4\u5219\u5C42\u6B21\u7ED3\u6784"),
      bodyText("\u4E0B\u9762\u662F MySqlParser.g4 \u5B9A\u4E49\u7684\u8BED\u6CD5\u89C4\u5219\u6811\u7ED3\u6784\uFF0C\u5C55\u793A\u4E86\u4ECE root \u5230\u53F6\u5B50\u8282\u70B9\u7684\u5B8C\u6574\u5C42\u6B21\uFF1A"),
      emptyLine(),
      codeBlock([
        "root",
        "  \u2514\u2500 sqlStatements",
        "       \u2514\u2500 sqlStatement",
        "            \u251C\u2500 ddlStatement",
        "            \u2502    \u251C\u2500 createDatabase     \u251C\u2500 createTable",
        "            \u2502    \u251C\u2500 dropDatabase       \u251C\u2500 dropTable",
        "            \u2502    \u251C\u2500 alterTable         \u251C\u2500 truncateTable",
        "            \u2502    \u251C\u2500 createIndex        \u2514\u2500 dropIndex",
        "            \u251C\u2500 dmlStatement",
        "            \u2502    \u251C\u2500 selectStatement",
        "            \u2502    \u2502    \u251C\u2500 selectElements \u2192 selectElement \u2192 expression",
        "            \u2502    \u2502    \u251C\u2500 tableSource (FROM)",
        "            \u2502    \u2502    \u251C\u2500 joinClause  \u2192 tableSource + ON expression",
        "            \u2502    \u2502    \u251C\u2500 whereClause \u2192 expression",
        "            \u2502    \u2502    \u251C\u2500 groupByClause \u2192 expressionList",
        "            \u2502    \u2502    \u251C\u2500 orderByClause \u2192 orderByElement",
        "            \u2502    \u2502    \u2514\u2500 limitClause",
        "            \u2502    \u251C\u2500 insertStatement \u2192 columnNameList + VALUES/SELECT",
        "            \u2502    \u251C\u2500 updateStatement \u2192 updateAssignment + WHERE",
        "            \u2502    \u2514\u2500 deleteStatement \u2192 WHERE",
        "            \u251C\u2500 showStatement",
        "            \u251C\u2500 useStatement",
        "            \u2514\u2500 describeStatement",
      ]),
      emptyLine(),

      bodyText("expression \u89C4\u5219\u7684\u5B50\u7C7B\u578B\uFF08\u901A\u8FC7 ANTLR \u6807\u7B7E\u751F\u6210\u4E0D\u540C\u7684 Context \u7C7B\uFF09\uFF1A"),
      codeBlock([
        "expression",
        "  \u251C\u2500 columnRefExpression    (columnRef \u5217\u5F15\u7528)",
        "  \u251C\u2500 functionCallExpression (functionCall \u51FD\u6570\u8C03\u7528)",
        "  \u251C\u2500 comparisonExpression   (a > b, a = b \u7B49)",
        "  \u251C\u2500 andExpression / orExpression",
        "  \u251C\u2500 inExpression           (IN \u5B50\u67E5\u8BE2)",
        "  \u251C\u2500 betweenExpression / likeExpression",
        "  \u251C\u2500 isNullExpression / isNotNullExpression",
        "  \u251C\u2500 parenExpression        (\u62EC\u53F7\u8868\u8FBE\u5F0F)",
        "  \u251C\u2500 literalExpression      (\u5B57\u9762\u91CF)",
        "  \u251C\u2500 mulDivModExpression / addSubExpression",
        "  \u251C\u2500 caseExpr              (CASE \u8868\u8FBE\u5F0F)",
        "  \u2514\u2500 starExpression        (SELECT *)",
      ]),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第三章 =====
      sectionTitle("3. SELECT \u8BED\u53E5\u5B8C\u6574\u8C03\u7528\u6808"),
      bodyRichText([
        { text: "\u4EE5 " },
        { text: "SELECT DISTINCT u.name, COUNT(o.id) FROM users u JOIN orders o ON u.id = o.user_id WHERE o.amount > 100 GROUP BY u.name ORDER BY u.name ASC", code: true },
        { text: " \u4E3A\u4F8B\uFF1A" }
      ]),
      emptyLine(),

      // 调用栈树
      treeNode("visitor.visit(tree)", 0),
      treeNode("visitRoot(RootContext)", 1, false, [{ text: "  [BaseVisitor \u9ED8\u8BA4\u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitSqlStatements(SqlStatementsContext)", 2, false, [{ text: "  [BaseVisitor \u9ED8\u8BA4\u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitSqlStatement(SqlStatementContext)", 3, false, [{ text: "  [BaseVisitor \u9ED8\u8BA4\u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitDmlStatement(DmlStatementContext)", 4, false, [{ text: "  [BaseVisitor \u9ED8\u8BA4\u900F\u4F20]", color: COLOR_GRAY }]),
      emptyLine(),
      treeNode("visitSelectStatement(SelectStatementContext)", 5, true, [{ text: "  \u2190 L140", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "statementType = \"SELECT\"", code: true },
        { text: "  |  ", color: COLOR_GRAY },
        { text: "\u2714 ", color: COLOR_ACCENT },
        { text: "hasDistinct = true", code: true },
      ]),
      emptyLine(),

      subSubTitle("3.1 SELECT \u5143\u7D20\u5206\u652F"),
      treeNode("visitSelectElements(...)", 1, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitSelectElement(...)", 2, true, [{ text: "  \u2190 L266  \u2192 \u68C0\u67E5\u522B\u540D", color: COLOR_GRAY }]),
      treeNode("visitColumnRefExpression(...)", 3, true, [{ text: "  \u2190 L250  \u2714 columns.add(\"u.name\")", color: COLOR_ACCENT }]),
      treeNode("visitSelectElement(...)", 2, true, [{ text: "  \u2190 L266", color: COLOR_GRAY }]),
      treeNode("visitFunctionCallExpression(...)", 3, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitFunctionCall(...)", 4, true, [{ text: "  \u2190 L308  \u2714 functions.add(\"COUNT\")", color: COLOR_ACCENT }]),
      treeNode("visitColumnRefExpression(...)", 5, true, [{ text: "  \u2190 L250  \u2714 columns.add(\"o.id\")", color: COLOR_ACCENT }]),
      emptyLine(),

      subSubTitle("3.2 FROM / JOIN \u5206\u652F"),
      treeNode("visitTableSource(TableSourceContext)", 1, true, [{ text: "  \u2190 L218", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "tables.add(\"users\")", code: true },
        { text: "  |  ", color: COLOR_GRAY },
        { text: "\u2714 ", color: COLOR_ACCENT },
        { text: "aliases.put(\"u\", \"users\")", code: true },
      ]),
      treeNode("visitJoinClause(JoinClauseContext)", 1, true, [{ text: "  \u2190 L237", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "joinConditions.add(\"INNER JOIN orders\")", code: true },
      ]),
      treeNode("visitTableSource(...)", 2, true, [{ text: "  \u2714 tables.add(\"orders\"), aliases.put(\"o\", \"orders\")", color: COLOR_ACCENT }]),
      treeNode("visitComparisonExpression (\u2192 ON \u6761\u4EF6)", 2, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitColumnRefExpression \u2192 columns.add(\"u.id\")", 3, true),
      treeNode("visitColumnRefExpression \u2192 columns.add(\"o.user_id\")", 3, true),
      emptyLine(),

      subSubTitle("3.3 WHERE \u5206\u652F"),
      treeNode("visitWhereClause(WhereClauseContext)", 1, true, [{ text: "  \u2190 L278", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "inWhereClause = true", code: true, color: COLOR_WARN },
        { text: "  \u2190 \u8BBE\u7F6E\u4E0A\u4E0B\u6587\u6807\u8BB0" }
      ]),
      treeNode("visitComparisonExpression(...)", 2, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitColumnRefExpression(...)", 3, true),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "columns.add(\"o.amount\")", code: true },
        { text: "  |  ", color: COLOR_GRAY },
        { text: "\u2714 ", color: COLOR_ACCENT },
        { text: "whereColumns.add(\"o.amount\")", code: true, color: COLOR_WARN },
        { text: "  \u2190 \u56E0\u4E3A inWhereClause=true", color: COLOR_GRAY },
      ]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "inWhereClause = false", code: true },
        { text: "  \u2190 \u8FD8\u539F\u4E0A\u4E0B\u6587\u6807\u8BB0" }
      ]),
      emptyLine(),

      subSubTitle("3.4 GROUP BY / ORDER BY \u5206\u652F"),
      treeNode("visitGroupByClause(GroupByClauseContext)", 1, true, [{ text: "  \u2190 L288", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "        \u2714 ", color: COLOR_ACCENT },
        { text: "groupByColumns.add(\"u.name\")", code: true },
      ]),
      treeNode("visitOrderByClause(...)", 1, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitOrderByElement(OrderByElementContext)", 2, true, [{ text: "  \u2190 L298", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "orderByColumns.add(\"u.name ASC\")", code: true },
      ]),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第四章 =====
      sectionTitle("4. INSERT \u8BED\u53E5\u8C03\u7528\u6808"),
      bodyRichText([
        { text: "\u4EE5 " },
        { text: "INSERT INTO users (name, age) VALUES ('Tom', 25)", code: true },
        { text: " \u4E3A\u4F8B\uFF1A" }
      ]),
      emptyLine(),
      treeNode("visitor.visit(tree)", 0),
      treeNode("visitRoot \u2192 visitSqlStatements \u2192 visitSqlStatement \u2192 visitDmlStatement", 1, false, [{ text: "  [\u900F\u4F20]", color: COLOR_GRAY }]),
      emptyLine(),
      treeNode("visitInsertStatement(InsertStatementContext)", 2, true, [{ text: "  \u2190 L165", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "statementType = \"INSERT\"", code: true },
      ]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "tables.add(\"users\")", code: true },
      ]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "columns.add(\"name\"), columns.add(\"age\")", code: true },
        { text: "  \u2190 \u4ECE columnNameList \u63D0\u53D6", color: COLOR_GRAY },
      ]),
      treeNode("visitExpressionList(...)", 3, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitLiteralExpression (\"Tom\")", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      treeNode("visitLiteralExpression (25)", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      emptyLine(),

      // ===== 第五章 =====
      sectionTitle("5. CREATE TABLE \u8BED\u53E5\u8C03\u7528\u6808"),
      bodyRichText([
        { text: "\u4EE5 " },
        { text: "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL)", code: true },
        { text: " \u4E3A\u4F8B\uFF1A" }
      ]),
      emptyLine(),
      treeNode("visitor.visit(tree)", 0),
      treeNode("visitRoot \u2192 visitSqlStatements \u2192 visitSqlStatement \u2192 visitDdlStatement", 1, false, [{ text: "  [\u900F\u4F20]", color: COLOR_GRAY }]),
      emptyLine(),
      treeNode("visitCreateTable(CreateTableContext)", 2, true, [{ text: "  \u2190 L79", color: COLOR_GRAY }]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "statementType = \"CREATE_TABLE\"", code: true },
      ]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "tables.add(\"users\")", code: true },
      ]),
      bodyRichText([
        { text: "              \u2714 ", color: COLOR_ACCENT },
        { text: "columns.add(\"id\"), columns.add(\"name\")", code: true },
        { text: "  \u2190 \u4ECE columnDefinition \u5217\u8868\u63D0\u53D6", color: COLOR_GRAY },
      ]),
      treeNode("visitColumnDefinition(...)", 3, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitDataType (INT)", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      treeNode("visitColumnConstraint (PRIMARY KEY)", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      treeNode("visitColumnDefinition(...)", 3, false, [{ text: "  [BaseVisitor \u900F\u4F20]", color: COLOR_GRAY }]),
      treeNode("visitDataType (VARCHAR(100))", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      treeNode("visitColumnConstraint (NOT NULL)", 4, false, [{ text: "  [\u53F6\u5B50\u8282\u70B9]", color: COLOR_GRAY }]),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第六章 =====
      sectionTitle("6. \u5DF2 Override \u7684 17 \u4E2A Visit \u65B9\u6CD5\u4E00\u89C8"),
      bodyText("\u4E0B\u8868\u5217\u51FA\u4E86 MySqlMetadataVisitor \u4E2D\u6240\u6709\u5DF2\u91CD\u5199\u7684 visit \u65B9\u6CD5\u53CA\u5176\u4F5C\u7528\uFF1A"),
      emptyLine(),

      // DDL 方法表
      subTitle("6.1 DDL \u8BED\u53E5\u7EA7\u65B9\u6CD5\uFF088 \u4E2A\uFF09"),
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3200, 3800, 1180, 1180],
        rows: [
          new TableRow({ children: [
            headerCell("\u65B9\u6CD5", 3200), headerCell("\u4F5C\u7528", 3800), headerCell("\u884C\u53F7", 1180), headerCell("\u8868\u540D", 1180),
          ]}),
          ...[
            ["visitCreateDatabase", "statementType = \"CREATE_DATABASE\"", "L67", "\u2014"],
            ["visitDropDatabase", "statementType = \"DROP_DATABASE\"", "L73", "\u2014"],
            ["visitCreateTable", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D\u3001\u5217\u5B9A\u4E49", "L79", "\u2714"],
            ["visitDropTable", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L93", "\u2714"],
            ["visitAlterTable", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L102", "\u2714"],
            ["visitTruncateTable", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L111", "\u2714"],
            ["visitCreateIndex", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L120", "\u2714"],
            ["visitDropIndex", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L129", "\u2714"],
          ].map((row, i) => new TableRow({ children: [
            dataCell(row[0], 3200, { code: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[1], 3800, { bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[2], 1180, { center: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[3], 1180, { center: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
          ]}))
        ]
      }),
      emptyLine(),

      // DML 方法表
      subTitle("6.2 DML \u8BED\u53E5\u7EA7\u65B9\u6CD5\uFF087 \u4E2A\uFF09"),
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3200, 4980, 1180],
        rows: [
          new TableRow({ children: [
            headerCell("\u65B9\u6CD5", 3200), headerCell("\u4F5C\u7528", 4980), headerCell("\u884C\u53F7", 1180),
          ]}),
          ...[
            ["visitSelectStatement", "\u8BBE\u7C7B\u578B + \u68C0\u6D4B DISTINCT/UNION/\u5B50\u67E5\u8BE2", "L140"],
            ["visitInsertStatement", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D\u3001\u5217\u540D", "L165"],
            ["visitUpdateStatement", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L180"],
            ["visitDeleteStatement", "\u8BBE\u7C7B\u578B + \u63D0\u53D6\u8868\u540D", "L189"],
            ["visitShowStatement", "statementType = \"SHOW\"", "L198"],
            ["visitUseStatement", "statementType = \"USE\"", "L204"],
            ["visitDescribeStatement", "statementType = \"DESCRIBE\"", "L210"],
          ].map((row, i) => new TableRow({ children: [
            dataCell(row[0], 3200, { code: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[1], 4980, { bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[2], 1180, { center: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
          ]}))
        ]
      }),
      emptyLine(),

      // 子句级方法表
      subTitle("6.3 \u5B50\u53E5/\u5143\u7D20\u7EA7\u65B9\u6CD5\uFF087 \u4E2A\uFF09"),
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3200, 4980, 1180],
        rows: [
          new TableRow({ children: [
            headerCell("\u65B9\u6CD5", 3200), headerCell("\u4F5C\u7528", 4980), headerCell("\u884C\u53F7", 1180),
          ]}),
          ...[
            ["visitTableSource", "\u63D0\u53D6 FROM \u8868\u540D + \u522B\u540D", "L218"],
            ["visitJoinClause", "\u6536\u96C6 JOIN \u6761\u4EF6", "L237"],
            ["visitColumnRefExpression", "\u6536\u96C6\u5217\u540D\uFF08+ WHERE \u4E0A\u4E0B\u6587\u5224\u65AD\uFF09", "L250"],
            ["visitSelectElement", "\u6536\u96C6 SELECT \u5217\u522B\u540D", "L266"],
            ["visitWhereClause", "\u8BBE\u7F6E/\u8FD8\u539F inWhereClause \u4E0A\u4E0B\u6587\u6807\u8BB0", "L278"],
            ["visitGroupByClause", "\u6536\u96C6 GROUP BY \u5217", "L288"],
            ["visitOrderByElement", "\u6536\u96C6 ORDER BY \u5217 + \u6392\u5E8F\u65B9\u5411", "L298"],
            ["visitFunctionCall", "\u6536\u96C6\u51FD\u6570\u540D", "L308"],
            ["visitUpdateAssignment", "\u6536\u96C6 UPDATE SET \u7684\u5217", "L318"],
          ].map((row, i) => new TableRow({ children: [
            dataCell(row[0], 3200, { code: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[1], 4980, { bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[2], 1180, { center: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
          ]}))
        ]
      }),
      new Paragraph({ children: [new PageBreak()] }),

      // ===== 第七章 =====
      sectionTitle("7. \u6570\u636E\u6D41\u5411\u603B\u7ED3"),
      bodyText("\u6574\u4E2A\u8C03\u7528\u6808\u7684\u6570\u636E\u6D41\u5411\u53EF\u4EE5\u603B\u7ED3\u4E3A\uFF1A"),
      emptyLine(),
      highlightBox("visit() \u2192 accept() \u2192 BaseVisitor \u900F\u4F20 4 \u5C42 \u2192 \u8BED\u53E5\u7EA7 override \u63D0\u53D6\u7C7B\u578B/\u8868\u540D \u2192 visitChildren() \u2192 \u5B50\u53E5\u7EA7 override \u63D0\u53D6\u5217\u540D/\u51FD\u6570/\u6761\u4EF6 \u2192 visitChildren() \u2192 \u76F4\u5230\u53F6\u5B50\u8282\u70B9\u8FD4\u56DE", COLOR_PRIMARY),
      emptyLine(),

      subTitle("7.1 \u5143\u6570\u636E\u6536\u96C6\u7ED3\u679C"),
      bodyText("\u904D\u5386\u7ED3\u675F\u540E\uFF0C\u4EE5\u4E0B\u6210\u5458\u53D8\u91CF\u4E2D\u5C06\u5305\u542B\u5B8C\u6574\u7684\u5143\u6570\u636E\uFF1A"),
      emptyLine(),
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [2400, 2000, 4960],
        rows: [
          new TableRow({ children: [
            headerCell("\u5B57\u6BB5", 2400), headerCell("\u7C7B\u578B", 2000), headerCell("\u63CF\u8FF0", 4960),
          ]}),
          ...[
            ["statementType", "String", "SQL \u8BED\u53E5\u7C7B\u578B\uFF08SELECT/INSERT/UPDATE/DELETE/CREATE_TABLE \u7B49\uFF09"],
            ["tables", "Set<String>", "\u5F15\u7528\u5230\u7684\u8868\u540D\u96C6\u5408"],
            ["columns", "Set<String>", "\u5F15\u7528\u5230\u7684\u5217\u540D\u96C6\u5408"],
            ["aliases", "Map<String,String>", "\u522B\u540D\u6620\u5C04 alias \u2192 \u539F\u59CB\u540D"],
            ["functions", "List<String>", "\u51FD\u6570\u8C03\u7528\u5217\u8868"],
            ["whereColumns", "Set<String>", "WHERE \u6761\u4EF6\u4E2D\u5F15\u7528\u7684\u5217"],
            ["joinConditions", "List<String>", "JOIN \u6761\u4EF6\u5217\u8868"],
            ["orderByColumns", "List<String>", "ORDER BY \u5217\u5217\u8868"],
            ["groupByColumns", "List<String>", "GROUP BY \u5217\u5217\u8868"],
            ["hasSubQuery", "boolean", "\u662F\u5426\u5305\u542B\u5B50\u67E5\u8BE2"],
            ["hasDistinct", "boolean", "\u662F\u5426\u5305\u542B DISTINCT"],
            ["hasUnion", "boolean", "\u662F\u5426\u5305\u542B UNION"],
          ].map((row, i) => new TableRow({ children: [
            dataCell(row[0], 2400, { code: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[1], 2000, { code: true, bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
            dataCell(row[2], 4960, { bg: i % 2 === 0 ? COLOR_LIGHT_GRAY : undefined }),
          ]}))
        ]
      }),
      emptyLine(),

      subTitle("7.2 WHERE \u4E0A\u4E0B\u6587\u6807\u8BB0\u673A\u5236"),
      bodyText("MySqlMetadataVisitor \u4F7F\u7528\u4E86\u4E00\u4E2A\u5DE7\u5999\u7684\u4E0A\u4E0B\u6587\u6807\u8BB0\u673A\u5236\u6765\u533A\u5206 WHERE \u5B50\u53E5\u4E2D\u7684\u5217\u548C\u5176\u4ED6\u4F4D\u7F6E\u7684\u5217\uFF1A"),
      emptyLine(),
      codeBlock([
        "visitWhereClause(ctx) {",
        "    inWhereClause = true;       // \u2460 \u8FDB\u5165 WHERE \u524D\u8BBE\u7F6E\u6807\u8BB0",
        "    visitChildren(ctx);          // \u2461 \u904D\u5386 WHERE \u5185\u7684\u6240\u6709\u5B50\u8282\u70B9",
        "    inWhereClause = false;       // \u2462 \u79BB\u5F00 WHERE \u540E\u8FD8\u539F\u6807\u8BB0",
        "    return null;",
        "}",
        "",
        "visitColumnRefExpression(ctx) {",
        "    columns.add(colText);        // \u603B\u662F\u6DFB\u52A0\u5230\u5168\u5C40 columns",
        "    if (inWhereClause) {",
        "        whereColumns.add(colText); // \u4EC5\u5728 WHERE \u4E0A\u4E0B\u6587\u4E2D\u989D\u5916\u6DFB\u52A0",
        "    }",
        "}",
      ]),
    ]
  }]
});

// 生成文件
Packer.toBuffer(doc).then(buffer => {
  const outputPath = process.argv[2] || "docs/MySqlMetadataVisitor-Visit回调方法调用栈.docx";
  fs.writeFileSync(outputPath, buffer);
  console.log("✅ 文档已生成: " + outputPath);
});
