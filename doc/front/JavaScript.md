## 笔记

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

当页面中嵌套了 iframe 时，在开始时对 iframe 进行隐藏，当点击时 iframe 展示，此时若时在一开始就让 iframe 随父页面一起加载，就会导致 iframe 获取到的页面的款高度都为
0，由此会导致 iframe 中的内容无法显示。

div 隐藏后显示的问题同理。所以，一个页面的展示不仅仅是代码对不对，还有方法的调用时间问题。

5. 数组截取
   `array.slice(a，b)` 截取`[a,b)`位的数组内容

6. Object.entries()

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

## 语音合成

```html
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8" />
  <title></title>
  <script src="js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    $(function() {
      var text =
        '君不见，黄河之水天上来，奔流到海不复回。君不见，高堂明镜悲白发，朝如青丝暮成雪。人生得意须尽欢，莫使金樽空对月。天生我材必有用，千金散尽还复来。烹羊宰牛且为乐，会须一饮三百杯。岑夫子，丹丘生，将进酒，杯莫停。与君歌一曲，请君为我倾耳听。钟鼓馔玉不足贵，但愿长醉不复醒。古来圣贤皆寂寞，惟有饮者留其名。陈王昔时宴平乐，斗酒十千恣欢谑。主人何为言少钱，径须沽取对君酌。五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。'
      if ('speechSynthesis' in window) {
        var msg = new SpeechSynthesisUtterance()
        var voices = window.speechSynthesis.getVoices()
        msg.voice = voices[0]
        if (msg.voice.lang != 'zh-CN') {
          //语言种类
          $(voices).each(function(i, n) {
            if (n.lang == 'zh-CN') {
              console.log(voices[0].voiceURI)
              msg.voice = n
              return false
            }
          })
        }
        console.log(msg.voice.voiceURI)
        msg.rate = 1 //语速
        msg.pitch = 1 //语调
        msg.text = text
        speechSynthesis.speak(msg)
      } else {
        alert('你的浏览器不支持语音合成')
      }
    })
  </script>
</head>
<body></body>
</html>
```

对于此语音合成对象：

`new SpeechSynthesisUtterance();`//获取文本转换后的语音对象

`lang: (...)`//语言

`onboundary: (...)`

`onend: (...)`//结束

`onerror: (...)`//错误

`onmark: (...)`

`onpause: (...)`//暂停

`onresume: (...)`//继续

`onstart: (...)`//开始

`pitch: (...)`//语调

`rate: (...)`//语速

`text: (...)`//文本

`voice: (...)`//语音服务对象

`volume: (...)`//音量

`window.speechSynthesis;`//获取语音合成对象

`cancel: ƒ cancel()`//取消

`getVoices: ƒ getVoices()`//得到语音服务对象

`onvoiceschanged: (...)`//

`pause: ƒ pause()`//暂停

`paused: (...)`//暂停

`pending: (...)`

`resume: ƒ resume()`//继续

`speak: ƒ speak()`//开始播放

`speaking: (...)`//播放标志

`constructor: ƒ SpeechSynthesis()`

`window.speechSynthesis.getVoices();`//语音服务对象

`default: (...)`//是否默认

`lang: (...)`//语言

`localService: (...)`//是否为本地服务

`name: (...)`//名称

`voiceURI: (...)`//对象 uri

语音合成有关问题：

speechSynthesis.speak() without user activation is no longer allowed since M71, around December 2018.
See https://www.chromestatus.com/feature/5687444770914304 for more details

语音合成在线演示网址：

https://codepen.io/SteveJRobertson/pen/emGWaR

## 代码风格

基本设置
4空格缩进
UTF-8 编码
严格模式
建议打开严格模式，但不要依赖严格模式中语言特性的变化（仅当需要支持 IE8、IE9 时）

'use strict';
引号
使用单引号，这样可以跟 HTML 的双引号更好的一起工作。

分号
在语句（Statement）的结尾加分号

// 不建议
var fn = function() {
// Long code
} // 没有分号

// 建议
var fn = function() {
// Long code
}; // 这里有分号
以防万一，在可能有坑的地方手工加分号

var f1 = function ff1() {
return function() {
return 1;
};
} // 此处漏写分号
(function() { // 此处调用了上面的ff1，WAT
})();
console.log(f2); // 1

var f2 = function ff2() {
return function() {
return 1;
};
} // 此处漏写分号
// IIFE
;(function() { // 注意前面的分号
})();
console.log(f2); // function
或者使用 void function() {}() 的写法，见下

