
Spring Cloud Alibaba是Spring Cloud标准的一种实现

## Spring Cloud Alibaba




## 搭建SpringGateway模块

添加依赖
```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-gateway</artifactId>
	<version>3.1.5</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter</artifactId>
	<version>2.6.11</version>
</dependency>
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
	<version>2021.0.4.0</version>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
	<version>3.1.5</version>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-loadbalancer</artifactId>
	<version>3.1.5</version>
</dependency>
```
添加配置
```yml
spring:
  application:
    name: gateway
  cloud:
    gateway:
      routes:
        - id: demo-service
          uri: lb://demo-service
          predicates: # 判断请求是否响应
            - Path=/demo/**
          filters:
            - StripPrefix=1 # 去除请求路径的部分
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```
为gateway的启动类添加`@EnableDiscoveryClient`注解

