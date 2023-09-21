## SpringBoot 启动顺序

配置属性、获取监听器，发布应用开始启动事件、初始化输入参数、配置环境，输出 banner、创建上下文、预处理上下文、刷新上下文(加载 tomcat 容器)
、再刷新上下文、发布应用已经启动事件、发布应用启动完成事件

SpringBoot

## 传参

### 普通传参(前端参数与后端形参名称一致)

**前端代码：**

```javascript
var param = {
  name: '张三',
}
$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items.do',
    type: 'post',
    data: param,
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @RequestMapping("/items")
    public String charon(String name) {
        logger.info(name);
        return "test";
    }
```

**后台结果：**name=张三

### 普通传参(前端参数与后端形参名称不一致)

**前端代码：**

```javascript
var param = {
  dcc: '李四',
}

$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items2.do',
    type: 'post',
    data: param,
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items2")
    public String charon2(@RequestParam("dcc") String name) {
        logger.info(name);
        return "test";
    }
```

当前端参数名称和后端不一致时可以使用 RequestParam 注解，请求参数的参数名,可以作为参数映射名称
**后台结果：**name=李四

### pojo 实体类传参

**前端代码**

```javascritp
    var param = {
        "users" : "账户",
        "password" : "密码"
    }

    $('#btn').click(function () {
        $.ajax({
            url : '/WeChart/items3.do',
            type : 'post',
            data : param,
            dataType : 'json',
            success : function (m) {
                console.log(m)
            }
        })
    })
```

**后端代码**

```java
    @PostMapping("/items3")
    public String charon3(Pusers pusers) {
        logger.info(pusers.getUsers());
        logger.info(pusers.getPassword());
        return "test";
    }
```

**实体类代码**

```java
public class Pusers {
    private int id;
    private String users;
    private String password;
    //省略get/set方法
}
```

前端参数名称对应实体类中的属性名称，后端形参可以直接用实体类来表示
**后台结果：**users=账户，password=密码

### 多参无实体类传参一

**前端代码**

```javascript
var param = {
  users: '账户',
  password: '密码',
  name: '我的名字',
}
param = JSON.stringify(param) //将对象转为json串
$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items4.do',
    contentType: 'application/json;charset=UTF-8',
    data: param,
    type: 'post',
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items4")
    public String charon4(@RequestBody String map) {
        logger.info(map);
        return "test";
    }
```

**后端结果：**{"users":"账户","password":"密码","name":"我的名字"}

### 多参无实体类传参二

**前端代码**

```javascript
var param = {
  users: '账户',
  password: '密码',
  name: '我的名字',
}

$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items5.do',
    data: param,
    type: 'post',
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items5")
    public String charon5(@RequestParam Map<String, Object> map) {
        logger.info(map.toString());
        logger.info((String) map.get("name"));
        logger.info((String) map.get("users"));
        return null;
    }
```

**后端结果：**{users=账户, password=密码, name=我的名字}，我的名字，账户

### 传递数组

**前端代码**

```javascript
var param = {
  ids: [1, 2, 3],
}

$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items6.do',
    data: param,
    type: 'post',
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items6")
    public String charon6(@RequestParam(value = "ids[]") Integer[] ids) {
        logger.info("数据：{}", Arrays.asList(ids));
        return "test";
    }
```

**后端结果：**[1, 2, 3]

### 传递集合

**前端代码**

```javascript
var param = {
  ids: [1, 2, 3],
}

$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items7.do',
    data: param,
    type: 'post',
    dataType: 'json',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items7")
    public String charon7(@RequestParam(value = "ids[]") List<Integer> ids) {
        logger.info("数据：{}", ids.toString());
        return "test";
    }
```

**后端结果：**[1, 2, 3]

### 传递复杂性参数

**前端代码**

```javascript
var dcc = []
var lists = [
  {
    users: '张三1',
    password: '密码1',
  },
  {
    users: '张三2',
    password: '密码2',
  },
]
var user = {
  username: '张三',
}
user.lists = lists //向对象种添加新属性
dcc.push(user)
$('#btn').click(function () {
  $.ajax({
    url: '/WeChart/items9.do',
    data: JSON.stringify(dcc),
    contentType: 'application/json',
    type: 'post',
    success: function (m) {
      console.log(m)
    },
  })
})
```

