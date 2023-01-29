## SpringBoot 启动顺序

配置属性、获取监听器，发布应用开始启动事件、初始化输入参数、配置环境，输出 banner、创建上下文、预处理上下文、刷新上下文(加载 tomcat 容器)、再刷新上下文、发布应用已经启动事件、发布应用启动完成事件

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
