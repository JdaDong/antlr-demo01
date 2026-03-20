# 运行全部 152 个测试
mvn test

# 只运行某个测试类
mvn test -Dtest=MySqlMetadataVisitorTest
mvn test -Dtest=MySqlAuditListenerTest
mvn test -Dtest=MySqlFormatterTest
mvn test -Dtest=ParseTreeVisualizerTest
