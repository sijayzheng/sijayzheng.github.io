## @Controller

**控制器 Controller 负责处理由 DispatcherServlet 分发的请求，它把用户请求的数据经过业务处理层处理之后封装成一个 Model，然后再把该 Model 返回给对应的 View 进行展示。**

\*\*
\*\*

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

### @RequestMapping 常用属性

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

### 请求处理方法的参数类型

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

### 请求处理方法可返回类型

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

### Model

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

### ModelAndView

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

## 问题

1. **问题描述：**

annot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()" because "this.condition" is null

**解决方法：**

在 application.yml 中添加以下配置

```yml
#路径匹配策略
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```
