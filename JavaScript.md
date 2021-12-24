# 知识

1. `==`比较的是值，`===`比较的是数据类型和值

2. this 是什么(只在浏览器中，不含 Node)

> 在全局中 this 是 window；
>
> 在非严格模式下的函数（包括无参、有参、自执行）this 是 window；
>
> 谁调用，this 是谁；
>
> 通过 new 的对象中是对象实例（var p = new P();function P(){this} this 是 p）；
>
> 在严格模式中如不进行定义，this 则为 undefined；

3. JSON 操作

> $.parseJSON( jsonstr ); //jQuery.parseJSON(jsonstr),可以将 json 字符串转换成 json 对象
>
> JSON.parse(jsonstr); //可以将 json 字符串转换成 json 对象
>
> JSON.stringify(jsonobj); //可以将 json 对象转换成 json 对符串


4. 页面加载

当页面中嵌套了 iframe 时，在开始时对 iframe 进行隐藏，当点击时 iframe 展示，此时若时在一开始就让 iframe 随父页面一起加载，就会导致 iframe 获取到的页面的款高度都为 0，由此会导致 iframe 中的内容无法显示。

div 隐藏后显示的问题同理。所以，一个页面的展示不仅仅是代码对不对，还有方法的调用时间问题。

1. .slice(a，b) 截取[a,b)位的数组内容

## 1.多条件的 IF 语句

我们可以将多个条件值存储在一个数组中，并且使用数组的`includes`方法来进行条件判断。

```
//普通写法
if (x === 'abc' || x === 'def' || x === 'ghi' || x ==='jkl') {
    //logic
}
//优雅写法
if (['abc', 'def', 'ghi', 'jkl'].includes(x)) {
   //logic
}
```

## 2.If true … else 条件简写

当`if-else`语句中不包含复杂逻辑时，下面这种写法将是一个不错的捷径。我们可以简单地使用三元运算符来实现。

```
// 普通写法
let test: boolean;
if (x > 100) {
    test = true;
} else {
    test = false;
}
// 优雅写法
let test = (x > 10) ? true : false;
//or we can simply use
let test = x > 10;
console.log(test);
```

如果是嵌套条件语句，我们还可以这样写：

```
let x = 300,
let test2 = (x > 100) ? 'greater 100' : (x < 50) ? 'less 50' : 'between 50 and 100';
console.log(test2); // "greater than 100"
```

## 3.空值, 未定义和空字符的检查

当我们创建一个新的变量，有时候会在引用它时检查该变量是否为`null`或者`undefined`。JavaScript 确实提供了实现这个功能的简便方法。

```
// 普通写法
if (first !== null || first !== undefined || first !== '') {
    let second = first;
}
// 优雅写法
let second = first|| '';
```

## 4.Null 检查，以及赋默认值

```
let first = null,
let second = first || '';
console.log("null check", test2); // output will be ""
```

## 5.Undefined 检查，以及赋默认值

```
let first= undefined,
let second = first || '';
console.log("undefined check", test2); // output will be ""
```

## 6.foreach 循环的简写

这是 JavaScript 迭代循环的简写方式。

```
// 普通写法
for (var i = 0; i < testData.length; i++)

// 优雅写法
for (let i in testData) or  for (let i of testData)
```

## 7.比较后返回值

在 return 语句中使用比较函数，可以将 5 行代码压缩至 1 行。

```
// 普通写法
let test;
function checkReturn() {
    if (!(test === undefined)) {
        return test;
    } else {
        return callMe('test');
    }
}
var data = checkReturn();
console.log(data); //output test
function callMe(val) {
    console.log(val);
}
// 优雅写法
function checkReturn() {
    return test || callMe('test');
}
```

## 8.函数调用的简单写法

我们可以使用三元运算符实现这些类型的功能。

```
// 普通写法
function test1() {
  console.log('test1');
};
function test2() {
  console.log('test2');
};
var test3 = 1;
if (test3 == 1) {
  test1();
} else {
  test2();
}
// 优雅写法
(test3 === 1? test1:test2)();
```

## 9.Switch 语句的简单写法

我们可以将条件存储在`key-value`的字典对象中，然后通过字典对象的索引来判断取值。

```
// Longhand
switch (data) {
  case 1:
    test1();
  break;

  case 2:
    test2();
  break;

  case 3:
    test();
  break;
  // And so on...
}

// Shorthand
var data = {
  1: test1,
  2: test2,
  3: test
};

data[anything] && data[anything]();
```

## 10.多行字符串的简写

当我们在代码中处理多行字符串时，我们可以这样做：

```
//普通写法
const data = 'abc abc abc abc abc abc\n\t'
    + 'test test,test test test test\n\t'
//优雅写法
const data = `abc abc abc abc abc abc
         test test,test test test test`
```

## 11.隐式返回的简写

我们可以使用箭头函数直接返回值，而不用写`return`语句。

```
Longhand:
//longhand
function getArea(diameter) {
  return Math.PI * diameter
}
//shorthand
getArea = diameter => (
  Math.PI * diameter;
)
```

## 12.条件查找的简写

如果我们的代码中需要检查变量的类型，然后基于不同类型调用不同的方法，那么我们除了使用`if-else`语句或者`switch`语句来判断外，还可以使用更简单的方法吗？

