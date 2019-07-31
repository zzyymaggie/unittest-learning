单元测试分享



DbUnit参考：
http://outofmemory.cn/code-snippet/2644/usage-DbUnit-do-gaoxiao-unit-test

代码覆盖率插件jacoco
- 配置参考springboot-test-demo/pom.xml
- 执行测试用例：mvn test
- 生成代码覆盖率报告 mvn jacoco:report
- 查看报告：springboot-test-demo/target/site/jacoco/index.html
- 上传到sonar: 
`mvn sonar:sonar -Dsonar.host.url=http://localhost:9000  -Dsonar.login=d4de8bf1e3e14f4a9852817461c35be5630ed71d -Dsonar.java.binaries=target/classes`
执行单元测试插件 maven-surefire-plugin
skip=true:忽略单元测试
skip=false:执行单元测试（默认值）