**后端代码**

```java
    @PostMapping("/items9")
    public String charon9(@RequestBody String maps) {
        logger.info("数据：{}", maps);
        return "test";
    }
```

**后端结果：**[{"username":"张三","lists":[{"users":"张三 1","password":"密码 1"},{"users":"张三 2","password":"密码 2"}]}]

### 总结

1. @RequestBody 注解，必须与 contentType 类型 application/json 配合使用
2. @RequestParam 注解，必须与 contentTyp 类型 application/x-www-form-urlencoded 配合使用，其为默认类型。
3. JSON.stringify()把对象类型转换为字符串类型，配合@RequestBody 注解和 contentType 类型 application/json 使用。

## SpringBoot 注解

## 1.SpringBoot/spring

### @SpringBootApplication:

包含@Configuration、@EnableAutoConfiguration、@ComponentScan 通常用在主类上；

### @Repository:

用于标注数据访问组件，即 DAO 组件；

### @Mapper

用于标注 Mybatis 的 mapper 类

### @Service:

用于标注业务层组件；

### @RestController:

用于标注控制层组件(如 struts 中的 action)，包含@Controller 和@ResponseBody；

### @Controller:

用于标注是控制层组件，需要返回页面时请用@Controller 而不是@RestController；

### @Component:

泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注；

### @ResponseBody:

表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用，在使用@RequestMapping 后，返回值通常解析为跳转路径，

加上@responsebody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中；比如异步获取 json 数据，加上@responsebody 后，会直接返回 json 数据；

### @RequestBody:

参数前加上这个注解之后，认为该参数必填。表示接受 json 字符串转为对象、List 等；

### @ComponentScan:

组件扫描。个人理解相当于，如果扫描到有@Component @Controller @Service 等这些注解的类，则把这些类注册为 bean\*；

### @Configuration:

指出该类是 Bean 配置的信息源，相当于 XML 中的，一般加在主类上；

### @Bean:

相当于 XML 中的,放在方法的上面，而不是类，意思是产生一个 bean,并交给 spring 管理；

### @EnableAutoConfiguration:

让 Spring Boot 根据应用所声明的依赖来对 Spring 框架进行自动配置，一般加在主类上；

### @AutoWired:

byType 方式。把配置好的 Bean 拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作；

当加上（required=false）时，就算找不到 bean 也不报错；

### @Qualifier:

当有多个同一类型的 Bean 时，可以用@Qualifier(“name”)来指定。与@Autowired 配合使用；

### @Resource(name=”name”,type=”type”)：

没有括号内内容的话，默认 byName。与@Autowired 干类似的事；

### @RequestMapping:

RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径；

> 该注解有六个属性:
>
> params:指定 request 中必须包含某些参数值是，才让该方法处理。
>
> headers:指定 request 中必须包含某些指定的 header 值，才能让该方法处理请求。
>
> value:指定请求的实际地址，指定的地址可以是 URI Template 模式
>
> method:指定请求的 method 类型， GET、POST、PUT、DELETE 等
>
> consumes:指定处理请求的提交内容类型（Content-Type），如 application/json,text/html;
>
> produces:指定返回的内容类型，仅当 request 请求头中的(Accept)类型中包含该指定类型才返回。

### @GetMapping、@PostMapping、@PutMapping、@DeleteMapping:

相当于@RequestMapping（value=”/”，method=RequestMethod.Get\Post\Put\Delete 等） 。是个组合注解；

### @RequestParam:

用在方法的参数前面。相当于 request.getParameter()；

### @PathVariable:

路径变量。如 RequestMapping(“user/get/mac/{macAddress}”) ；

```
public String getByMacAddress(@PathVariable(“macAddress”) String macAddress) {
    //do something;
}
```

参数与大括号里的名字相同的话，注解后括号里的内容可以不填。

### @AssertTrue

用于 boolean 字段,该字段只能为 true

### @AssertFalse

