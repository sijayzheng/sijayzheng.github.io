# jQuery() 简写为$()

## 1. 选择器

$(selector)

`selector`取值为 css 选择器`+`xPath

可使用 jQuery 选择器将 DOM 对象转为 jQuery 对象

如下例中的`e2`和`e3`是相等的

```
<div id="d1"></div>
<script>
    var e1 = document.getElementById('d1');
    var e2 = $(e1);
    var e3 = $('#d1');
</script>
```

- **基本选择器**

```
$("#id")            //ID选择器
$("div")            //元素选择器
$(".classname")     //类选择器
$(".classname,.classname1,#id1")     //组合选择器("#id")            //ID选择器
$("div")            //元素选择器
$(".classname")     //类选择器
$(".classname,.classname1,#id1")     //组合选择器
```

- **层次选择器**

```
$("#id>.classname ")    //子元素选择器
$("#id .classname ")    //后代元素选择器
$("#id + .classname ")    //紧邻下一个元素选择器
$("#id ~ .classname ")    //兄弟元素选择器
```

- **过滤选择器(重点)**

```
$("li:first")    //第一个li
$("li:last")     //最后一个li
$("li:even")     //挑选下标为偶数的li
$("li:odd")      //挑选下标为奇数的li
$("li:eq(4)")    //下标等于4的li
$("li:gt(2)")    //下标大于2的li
$("li:lt(2)")    //下标小于2的li
$("li:not(#runoob)") //挑选除 id="runoob" 以外的所有li("li:first")    //第一个li
$("li:last")     //最后一个li
$("li:even")     //挑选下标为偶数的li
$("li:odd")      //挑选下标为奇数的li
$("li:eq(4)")    //下标等于4的li
$("li:gt(2)")    //下标大于2的li
$("li:lt(2)")    //下标小于2的li
$("li:not(#runoob)") //挑选除 id="runoob" 以外的所有li
```

**内容过滤选择器**

```
$("div:contains('Runob')")    // 包含 Runob文本的元素
$("td:empty")                 //不包含子元素或者文本的空元素
$("div:has(selector)")        //含有选择器所匹配的元素
$("td:parent")                //含有子元素或者文本的元素("div:contains('Runob')")    // 包含 Runob文本的元素
$("td:empty")                 //不包含子元素或者文本的空元素
$("div:has(selector)")        //含有选择器所匹配的元素
$("td:parent")                //含有子元素或者文本的元素
```

**可见性过滤选择器**

```
$("li:hidden")       //匹配所有不可见元素，或type为hidden的元素
$("li:visible")      //匹配所有可见元素("li:hidden")       //匹配所有不可见元素，或type为hidden的元素
$("li:visible")      //匹配所有可见元素
```

**属性过滤选择器**

```
$("div[id]")        //所有含有 id 属性的 div 元素
$("div[id='123']")        // id属性值为123的div 元素
$("div[id!='123']")        // id属性值不等于123的div 元素
$("div[id^='qq']")        // id属性值以qq开头的div 元素
$("div[id$='zz']")        // id属性值以zz结尾的div 元素
$("div[id*='bb']")        // id属性值包含bb的div 元素
$("input[id][name$='man']") //多属性选过滤，同时满足两个属性的条件的元素("div[id]")        //所有含有 id 属性的 div 元素
$("div[id='123']")        // id属性值为123的div 元素
$("div[id!='123']")        // id属性值不等于123的div 元素
$("div[id^='qq']")        // id属性值以qq开头的div 元素
$("div[id$='zz']")        // id属性值以zz结尾的div 元素
$("div[id*='bb']")        // id属性值包含bb的div 元素
$("input[id][name$='man']") //多属性选过滤，同时满足两个属性的条件的元素
```

**状态过滤选择器**

```
$("input:enabled")    // 匹配可用的 input
$("input:disabled")   // 匹配不可用的 input
$("input:checked")    // 匹配选中的 input
$("option:selected")  // 匹配选中的 option("input:enabled")    // 匹配可用的 input
$("input:disabled")   // 匹配不可用的 input
$("input:checked")    // 匹配选中的 input
$("option:selected")  // 匹配选中的 option
```

- **表单选择器**

```
$(":input")      //匹配所有 input, textarea, select 和 button 元素
$(":text")       //所有的单行文本框，$(":text") 等价于$("[type=text]")，推荐使用$("input:text")效率更高，下同
$(":password")   //所有密码框
$(":radio")      //所有单选按钮
$(":checkbox")   //所有复选框
$(":submit")     //所有提交按钮
$(":reset")      //所有重置按钮
$(":button")     //所有button按钮
$(":file")       //所有文件域(":input")      //匹配所有 input, textarea, select 和 button 元素
$(":text")       //所有的单行文本框，$(":text") 等价于$("[type=text]")，推荐使用$("input:text")效率更高，下同
$(":password")   //所有密码框
$(":radio")      //所有单选按钮
$(":checkbox")   //所有复选框
$(":submit")     //所有提交按钮
$(":reset")      //所有重置按钮
$(":button")     //所有button按钮
$(":file")       //所有文件域
```

