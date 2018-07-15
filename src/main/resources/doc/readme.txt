记录遇到的一些坑
一. springboot idea 的热启动配置
1.pom.xml 添加配置
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional><!-- optional=true,依赖不会传递，该项目依赖devtools；之后依赖myboot项目的项目如果想要使用devtools，需要重新引入 -->
</dependency>
2.file ->settings ->compiler ->build project automatically  勾选
3.ctrl + shift + alt + /   register -> compiler.automake.allow when app running 勾选

二. springboot+shiro+thymeleaf  引入css,js时遇到的问题(蛋疼的找这个问题找了一下午)
1.在没有shiro的时候,html引入css js格式为:
    <link th:href="@{/css/tooltip-viewport.css}" rel="stylesheet"/>
    <script th:src="@{/js/jquery/3.2.1/jquery.js}"></script>
2.当项目有shiro的时候,shiro的过滤条件必须加上
    filterChainDefinitionMap.put("/css/**","anon");
    filterChainDefinitionMap.put("/js/**","anon");
  而不能只配置
  filterChainDefinitionMap.put("/static/**","anon");
