## 简介

[MyBatis 文档](https://mybatis.net.cn/)

- 原名 iBatis

- 持久层框架

- 支持自定义 SQL、存储过程以及高级映射

- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作

- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO 为数据库中的记录。

- 简化 JDBC 操作

## HelloMyBatis

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

新建普通 maven 项目，添加`mysql-connector-java`、`mybatis`、`junit`的依赖

3. 依据[文档](https://mybatis.net.cn/getting-started.html)添加`mybatis-config.xml`,新建`mybatis`的操作工具类、`pojo`类、数据库映射文件`mapper.xml`、数据库操作接口`mapper.java`

```java
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 获取SqlSessionFactory
     */
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
```

```java
public class UserPO {
    private int id;
    private String username;
    private String pwd;
    //省略 getter and setter
}
```

```java
public interface UserMapper {
     /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    List<UserPO> listUser();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    UserPO getUserById(int id);

    /**
     * 添加
     * @param user
     * @return
     */
    int addUser(UserPO user);

    /**
     * 修改
     * @param user
     * @return
     */
    int updateUser(UserPO user);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    int deleteUser(int id);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--绑定相应的Mapper接口-->
<mapper namespace="zheng.sijay.mapper.UserMapper">
    <select id="listUser" resultType="UserPO">
        select *
        from user
    </select>
    <select id="getUserById" resultType="UserPO">
        select *
        from user
        where id = #{id}
    </select>
    <!--    对象的属性可以直接取出-->
    <insert id="addUser" parameterType="UserPO">
        insert into user (id, name, pwd)
        values (#{id}, #{name}, #{pwd});
    </insert>
    <update id="updateUser">
        update user
        set name = #{name},
            pwd=#{pwd}
        where id = #{id};
    </update>
</mapper>

```

4. 编写测试类并运行

```java
public class UserMapperTest {
        @Test
    public void listUser() {
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            // 旧写法，存在类型强制转换
            sqlSession.selectList("zheng.sijay.mapper.UserMapper.listUser").forEach(System.out::println);
            // 新写法
            mapper.listUser().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById() {
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            System.out.println(mapper.getUserById(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addUser(){
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            UserPO po = new UserPO(10,"tom","123456");
            // 执行SQL
            System.out.println(mapper.addUser(po));
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser(){
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            UserPO po = new UserPO(10,"tom L","654321");
            // 执行SQL
            System.out.println(mapper.updateUser(po));
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser(){
        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            // 获取接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            System.out.println(mapper.deleteUser(3));
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## 作用域和生命周期

作用域错误和生命周期错误会导致并发问题

### SqlSessionFactoryBuilder

可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。 因此 SqlSessionFactoryBuilder 实例的最佳作用域是**方法作用域**（也就是局部方法变量）。 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。

### SqlSessionFactory

SqlSessionFactory 由 SqlSessionFactoryBuilder 创建。SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。 SqlSessionFactory 在应用运行期间不能创建多次。因此 SqlSessionFactory 的最佳作用域是**应用作用域**。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

### SqlSession

SqlSession 通过 SqlSessionFactory 创建。每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例是**线程不安全**的，因此是不能被共享的，所以它的最佳的作用域是**请求或方法作用域**。 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。每次请求，就打开一个 SqlSession，结束后就关闭它。 这个关闭操作很重要，为了确保关闭成功应该把这个关闭操作放到 finally 块中。 下面的示例就是一个确保 SqlSession 关闭的标准模式：

```java
// java7的try-with-resource
try (SqlSession session = sqlSessionFactory.openSession()) {
  // 你的应用逻辑代码
}
```

在所有代码中都遵循这种使用模式，可以保证所有数据库资源都能被正确地关闭。

### 映射器实例

映射器即 mapper 接口。映射器是一些绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。虽然从技术层面上来讲，任何映射器实例的最大作用域与请求它们的 SqlSession 相同。但方法作用域才是映射器实例的最合适的作用域。 也就是说，映射器实例应该在调用它们的方法中被获取，使用完毕之后即可丢弃。 映射器实例并不需要被显式地关闭。尽管在整个请求作用域保留映射器实例不会有什么问题，但是你很快会发现，在这个作用域上管理太多像 SqlSession 的资源会让你忙不过来。 因此，最好将映射器放在方法作用域内。就像下面的例子一样：

```
try (SqlSession session = sqlSessionFactory.openSession()) {
  BlogMapper mapper = session.getMapper(BlogMapper.class);
  // 你的应用逻辑代码
}
```

## 配置文件（默认 mybatis-config.xml）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.example.domain"/>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--注册Mapper.xml-->
    <mappers>
        <mapper resource="UserMapper.xml"/>
    </mappers>
</configuration>
```

基于 MyBatis 的应用以 SqlSessionFactory 的实例为核心。SqlSessionFactory 的实例可通过 SqlSessionFactoryBuilder 获得。而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。MyBatis 中的 Resources 的工具类可以很容易的从 XML 文件中构建 SqlSessionFactory。

该配置文件中包含了对 MyBatis 系统的核心设置，包括获取数据库连接实例的数据源（DataSource）以及决定事务作用域和控制方式的事务管理器（TransactionManager）

### typeAliases

```xml
<typeAliases>
    <package name=""/>
</typeAliases>
```

配置别名之后，在 mapper.xml 中可以不使用全限定名

### environments

配置事务管理和连接池

### mappers

配置映射器

## mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--绑定相应的Mapper接口-->
<mapper namespace="">

</mapper>
```

> 注意：添加、修改、删除需要提交事务

### namespace

- 利用更长的全限定名将不同的语句隔离
- 实现接口绑定，名称为映射器接口的全限定名

> **命名解析**
>
> - 全限定名将呗直接用于查找及使用
> - 短名称也可以作为一个单独的引用，但在不唯一的情况下会产生“段名称不唯一”的错误

### select

查询语句

- `id`：映射器中的方法名
- `resultType`：sql 的返回值，返回一个
- `parameterType`：参数类型
  - map（多参数）：取key值
  - 实体类：取属性值
  - 基本类型（可忽略不写）：取参数名

- `resultMap`：sql 的返回值，返回多个

模糊查询拼接通配符时可以在代码中也可以在sql中

// TODO

## 错误、异常

- > org.apache.ibatis.binding.BindingException: Type interface \*\*\*Mapper is not known to the MapperRegistry.

未注册\*\*\*Mapper.xml

- > Error building SqlSession.
  > The error may exist in zheng/sijay/mapper/UserMapper
  > Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource zheng/sijay/mapper/UserMapper

maven 导出资源不正确或未生效，详见[maven#pom](back/Maven.md)

mybatis-config.xml 配置文件中的 mappers 错误

- > Error building SqlSession.
  > Cause: org.apache.ibatis.builder.BuilderException: Error creating document instance. Cause: org.xml.sax.SAXParseException; lineNumber: 24; columnNumber: 17; 元素类型为 "configuration" 的内容必须匹配 "(properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,reflectorFactory?,plugins?,environments?,databaseIdProvider?,mappers?)"。

配置文件错误

- > Mapper method 'zheng.sijay.mapper.UserMapper.addUser attempted to return null from a method with a primitive return type (int).

mapper.xml中的标签错误，如添加语句使用了select
