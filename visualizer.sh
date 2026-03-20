# 生成所有可视化文件
mvn -q exec:java -Dexec.mainClass="com.example.antlr.TreeVisualizerDemo"

# 打开 HTML 查看
open output/select-join.html
