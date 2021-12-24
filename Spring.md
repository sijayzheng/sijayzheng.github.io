![image](../img/1602656735155-8b693f8f-3cb0-4c69-ad4e-37644cea4ee5.png)

#### 核心容器：包括 Core、Beans、Context、EL 模块

- Core 模块：封装了框架依赖的最底层部分，包括资源访问、类型转换及一些常用工具类。
- Beans 模块：提供了框架的基础部分，包括控制反转（IOC）和依赖注入（DI）。其中 BeanFactory 是容器核心，本质是“工厂设计模式”的实现，而且无需编程实现“单例设计模式”，单例完全由容器控制，而且提倡面向接口编程，而非面向实现编程。所有应用程序对象及对象间关系由框架管理，从而真正从程序逻辑中把维护对象之间的依赖关系提取出来，所有这些依赖关系都由 BeanFactory 来维护。
- Context 模块：以 Core 和 Beans 为基础，集成 Beans 模块功能并添加资源绑定、数据验证、国际化、Java EE 支持、容器生命周期、事件传播等。核心接口是 ApplicationContext。
- EL 模块：提供强大的表达式语言支持，支持访问和修改属性值，方法调用，支持访问及修改数组、容器和索引器，命名变量，支持算数和逻辑运算，支持从 Spring 容器获取 Bean，它也支持列表投影、选择和一般的列表聚合等。

#### AOP、Aspects 模块

- AOP 模块：Spring AOP 模块提供了符合 AOP Alliance 规范的面向切面的编程（aspect-oriented programming）实现，提供比如日志记录、权限控制、性能统计等通用功能和业务逻辑分离的技术，并且能动态的把这些功能添加到需要的代码中，这样各专其职，降低业务逻辑和通用功能的耦合。
- Aspects 模块：提供了对 AspectJ 的集成，AspectJ 提供了比 Spring ASP 更强大的功能。
- 数据访问/集成模块：该模块包括了 JDBC、ORM、OXM、JMS 和事务管理。
- 事务模块：该模块用于 Spring 管理事务，只要是 Spring 管理对象都能得到 Spring 管理事务的好处，无需在代码中进行事务控制了，而且支持编程和声明性的事务管理。
- JDBC 模块：提供了一个 JDBC 的样例模板，使用这些模板能消除传统冗长的 JDBC 编码还有必须的事务控制，而且能享受到 Spring 管理事务的好处。
- ORM 模块：提供与流行的“对象-关系”映射框架的无缝集成，包括 Hibernate、JPA、MyBatis 等。而且可以使用 Spring 事务管理，无需额外控制事务。
- OXM 模块：提供了一个对 Object/XML 映射实现，将 java 对象映射成 XML 数据，或者将 XML 数据映射成 java 对象，Object/XML 映射实现包括 JAXB、Castor、XMLBeans 和 XStream。
- JMS 模块：用于 JMS（Java Messaging Service），提供一套“消息生产者、消息消费者”模板用于更加简单的使用 JMS。JMS 用于用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。
- Web/Remoting 模块：Web/Remoting 模块包含了 Web、Web-Servlet、Web-Struts、Web-Porlet 模块。
- Web 模块：提供了基础的 Web 功能。例如多文件上传、集成 IoC 容器、远程过程访问（RMI、Hessian、Burlap）以及 Web Service 支持，并提供一个 RestTemplate 类来提供方便的 Restful services 访问。
- Web-Servlet 模块：提供了一个 Spring MVC Web 框架实现。Spring MVC 框架提供了基于注解的请求资源注入、更简单的数据绑定、数据验证等及一套非常易用的 JSP 标签，完全无缝与 Spring 其他技术协作。
- Web-Struts 模块：提供了与 Struts 无缝集成，Struts 1.x 和 Struts 2.x 都支持。
- Test 模块：Spring 支持 Junit 和 TestNG 测试框架，而且还额外提供了一些基于 Spring 的测试功能，比如在测试 Web 框架时，模拟 Http 请求的功能。





#### @Component

