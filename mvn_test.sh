#!/bin/bash
#
# 测试脚本 — ANTLR SQL Parser
#
# 用法：
#   ./mvn_test.sh                              # 运行全部测试
#   ./mvn_test.sh mysql                        # 运行 MySQL 相关测试
#   ./mvn_test.sh pg                           # 运行 PostgreSQL 相关测试
#   ./mvn_test.sh spark                        # 运行 SparkSQL 相关测试
#   ./mvn_test.sh class MySqlFormatterTest     # 运行指定测试类
#   ./mvn_test.sh method "MySqlFormatterTest#testSimpleSelect"  # 运行指定方法
#   ./mvn_test.sh quick                        # 快速测试（跳过慢速测试）
#

set -e

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$PROJECT_DIR"

# ============ 颜色输出 ============
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m'

info()    { echo -e "${BLUE}[INFO]${NC} $1"; }
success() { echo -e "${GREEN}[OK]${NC} $1"; }
warn()    { echo -e "${YELLOW}[WARN]${NC} $1"; }
error()   { echo -e "${RED}[ERROR]${NC} $1"; }
step()    { echo -e "${CYAN}[STEP]${NC} $1"; }

# ============ 测试类映射 ============
MYSQL_TESTS="MySqlMetadataVisitorTest,MySqlAuditListenerTest,MySqlFormatterTest"
PG_TESTS="PostgreSqlMetadataVisitorTest,PostgreSqlAuditListenerTest"
SPARK_TESTS="SparkSqlFormatterTest"
COMMON_TESTS="ParseTreeVisualizerTest,SqlDiffEngineTest"

# ============ 计时 ============
SECONDS=0

# ============ 运行测试 ============
run_tests() {
    local label="$1"
    shift
    step "运行测试: ${label}..."
    echo ""

    if mvn test "$@" 2>&1 | tee /dev/stderr | grep -q "BUILD SUCCESS"; then
        echo ""
        success "${label} — 全部通过 ✅"
    else
        echo ""
        error "${label} — 存在失败 ❌"
        exit 1
    fi
}

run_tests_quiet() {
    local label="$1"
    shift
    step "运行测试: ${label}..."

    local output
    output=$(mvn test "$@" 2>&1)
    local exit_code=$?

    # 提取测试汇总行
    local summary=$(echo "$output" | grep -E "^\\[INFO\\] Tests run:|^\\[INFO\\] Results:" -A1 | tail -1)
    local total=$(echo "$output" | grep "^\\[INFO\\] Tests run:" | tail -1)

    if [ $exit_code -eq 0 ]; then
        echo "$total"
        success "${label} — 全部通过 ✅"
    else
        echo "$output" | grep -E "^\[ERROR\]|^\[INFO\] Tests run:" | tail -20
        echo ""
        error "${label} — 存在失败 ❌"
        echo ""
        info "运行 '$0 verbose' 查看完整输出"
        exit 1
    fi
}

# ============ 主流程 ============
echo ""
echo "╔══════════════════════════════════════════╗"
echo "║     ANTLR SQL Parser — 测试脚本          ║"
echo "╚══════════════════════════════════════════╝"
echo ""

target="${1:-all}"

case "$target" in
    all|a)
        run_tests_quiet "全部测试 (152个)"
        ;;
    verbose|v)
        run_tests "全部测试 (verbose)"
        ;;
    mysql|my)
        run_tests_quiet "MySQL 测试" -Dtest="${MYSQL_TESTS}"
        ;;
    pg|postgresql|postgres)
        run_tests_quiet "PostgreSQL 测试" -Dtest="${PG_TESTS}"
        ;;
    spark|sparksql)
        run_tests_quiet "SparkSQL 测试" -Dtest="${SPARK_TESTS}"
        ;;
    common|base)
        run_tests_quiet "通用组件测试" -Dtest="${COMMON_TESTS}"
        ;;
    class|c)
        if [ -z "$2" ]; then
            error "请指定测试类名, 例如: $0 class MySqlFormatterTest"
            exit 1
        fi
        run_tests_quiet "测试类: $2" -Dtest="$2"
        ;;
    method|m)
        if [ -z "$2" ]; then
            error "请指定测试方法, 例如: $0 method 'MySqlFormatterTest#testSimpleSelect'"
            exit 1
        fi
        run_tests "测试方法: $2" -Dtest="$2"
        ;;
    quick|q)
        info "快速模式: 每个方言只跑核心测试"
        run_tests_quiet "快速测试" -Dtest="${MYSQL_TESTS%%,*},${COMMON_TESTS%%,*}"
        ;;
    list|ls)
        echo "可用的测试类:"
        echo ""
        echo "  📦 MySQL:"
        echo "     • MySqlMetadataVisitorTest"
        echo "     • MySqlAuditListenerTest"
        echo "     • MySqlFormatterTest"
        echo ""
        echo "  📦 PostgreSQL:"
        echo "     • PostgreSqlMetadataVisitorTest"
        echo "     • PostgreSqlAuditListenerTest"
        echo ""
        echo "  📦 SparkSQL:"
        echo "     • SparkSqlFormatterTest"
        echo ""
        echo "  📦 通用:"
        echo "     • ParseTreeVisualizerTest"
        echo "     • SqlDiffEngineTest"
        echo ""
        exit 0
        ;;
    -h|--help|help)
        echo "用法: $0 [命令] [参数]"
        echo ""
        echo "命令:"
        echo "  all (默认)        运行全部 152 个测试"
        echo "  verbose           运行全部测试 (完整输出)"
        echo "  mysql             仅 MySQL 相关测试"
        echo "  pg                仅 PostgreSQL 相关测试"
        echo "  spark             仅 SparkSQL 相关测试"
        echo "  common            仅通用组件测试"
        echo "  class <类名>      运行指定测试类"
        echo "  method <类#方法>  运行指定测试方法"
        echo "  quick             快速冒烟测试"
        echo "  list              列出所有测试类"
        echo "  help              显示此帮助"
        echo ""
        echo "示例:"
        echo "  $0                                           # 运行全部"
        echo "  $0 mysql                                     # MySQL 测试"
        echo "  $0 class MySqlFormatterTest                  # 指定类"
        echo "  $0 method 'MySqlFormatterTest#testSimple'    # 指定方法"
        exit 0
        ;;
    *)
        error "未知命令: $target"
        echo "运行 $0 help 查看用法"
        exit 1
        ;;
esac

echo ""
info "耗时: ${SECONDS}s"
echo ""
