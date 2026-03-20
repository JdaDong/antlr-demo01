# 1. 给脚本添加执行权限
chmod +x generate-antlr.sh

# 2. 生成所有方言的代码到 src/main/java
./generate-antlr.sh

# # 3. 也可以单独生成某个方言
# ./generate-antlr.sh mysql      # 仅 MySQL
# ./generate-antlr.sh pg         # 仅 PostgreSQL
# ./generate-antlr.sh spark      # 仅 SparkSQL

# # 4. 清理生成的文件
# ./generate-antlr.sh clean