## 2. 事件

`$(selector).eventname()`或者使用`$(selector).on('eventname',function(event){})`来绑定事件

eventname 如下：

| eventname  | 说明       |
| ---------- | ---------- |
| click      | 点击       |
| dblclick   | 双击       |
| mouseenter | 鼠标进入   |
| mouseleave | 鼠标离开   |
| hover      | 鼠标悬停   |
| keypress   | 按键       |
| keydown    | 按下键时   |
| keyup      | 按键弹起时 |
| focus      | 得焦       |
| blur       | 失焦       |
| change     | 值改变     |
| submit     | 提交       |
| resize     | 调整大小   |
| scroll     | 滚动       |

## 3. 效果

- `hide()` 隐藏元素
- `show()` 显示元素
- `toggle()` 切换元素的显示隐藏

## 4. DOM 操作

### 获取和设置内容或属性

| 方法     | 作用                                        |
| -------- | ------------------------------------------- |
| `text()` | 获取或设置 DOM 对象的内容(不含 html 标签)   |
| `html`   | 获取或设置 DOM 对象 html 内容(含 html 标签) |
| `val()`  | 获取或设置 DOM 对象的值                     |
| `attr()` | 获取或设置 DOM 对象的属性                   |

### 添加或删除元素

- `append()` 在被选元素的结尾插入内容
- `prepend()` 在被选元素的开头插入内容
- `after()` 在被选元素之后插入内容
- `before()` 在被选元素之前插入内容
- `remove()` 删除元素及其子元素
- `empty()` 删除其子元素

```
<div id="d1">
    <div id="div1"></div>
    <div id="div2"></div>
</div>
<div id="d2">
    <div></div>
    <div></div>
</div>
<div id="d3">
    <div></div>
    <div></div>
</div>
<script>
    $('#d1').append('<div id="dv1"></div>');
    $('#d1').prepend('<div id="dv2"></div>');
    $('#d1').after('<div id="dv3"></div>');
    $('#d1').before('<div id="dv4"></div>');
    $('#d2').remove();
    $('#d3').empty();
</script>
```

结果如下所示：

```
<div id="dv4"></div>
<div id="d1">
    <div id="dv2"></div>
    <div id="div1"></div>
    <div id="div2"></div>
    <div id="dv1"></div>
</div>
<div id="dv3"></div>
<div id="d3"></div>
```

### 设置 class 类及样式

- `addClass()` 向被选元素添加一个或多个类
- `removeClass()` 从被选元素删除一个或多个类
- `toggleClass()` 对被选元素进行添加/删除类的切换操作
- `css()` 设置或返回样式属性

## 5. 元素遍历

### 父

- `parent()` 直接父代
- `parents()` 所以祖先
- `parentsUntil()` 其到其某代祖先之间的所有祖先

### 子

- `children()` 直接子代
- `find()` 所有后代

### 兄弟

- `siblings()` 所有同胞
- `next()` 后一个同胞
- `nextAll()` 其后的所有同胞
- `nextUntil()` 其后两个之间的所有同胞
- `prev()` 上、前一个同胞
- `prevAll()` 其前的所有同胞
- `prevUntil()` 其前两个之间的所有同胞

### 过滤

- `first()` 其首个元素
- `last()` 其最后一个元素
- `eq()` 带有指定索引号的元素
- `filter()` 条件匹配的所有元素
- `not()` 条件不匹配的所有元素，与`filter()`作用相反

## 6. Ajax

```
load()` 从服务器请求数据并添加到元素中`$(selector).load(URL,data,callback);
```

如下为一个常用的 Ajax 请求：

```
$.ajax({
    url: 'url', //请求的 URL
    type: 'post', //请求的类型（GET 或 POST）。
    data: 'data', //要发送到服务器的数据。
    dataType: 'json', //服务器响应的数据类型。
    async: true, //是否异步处理。默认是 true。
    timeout: 30 * 1000, //超时时间
    success: function(result) {}, //成功时运行的函数
    errorfunction(result) {}, //失败要运行的函数
});
```

## 7. 其他

- `$.each(list,function(i,n){})` 遍历
- `$.inArray()` 在数组中查找指定值并返回它的索引值
- `$.isArray()` 判断指定参数是否是一个数组
- `$.isEmptyObject()` 检查对象是否为空（不包含任何属性）
- `$.isFunction()` 判断指定参数是否是一个函数
- `$.isNumeric()` 判断指定参数是否是一个数字值
- `$.now()` 当前时间
- `$.trim()` 去除字符串两端的空白字符
- `$.type()` 确定 JavaScript 内置对象的类型
- `.length` 数据大小

## jQuery 插件

```javascript
$.fn.studentindex_dashboard = function (id, options) {
  //options是前端页面通过ajax获取的json数据
  $(this).children().remove()
  options = $.extend(defaults, options)
  var dataObj = options.data
  var itemDiv = ''
  $.each(dataObj.datalist, function (i, n) {
    itemDiv += "<div><span>姓名:</span><span id='lnames'>" + n.names + '</span></div>'
  })
  $(this).append(itemDiv)
  return this
}
```