```
// 普通写法
if (type === 'test1') {
  test1();
}
else if (type === 'test2') {
  test2();
}
else if (type === 'test3') {
  test3();
}
else if (type === 'test4') {
  test4();
} else {
  throw new Error('Invalid value ' + type);
}
// 优雅写法
var types = {
  test1: test1,
  test2: test2,
  test3: test3,
  test4: test4
};

var func = types[type];
(!func) && throw new Error('Invalid value ' + type); func();
```

## 13.Object.entries()

这个特性可以将一个对象转换成一个对象数组。

```
const data = { test1: 'abc', test2: 'cde', test3: 'efg' };
const arr = Object.entries(data);
console.log(arr);
/** Output:
[ [ 'test1', 'abc' ],
  [ 'test2', 'cde' ],
  [ 'test3', 'efg' ]
]
**/
```

## 14.Object.values()

这也是 ES8 的一个新特性，它和`Object.entries()`的功能类似，但是返回结果中没有 key 值。

```
const data = { test1: 'abc', test2: 'cde' };
const arr = Object.values(data);
console.log(arr);
/** Output:
[ 'abc', 'cde']
**/
```

## 15.多次重复字符串

为了重复操作同一个字符串，我们可以在同一个`for`循环中将它们连接起来，那么有没有更好的方法呢？

```
//普通写法
let test = '';
for(let i = 0; i < 5; i ++) {
  test += 'test ';
}
console.log(str); // test test test test test
//优雅写法
'test '.repeat(5);
```

## 16.Math.pow()简写

```
//普通写法
Math.pow(2,3); // 8
//优雅写法
2**3 // 8
```

## 17.数字分隔符

现在，我们只需使用`_`即可轻松分隔数字，这可以帮助开发者更容易处理较大的数字。

```
//老语法
let number = 98234567

//新语法
let number = 98_234_567
```

## 18.语音合成

```
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script src="js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
            $(function() {
                var text = '君不见，黄河之水天上来，奔流到海不复回。君不见，高堂明镜悲白发，朝如青丝暮成雪。人生得意须尽欢，莫使金樽空对月。天生我材必有用，千金散尽还复来。烹羊宰牛且为乐，会须一饮三百杯。岑夫子，丹丘生，将进酒，杯莫停。与君歌一曲，请君为我倾耳听。钟鼓馔玉不足贵，但愿长醉不复醒。古来圣贤皆寂寞，惟有饮者留其名。陈王昔时宴平乐，斗酒十千恣欢谑。主人何为言少钱，径须沽取对君酌。五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。';
                if('speechSynthesis' in window) {
                    var msg = new SpeechSynthesisUtterance();
                    var voices = window.speechSynthesis.getVoices();
                    msg.voice = voices[0];
                    if(msg.voice.lang != "zh-CN") { //语言种类
                        $(voices).each(function(i, n) {
                            if(n.lang == "zh-CN") {
                                console.log(voices[0].voiceURI)
                                msg.voice = n;
                                return false;
                            }
                        });
                    }
                    console.log(msg.voice.voiceURI)
                    msg.rate = 1; //语速
                    msg.pitch = 1; //语调
                    msg.text = text;
                    speechSynthesis.speak(msg);
                } else {
                    alert('你的浏览器不支持语音合成');
                }
            });
        </script>
    </head>
    <body>
    </body>
</html>
```

![image.gif](img/1593759022390-4fb106c4-b71e-4537-8dc3-eff0ed871c13.gif)

对于此语音合成对象：

new SpeechSynthesisUtterance();//获取文本转换后的语音对象

lang: (...)//语言

onboundary: (...)

onend: (...)//结束

onerror: (...)//错误

onmark: (...)

onpause: (...)//暂停

onresume: (...)//继续

onstart: (...)//开始

pitch: (...)//语调

rate: (...)//语速

text: (...)//文本

voice: (...)//语音服务对象

volume: (...)//音量

window.speechSynthesis;//获取语音合成对象

cancel: ƒ cancel()//取消

getVoices: ƒ getVoices()//得到语音服务对象

onvoiceschanged: (...)//

pause: ƒ pause()//暂停

paused: (...)//暂停

pending: (...)

resume: ƒ resume()//继续

speak: ƒ speak()//开始播放

speaking: (...)//播放标志

constructor: ƒ SpeechSynthesis()

window.speechSynthesis.getVoices();//语音服务对象

default: (...)//是否默认

lang: (...)//语言

localService: (...)//是否为本地服务

name: (...)//名称

voiceURI: (...)//对象 uri

语音合成有关问题：

speechSynthesis.speak() without user activation is no longer allowed since M71, around December 2018. See https://www.chromestatus.com/feature/5687444770914304 for more details

语音合成在线演示网址：

https://codepen.io/SteveJRobertson/pen/emGWaR

## 19.常见异常

- provisional headers are shown
- Uncaught TypeError: Cannot read property 常见于在渲染 UI 组件时对于状态的初始化操作不当
- (unknown): Script error 被浏览器的跨域策略限制时，会产生这类的脚本错误
- TypeError: ‘undefined' is not a function 调用未定义的函数时
- Uncaught RangeError: Maximum call stack 调用一个不终止的递归函数;js 库冲突
- TypeError: Cannot read property ‘length' 读取未定义变量的长度属性
- Uncaught TypeError: Cannot set property 获取或设置一个未定义变量的任何未定义的属性
- ReferenceError: event is not defined 访问未定义的变量或超出当前范围的变量