该字段的值只能为 false

### @CreditcardNumber

对信用卡号进行一个大致的验证

### @DecimalMax

只能小于或等于该值

### @DecimalMin

只能大于或等于该值

### @Digits(Integer=2, fraction=20)

检查是否是一种数字的整数、分数,小数位数的数字。

### @Email

检查是否是一个有效的 emai 地址

### @Future

检查该字段的日期是否是属于将来的日期

### @Length(min=0,max=10)

检查所属的字段的长度是否在 min 和 max 之间,只能用于字符串

### @Max

该字段的值只能小于或等于该值

### @Min

该字段的值只能大于或等于该值

### @NotNull

不能为 nuL

### @NotBlank

不能为空,检查时会将空格忽略

### @NotEmpty

不能为空,这里的空是指空字符串

### @Null

检查该字段为空

### @Past

检查该字段的日期是在过去

###  

检查该字段的 size 是否在 min 和 max 之间,可以是字符串、数组、集合、Map 等

### @URL( protocol=host,port)

检查是否是一个有效的 RL,如果提供了 protocol,host 等,则该 URL 还需满足提供的条件

### @Valid

该注解只要用于字段为一个包含其他对象的集合或 map 或数组的字段,或该字段直接为一个其他对象的引用,这样在检查当前对象的同时也会检查该字段所引用的对

# 2. Jpa

### @Entity:

### @Table(name=”“):

表明这是一个实体类。一般用于 jpa ，这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table 可以省略；

### @MappedSuperClass:

用在确定是父类的 entity 上。父类的属性子类可以继承；

### @NoRepositoryBean:

一般用作父类的 repository，有这个注解，spring 不会去实例化该 repository；

### @Column:

如果字段名与列名相同，则可以省略；

### @Id:

表示该属性为主键；

### @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = “repair_seq”):

表示主键生成策略是 sequence（可以为 Auto、IDENTITY、native 等，Auto 表示可在多个数据库间切换），指定 sequence 的名字是 repair_seq；

### @SequenceGenerator(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1):

name 为 sequence 的名称，以便使用，sequenceName 为数据库的 sequence 名称，两个名称可以一致；

### @Transient:

表示该属性并非一个到数据库表的字段的映射,ORM 框架将忽略该属性.

如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,否则,ORM 框架默认其注解为@Basic；

### @Basic(fetch=FetchType.LAZY):

标记可以指定实体属性的加载方式；

### @JsonIgnore:

作用是 json 序列化时将 java bean 中的一些属性忽略掉,序列化和反序列化都受影响；

### @JoinColumn(name=”loginId”):

一对一：本表中指向另一个表的外键。

一对多：另一个表指向本表的外键。

### @OneToOne

### @OneToMany

### @ManyToOne:

对应 Hibernate 配置文件中的一对一，一对多，多对一。

# 3. 全局异常处理

### @ControllerAdvice:

包含@Component。可以被扫描到。统一处理异常；

### @ExceptionHandler(Exception.class):

用在方法上面表示遇到这个异常就执行以下方法。

# 4. SpringCloud

### @EnableEurekaServer:

用在 springboot 启动类上，表示这是一个 eureka 服务注册中心；

### @EnableDiscoveryClient:

用在 springboot 启动类上，表示这是一个服务，可以被注册中心找到；

### @LoadBalanced:

开启负载均衡能力；

### @EnableCircuitBreaker:

用在启动类上，开启断路器功能；

### @HystrixCommand(fallbackMethod=”backMethod”):

用在方法上，fallbackMethod 指定断路回调方法；

### @EnableConfigServer:

用在启动类上，表示这是一个配置中心，开启 Config Server；

### @EnableZuulProxy:

开启 zuul 路由，用在启动类上；

### @SpringCloudApplication:

包含

@SpringBootApplication

@EnableDiscovertyClient

@EnableCircuitBreaker

分别是 SpringBoot 注解、注册服务中心 Eureka 注解、断路器注解。对于 SpringCloud 来说，这是每一微服务必须应有的三个注解，所以才推出了@SpringCloudApplication
这一注解集合

## other
