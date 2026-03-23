#!/bin/bash
#
# 编译脚本 — ANTLR SQL Parser
#
# 用法：
#   ./build.sh              # 编译项目
#   ./build.sh clean        # 清理后编译
#   ./build.sh package      # 打包 JAR
#   ./build.sh gen          # 先生成 ANTLR 代码，再编译
#   ./build.sh all          # 生成 + 清理 + 打包（完整构建）
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

# ============ 计时 ============
SECONDS=0

# ============ 命令 ============
do_compile() {
    step "编译项目..."
    mvn compile -q
    success "编译成功 ✅"
}

do_clean() {
    step "清理 target/ ..."
    mvn clean -q
    success "清理完成"
}

do_package() {
    step "打包 JAR (跳过测试)..."
    mvn package -DskipTests -q
    local jar=$(find target -maxdepth 1 -name "*.jar" ! -name "*-sources*" | head -1)
    if [ -n "$jar" ]; then
        success "打包完成 → $jar ($(du -h "$jar" | cut -f1))"
    else
        success "打包完成"
    fi
}

do_generate() {
    if [ -x "./generate-antlr.sh" ]; then
        step "生成 ANTLR 代码..."
        ./generate-antlr.sh all
    else
        warn "generate-antlr.sh 不存在或不可执行，跳过代码生成"
    fi
}

# ============ 主流程 ============
echo ""
echo "╔══════════════════════════════════════════╗"
echo "║     ANTLR SQL Parser — 编译脚本          ║"
echo "╚══════════════════════════════════════════╝"
echo ""

target="${1:-compile}"

case "$target" in
    compile|c)
        do_compile
        ;;
    clean)
        do_clean
        do_compile
        ;;
    package|pkg|jar)
        do_package
        ;;
    gen|generate)
        do_generate
        do_compile
        ;;
    all|full)
        do_generate
        do_clean
        do_package
        success "完整构建完成 🎉"
        ;;
    -h|--help|help)
        echo "用法: $0 [命令]"
        echo ""
        echo "命令:"
        echo "  compile (默认)  仅编译"
        echo "  clean           清理后重新编译"
        echo "  package         打包 JAR (跳过测试)"
        echo "  gen             先生成 ANTLR 代码，再编译"
        echo "  all             完整构建: 生成 + 清理 + 打包"
        echo "  help            显示此帮助"
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