被此注解标注的类将被 Spring 容器自动识别，自动生成 Bean 定义

#### @Controller

对应表现层的 Bean，也就是 Action。

#### @Service

对应的是业务层 Bean。

#### @Repository

对应数据访问层 Bean。





#### @Autowired

@Autowired 可以用来装配 bean，都可以写在字段上，或者方法上。使用 @Autowired，首先要在在 applicationContext.xml 中加入 `<bean class = "org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor"/>`。@Autowired 默认按类型装配，默认情况下要求依赖对象必须存在，如果要允许 null 值，可以设置它的 required 属性为 false。

#### @Configuration

通过使用注释 @Configuration 告诉 Spring，这个 Class 是 Spring 的核心配置文件，并且通过使用注解 @Bean 定义 bean





#### @Component

表示一个自动扫描 component。

#### @Repository 

表示持久化层的 DAO component。

#### @Service

表示业务逻辑层的 Service component。

#### @Controller 

表示表示层的 Controller component。







Bean 的作用域决定了从 Spring 容器中返回的 Bean 实例的类型。在 Spring 中，支持以下 5 种类型的作用域：

1. singleton — 单例模式，由 IOC 容器返回一个唯一的 bean 实例。
2. prototype — 原型模式，被请求时，每次返回一个新的 bean 实例。
3. request — 每个 HTTP Request 请求返回一个唯一的 Bean 实例。
4. session — 每个 HTTP Session 返回一个唯一的 Bean 实例。
5. globalSession — Http Session 全局 Bean 实例。

> 注：大多数情况下，你可能只需要处理 Spring 的核心作用域 — 单例模式（singleton）和原型模式（prototype），默认情况下，作用域是单例模式。





# SpringMVC

##  @Controller

**控制器 Controller 负责处理由 DispatcherServlet 分发的请求，它把用户请求的数据经过业务处理层处理之后封装成一个 Model，然后再把该 Model 返回给对应的 View 进行展示。**

**
**

## @RequestMapping

**@RequestMapping 注解用来处理请求地址映射，指示 Spring 用哪个类或方法来处理请求动作。** @RequestMapping 可用于类或方法上。

当 @RequestMapping 用于类上时，表示类中的所有响应方法都被映射到 value 属性所指示的路径下，如：

```
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = "/register")
    public String Register() {
        return "register";
    }
}
```

在这里，相关路径均要加上 `/user`，`Register()` 映射的 URL 为：

```
http://localhost:8080/user/register
```

若未用 `@RequestMapping(value = "/user")` 注解类，该方法映射的 URL 为：

```
http://localhost:8080/register
```

同样是上面的示例代码，`@RequestMapping(value = "/register")` 映射一个请求和请求的方法，请求由 `Register` 方法进行处理。

#### @RequestMapping 常用属性

@RequestMapping 常用属性如下：

**（1）value**

**指定请求的实际地址，即 value 属性将 URL 映射到方法上。**

如下面的代码：

```
@Controller
public class UserController {
    @RequestMapping(value = "/register")
    public String Register() {
        return "register";
    }
}
```

对应的 URL：

```
http://localhost:8080/register
```

如果 @RequestMapping 只有唯一的属性，value 属性名可省略。

```
@RequestMapping(value = "/register")
@RequestMapping("/register")
```

**（2）method**

**指定请求的 method 类型，即该方法可以处理的 HTTP 请求方式，如 GET、POST 等。**

```
@RequestMapping("/register",method = RequestMethod.POST)
```

> 注：如果未指定 method，则可以处理任意类型的 HTTP 请求。

**（3）consumes**

**指定处理请求的提交内容类型（Content-Type），例如 application/json, text/html。**

```
@RequestMapping("/register",method = RequestMethod.POST,consumes = "application/json")
```

这里只处理 request Content-Type 为 application/json 的请求。

**（4）produces**

**指定返回的内容类型，仅当 request 请求头中的（Accept）类型中包含该指定类型才返回。**

