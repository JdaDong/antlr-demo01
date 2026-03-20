#!/bin/bash
#
# ANTLR4 代码生成脚本
# 功能：从 .g4 文件生成 Lexer、Parser、Visitor、Listener 到 src/main/java 目录
#
# 用法：
#   ./generate-antlr.sh          # 生成所有方言
#   ./generate-antlr.sh mysql    # 仅生成 MySQL
#   ./generate-antlr.sh pg       # 仅生成 PostgreSQL
#   ./generate-antlr.sh spark    # 仅生成 SparkSQL
#

set -e

# ============ 配置 ============
PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
ANTLR4_VERSION="4.13.1"
BASE_PACKAGE="com.example.antlr"

# 源 .g4 文件目录
G4_DIR="${PROJECT_DIR}/src/main/antlr4"

# 输出目标目录（src/main/java 下对应的包路径）
OUTPUT_BASE="${PROJECT_DIR}/src/main/java/com/example/antlr"

# ANTLR4 jar 路径（Maven 本地仓库）
ANTLR4_JAR="${HOME}/.m2/repository/org/antlr/antlr4/${ANTLR4_VERSION}/antlr4-${ANTLR4_VERSION}-complete.jar"

# ============ 颜色输出 ============
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

info()    { echo -e "${BLUE}[INFO]${NC} $1"; }
success() { echo -e "${GREEN}[OK]${NC} $1"; }
warn()    { echo -e "${YELLOW}[WARN]${NC} $1"; }
error()   { echo -e "${RED}[ERROR]${NC} $1"; }

# ============ 检查/下载 ANTLR4 JAR ============
ensure_antlr4_jar() {
    if [ -f "$ANTLR4_JAR" ]; then
        info "使用已有的 ANTLR4 JAR: ${ANTLR4_JAR}"
        return
    fi

    # 尝试通过 Maven 下载
    info "ANTLR4 JAR 不存在，尝试通过 Maven 下载..."
    if command -v mvn &> /dev/null; then
        mvn dependency:resolve -Dartifact=org.antlr:antlr4:${ANTLR4_VERSION}:jar:complete -q 2>/dev/null || true
    fi

    if [ ! -f "$ANTLR4_JAR" ]; then
        # 下载 complete jar
        DOWNLOAD_URL="https://www.antlr.org/download/antlr-${ANTLR4_VERSION}-complete.jar"
        DOWNLOAD_DIR="$(dirname "$ANTLR4_JAR")"
        mkdir -p "$DOWNLOAD_DIR"

        info "从 ${DOWNLOAD_URL} 下载..."
        if command -v curl &> /dev/null; then
            curl -L -o "$ANTLR4_JAR" "$DOWNLOAD_URL"
        elif command -v wget &> /dev/null; then
            wget -O "$ANTLR4_JAR" "$DOWNLOAD_URL"
        else
            error "未找到 curl 或 wget，无法下载 ANTLR4 JAR"
            error "请手动下载：${DOWNLOAD_URL}"
            error "放置到：${ANTLR4_JAR}"
            exit 1
        fi
    fi

    if [ -f "$ANTLR4_JAR" ]; then
        success "ANTLR4 JAR 准备就绪"
    else
        error "无法获取 ANTLR4 JAR"
        exit 1
    fi
}

# ============ 生成单个方言 ============
generate_dialect() {
    local dialect=$1        # mysql / postgresql / sparksql
    local package_suffix=$2 # 包名后缀

    local g4_source="${G4_DIR}/${dialect}"
    local output_dir="${OUTPUT_BASE}/${package_suffix}"
    local full_package="${BASE_PACKAGE}.${package_suffix}"

    # 检查 .g4 文件是否存在
    if [ ! -d "$g4_source" ]; then
        warn "目录不存在，跳过: ${g4_source}"
        return
    fi

    local g4_files=$(find "$g4_source" -name "*.g4" -type f)
    if [ -z "$g4_files" ]; then
        warn "未找到 .g4 文件: ${g4_source}"
        return
    fi

    info "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    info "生成 ${dialect} (包: ${full_package})"
    info "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

    # 创建输出目录
    mkdir -p "$output_dir"

    # 运行 ANTLR4
    info "处理 .g4 文件:"
    for f in $g4_files; do
        echo "  → $(basename $f)"
    done

    java -jar "$ANTLR4_JAR" \
        -Dlanguage=Java \
        -visitor \
        -listener \
        -package "$full_package" \
        -o "$output_dir" \
        $g4_files

    # ANTLR 会在 output_dir 下创建与 sourceDirectory 对应的子目录结构
    # 需要把文件从嵌套子目录移到 output_dir 根目录
    # 查找所有生成的 .java 文件
    local nested_files=$(find "$output_dir" -mindepth 2 -name "*.java" -type f 2>/dev/null)
    if [ -n "$nested_files" ]; then
        for f in $nested_files; do
            mv "$f" "$output_dir/"
        done
        # 清理空的嵌套目录
        find "$output_dir" -mindepth 1 -type d -empty -delete 2>/dev/null || true
    fi

    # 同时删除生成的 .tokens 和 .interp 文件（不需要放在 src 中）
    rm -f "$output_dir"/*.tokens "$output_dir"/*.interp

    # 统计结果
    local count=$(find "$output_dir" -maxdepth 1 -name "*.java" -type f | wc -l | tr -d ' ')
    success "${dialect}: 生成 ${count} 个 Java 文件 → ${output_dir}"

    # 列出生成的文件
    for f in "$output_dir"/*.java; do
        echo "    📄 $(basename $f)"
    done
    echo ""
}

# ============ 主流程 ============
main() {
    echo ""
    echo "╔══════════════════════════════════════════╗"
    echo "║   ANTLR4 代码生成器 (v${ANTLR4_VERSION})            ║"
    echo "║   输出到: src/main/java/                 ║"
    echo "╚══════════════════════════════════════════╝"
    echo ""

    # 检查 ANTLR4 JAR
    ensure_antlr4_jar
    echo ""

    local target="${1:-all}"

    case "$target" in
        mysql|MYSQL)
            generate_dialect "mysql" "mysql"
            ;;
        pg|postgresql|POSTGRESQL|PG)
            generate_dialect "postgresql" "postgresql"
            ;;
        spark|sparksql|SPARKSQL|SPARK)
            generate_dialect "sparksql" "sparksql"
            ;;
        all|ALL)
            generate_dialect "mysql" "mysql"
            generate_dialect "postgresql" "postgresql"
            generate_dialect "sparksql" "sparksql"
            ;;
        clean)
            info "清理已生成的文件..."
            for dialect in mysql postgresql sparksql; do
                local dir="${OUTPUT_BASE}/${dialect}"
                if [ -d "$dir" ]; then
                    # 只删除 ANTLR 生成的文件（包含特定注释头）
                    local deleted=0
                    for f in "$dir"/*.java; do
                        if [ -f "$f" ] && head -5 "$f" | grep -q "Generated from"; then
                            rm "$f"
                            deleted=$((deleted + 1))
                        fi
                    done
                    success "清理 ${dialect}: 删除 ${deleted} 个生成文件"
                fi
            done
            ;;
        *)
            echo "用法: $0 [mysql|pg|spark|all|clean]"
            echo ""
            echo "参数:"
            echo "  mysql    仅生成 MySQL 方言"
            echo "  pg       仅生成 PostgreSQL 方言"
            echo "  spark    仅生成 SparkSQL 方言"
            echo "  all      生成所有方言 (默认)"
            echo "  clean    清理已生成的文件"
            exit 1
            ;;
    esac

    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    success "完成! 🎉"
    echo ""
}

main "$@"