空白与格式
在二元和三元运算符的符号与操作数之间添加空格，在非行末的 , ; } 后添加空格，在 { 前添加空格。并在每个逻辑块中间添加空白行。 特别的，在 if、while
等关键字后加空格，与函数调用做区分

// 不推荐
var foo='bar',hello=foo+2,test=true;
function hi(){
// ...
}
if(foo&&hello){
// ...
}else if(foo){
// ...
}else if(! test){
// ...
}

// 推荐
var foo = 'bar';
var hello = foo + 2;
var test = true;

function hi(arg1, arg2) {
// ...
}

if (foo && hello) {
// ...
} else if (foo) {
// ...
} else if (!test) {
// ...
}
注释
使用 // 作为注释符，可以使用 /* */ 作为多行注释符。注释符号与注释内容之间留空，注释的位置尽量放在代码之上：

/*不推荐*/
//不推荐
; // 不推荐

/* 推荐 */
// 推荐
;
建议在今后需要完善的代码中注释以 // TODO。请在 TODO 后标注你要做的事

if (condition) {
console.log('尚未实现');
// TODO: condition 成立时需要做额外的工作
}
不要为大括号另开一行
// 不推荐
if (foo)
{
// ...
}

// 推荐
if (foo) {
// ...
}

// 不允许
return
{
a: 1
};

// 一定要
return {
a: 1
};
只有一行语句时允许不带括号，但需把语句紧跟当前行后

if (foo) doSomething();
for (var i = 0; i < 10; i++) doSomething();
语句太长时写成两行，这时请加大括号

// 不推荐
for (var i = 0; i < 10; i++)
doSomething(aaaa, bbbb);

// 推荐
for (var i = 0; i < 10; i++) {
doSomething(aaaa, bbbb);
}

// 因为某些人调试时喜欢注释代码，于是就出现了
for (var i = 0; i < 10; i++)
// doSomething(aaaa, bbbb);
写 else 时不要另起一行

// 不推荐
if (test) {
things1();
things2();
}
else {
things3();
}

// 推荐
if (test) {
things1();
things2();
} else {
things3();
}
var 语句
使用变量之前必须先定义，不要定义全局变量。
// 变量 undefinedVar 从未定义过
undefinedVar = 1; // 严格模式中报错
console.log(global.undefinedVar); // 1

// 鉴于 js 中 var 的坑，可以在函数头统一定义所有变量（不强制，我也讨厌把变量定义堆一起。。。）
void function () {
for (var i = 0; i < arr.length; ++i) {
(function () {
console.log(i); // undefined

      // 很长的代码，你已经忘记了之前做了什么
      for (var i = 0; i < 10; ++i) {
        // Do some other things
      }
    })();

}
}();

// 小心 var 与 closure 合用时的坑
var elements = [ div1, div2, div3 ];
for (var i = 0; i < elements.length; ++i) {
elements[i].addEventListener('event', function() {
console.log(i); // 3
});
}
如果变量有初始赋值则使用单独的 var：
// 不推荐
var hello = 1, world = 2;

// 推荐
var hello = 1;
var world = 2;
var foo, fee, fxx;
另外强调，不推荐使用下面这种风格的变量定义方式。

// 不推荐
var hello = arr.pop(),
world = arr.pop();
// 不推荐
var hello = arr.pop()
, world = arr.pop();
原因：

当需要修改变量定义顺序时不容易做整行移动
过不了 eslint 的 indent 验证
变量的命名
使用以小写字母开头的驼峰命名（camelCase）法：

// 不推荐
var foo_bar = 'hello eleme';

// 推荐
var fooBar = 'hello eleme';
常量大写
// 不推荐
var prefix = 'http://api.ele.me/v1/';
var Prefix = 'http://api.ele.me/v1/'

// 推荐
var PREFIX = 'http://api.ele.me/v1/';
使用字面量
// 不建议
var obj = new Object();
var array = new Array();

// 推荐
var obj = {};
var array = [];

// 鉴于 Array 构造函数的特殊性，不建议
var arr1 = new Array(4, 5, 6); // [4, 5, 6]

// 以免与下面混淆
var arr2 = new Array(4); // [ undefined * 4 ]
// 等价于（不推荐）
var arr3 = [];
arr3.length = 4;
// 等价于（不推荐）
var arr4 = [,,,,];
console.log('0' in arr2, '0' in arr3, '0' in arr4); // false, false, false

// 不推荐
var str = new String('str');
console.log(str === 'str'); // false

var bool = new Boolean(false);
if (bool) console.log('wat'); // wat

// 当真需要使用字面量包装类时，使用显式强制转换（请先三思）
var strObject = Object('str');
strObject.customProperty = someValue;
比较
建议使用 ===/!== 而非 ==/!=。

// 不推荐
function foo(a) {
if (a == 123) {
// ...
}
}

// 推荐
function foo(a) {
a = Number(a);
if (a === 123) {
// ...
}
}
== 的规则比较复杂，大家可能记不住。

var a = '';

// false
if (a === 0);

// true
if (a == 0);
对于可能不存在的全局引用可以先做如此判断：

if (typeof localStorage !== 'undefined') {
// 此时访问 localStorage 绝对不会出现引用错误
}
或者

if ('localStorage' in self) {
// 此时访问 localStorage 绝对不会出现引用错误
}
但注意它们的区别

var a = undefined;

// 判断一个全局变量是否有声明
'a' in self; // true

// 判断一个变量是否为 undefined 并将未声明的引用作为 undefined 处理
typeof a !== 'undefined'; // false
避免无必要的 if 语句、三元运算符
var arr = [1, 2, 3];

// 不推荐
var flag1 = arr.length > 0 ? true : false;

// 不推荐
var flag2;
if (arr.length > 0) {
flag2 = true;
} else {
flag2 = false;
}

// 推荐
var flag3 = arr.length > 0;
合理的格式化三元运算符
// 不推荐
var flag1 = veryLooooooooooonnnnggggggCondition ? resultWhenTruth : resultWhenFalsy;

// 推荐
var flag2 = veryLooooooooooonnnnggggggCondition
? resultWhenTruth
: resultWhenFalsy;
复杂逻辑中建议使用显式转换
+num === Number(num);
!!bool === Boolean(bool);
str + '' === String(str);

// 特别的
if (bool)
// 等价于
if (Boolean(bool))
// 故
if ([]) console.log('true'); // true
// 而
if ([] === true) console.log('true'); // 无输出

// 另外
if (Boolean(String(false))) console.log('true'); // true
// 这点在保存 localStorage 时需要注意
不要使用 parseInt 做整数转换，如需使用 parseInt，请给它传入第二个参数 10，避免老式浏览器的坑（IE8？）

var floatValue = 123.456;

// 不要
var intValue = parseInt(floatValue);

// 可以用
var intValue2 = floatValue | 0;

// 更显然的
var intValue3 = Math.floor(floatValue);
特别地，使用 parseFloat 做部分转换

// 例如有：
// <div id="div" style="width: 10px"></div>

var divWidth = getComputedStyle(document.getElementById('div')).width; // '10px'

console.log(parseFloat(divWidth)); // 10
console.log(Number(divWidth)); // NaN
console.log(+divWidth); // NaN
函数定义
建议使用表达式来定义函数，而不是函数语句。

// 不推荐
function fee() {
// ...
}

// 推荐
var foo = function() {
// ...
};
因为函数语句是在进入作用域时声明，破坏了程序从上到下的执行顺序。可能出现定义在 return 后的情况。

void function() {
// 此处可以正常使用函数，但逻辑不清晰
foo();

return null;

function foo() {};
}();
只引用一次的函数建议匿名定义，因为名称存在主观因素。

// 不推荐
var foo = function() {
// ...
};
element.onclick = foo;

// 推荐
element.onclick = function() {
// ...
};
自执行函数
// 不推荐
(function() {
// ...
})();

+function() {
// ...
}();

// 推荐
~function() {
// ...
}();

// 推荐
void function() {
// ...
}();
括号和加号不是上下文无关的，可能受到上文缺分号的影响而出现奇怪的问题，这些问题甚至不会报错，极难调试，所以不推荐此种用法，比如：

var a = 1 // 此处无分号

+function() {
return 2
}();

// 此处 a 的值为 3
避免嵌套过深
可以使用 Promise 解决深层嵌套问题：

// 不推荐
async1(function() {
// TODO 1
async2(function() {
// TODO 2
async3(function() {
// TODO 3
});
});
});

// 推荐
Promise.resolve()
.then(function() {
return new Promise(function(resolve) {
async1(resolve);
});
})
.then(function() {
// TODO 1
return new Promise(function(resolve) {
async2(resolve);
});
})
.then(function() {
// TODO 2
return new Promise(function(resolve) {
async3(resolve);
});
})
.then(function() {
// TODO 3
});
禁止事项
禁止使用未定义的变量
禁止使用 eval，非用不可时可以使用 Function 构造器替代。
禁止使用 with 语句。
禁止在块作用域中使用函数声明语句。
if (true) {
// 禁止
function func1() {
// ...
}
// 允许
var func2 = function() {
// ...
};
}
禁止使用 8 进制词法
// true
if (010 === 8);
禁止使用 arguments 映射
void function(a) {
arguments[0]++;
// 此处 a 为 2
}(1);
禁止使用重名参数
禁止使用保留字做变量名如 interface 等

## 常见异常

### Uncaught TypeError: Cannot read property

常见于在渲染 UI 组件时对于状态的初始化操作不当

### (unknown): Script error

被浏览器的跨域策略限制时，会产生这类的脚本错误

### TypeError: ‘undefined' is not a function

调用未定义的函数时

### Uncaught RangeError: Maximum call stack

调用一个不终止的递归函数;js 库冲突

### TypeError: Cannot read property ‘length'

读取未定义变量的长度属性

### Uncaught TypeError: Cannot set property

获取或设置一个未定义变量的任何未定义的属性

### ReferenceError: event is not defined

访问未定义的变量或超出当前范围的变量

### Uncaught SyntaxError: Unexpected identifier

语法错误，缺少符号

### Uncaught SyntaxError: Unexpected token ILLEGAL

语法错误，引号不匹配

### Uncaught SyntaxError: Unexpected token o

JSON.parse(s)中的 s 不是字符串