```
@RequestMapping("/register",method = RequestMethod.POST,produces = "application/json")
```

**（5）params**

**指定 request 中必须包含某些参数值是，才让该方法处理。**

```
@RequestMapping("/register",method = RequestMethod.POST,params = "myParam = myValue")
```

这里只处理名为 "myParam"，值为 "myValue" 的请求。

**（6）headers**

**指定 request 中必须包含某些指定的 header 值，才能让该方法处理请求。**

```
@RequestMapping("/register",method = RequestMethod.POST,headers = "Referer = https://www.lanqiao.cn")
```

这里只处理 request 的 header 中包含了指定 "Referer" 请求头和对应值为 "[https://www.lanqiao.cn"](https://www.lanqiao.cn"/) 的请求。

#### 请求处理方法的参数类型

先来看一个例子：

```
@RequestMapping(value = "/register")
public String Register(User user,Model model) {
    model.addAttribute("user", user);
    return "success";
}
```

在这里 Register 方法接受一个 Model 类型的参数。其实每个请求处理方法可以有多个不同类型的参数。

支持的方法参数类型：

- org.springframework.web.context.request.WebRequest 或 org.springframework.web.context.request.NativeWebRequest
- java.util.Locale
- java.util.TimeZone (Java 6+) / java.time.ZoneId (on Java 8)
- java.io.InputStream / java.io.Reader
- java.io.OutputStream / java.io.Writer
- org.springframework.http.HttpMethod
- java.security.Principal
- @PathVariable
- @MatrixVariable
- @RequestParam、@RequestHeader、@RequestBody、@RequestPart
- HttpEntity<?>
- java.util.Map / org.springframework.ui.Model / org.springframework.ui.ModelMap
- org.springframework.web.servlet.mvc.support.RedirectAttributes
- @ModelAttribute 标记的参数
- org.springframework.validation.Errors / org.springframework.validation.BindingResult
- org.springframework.web.bind.support.SessionStatus
- org.springframework.web.util.UriComponentsBuilder

#### 请求处理方法可返回类型

在上一小节 `请求处理方法的参数类型` 中的例子中，Register 方法的返回类型为 String 类型。

支持的方法返回类型：

- ModelAndView
- Model
- Map
- View
- String
- void
- 如果该方法用 @ResponseBody 注解，则返回类型将写入响应 HTTP 主体。
- HttpEntity or ResponseEntity
- HttpHeaders
- Callable<?>
- DeferredResult<?>
- ListenableFuture<?>
- ResponseBodyEmitter、SseEmitter、StreamingResponseBody
- 除以上几种情况之外的其他任何返回类型都会被当做模型中的一个属性来处理，而返回的视图还是由 RequestToViewNameTranslator 来决定，添加到模型中的属性名称可以在该方法上用 `@ModelAttribute("attributeName")` 来定义，否则将使用返回类型的类名称的首字母小写形式来表示。

#### Model

Spring MVC 内部使用 Model 接口**存储模型数据**，功能类似于 Java 中的 Map，而 ModelMap 接口是实现了 Model 接口。因此，Model 和 ModelMap 的用法相近，如：

```
@RequestMapping(value = "/register")
public String Register(User user,Model model) {
    // jsp 页面传入的 form 表单
    // 将 User 对象添加到 Model 中
    model.addAttribute("user", user);
    return "success";
}
```

在这里，可以将参数中的 `Model model` 改为 `ModelMap modelMap`。

#### ModelAndView

**ModelAndView 既包含模型数据信息又包含视图信息**，它可用作 Controller 处理方法的返回值。Spring MVC 将包含的视图对模型数据进行渲染，模型数据可以看做 Java 中的 Map。

ModelAndView 的用法示例：

```
@RequestMapping(value = "/register")
public ModelAndView Register(User user,ModelAndView mv) {
    // jsp 页面传入的 form 表单
    // 将 User 对象添加到 ModelAndView 的 Model 中
    mv.addObject("user", user);
    // 设置返回视图的名称
    mv.setViewName("success");
    return mv;
}
```

