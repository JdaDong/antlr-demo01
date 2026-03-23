# 运行演示
mvn -q exec:java -Dexec.mainClass="com.example.antlr.LineageDemo"

# 打开交互式 HTML
open output/lineage-insert-select.html

# 查看 Gson JSON
cat output/lineage-join-gson.json

# 查看 Jackson JSON
cat output/lineage-join-jackson.json

# 渲染 DOT 为 PNG
dot -Tpng output/lineage-join.dot -o lineage.png
