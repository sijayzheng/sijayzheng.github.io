## 简介

[MyBatis 文档](<[MyBatis中文网](https://mybatis.net.cn/)>)

- 原名 iBatis

- 持久层框架

- 支持自定义 SQL、存储过程以及高级映射

- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作

- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO 为数据库中的记录。

- 简化 JDBC 操作

## HelloMyBatis

### 搭建环境

1. [搭建数据库](db/MySQL.md)

- 建一个测试库`create database mybatistest`

- 建一个测试表

  ```sql
  create table user(
  	id int primary key,
      name varchar(50),
      pwd varchar(50)
  )engine=innodb default charset=utf8mb4;
  ```

- 插入测试数据

  ```sql
  insert into user values(1,'张三','123456'),(2,'李四','123456'),(3,'王五','123456'),(4,'赵六','123456'),(5,'钱七','123456'),(6,'孙八','123456'),(7,'周九','123456'),(8,'吴十','123456');
  ```

2. 新建项目

   新建普通 maven 项目，添加`mysql-connector-java`、`mybatis`

## mapper.xml

- resultType
  - 返回一个
- resultMap
  - 返回多个

## 错误

- org.apache.ibatis.binding.BindingException: Type interface \*\*\*Mapper is not known to the MapperRegistry.

未注册\*\*\*Mapper.xml

- > Error building SqlSession.
  > The error may exist in zheng/sijay/mapper/UserMapper
  > Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource zheng/sijay/mapper/UserMapper

mapper.xml 找不到，可能是因为 maven 导出资源不正确或未生效，详见[maven#pom](back/Maven.md)
