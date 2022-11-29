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

当页面中嵌套了 iframe 时，在开始时对 iframe 进行隐藏，当点击时 iframe 展示，此时若时在一开始就让 iframe 随父页面一起加载，就会导致 iframe 获取到的页面的款高度都为 0，由此会导致 iframe 中的内容无法显示。

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
      $(function () {
        var text =
          '君不见，黄河之水天上来，奔流到海不复回。君不见，高堂明镜悲白发，朝如青丝暮成雪。人生得意须尽欢，莫使金樽空对月。天生我材必有用，千金散尽还复来。烹羊宰牛且为乐，会须一饮三百杯。岑夫子，丹丘生，将进酒，杯莫停。与君歌一曲，请君为我倾耳听。钟鼓馔玉不足贵，但愿长醉不复醒。古来圣贤皆寂寞，惟有饮者留其名。陈王昔时宴平乐，斗酒十千恣欢谑。主人何为言少钱，径须沽取对君酌。五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。'
        if ('speechSynthesis' in window) {
          var msg = new SpeechSynthesisUtterance()
          var voices = window.speechSynthesis.getVoices()
          msg.voice = voices[0]
          if (msg.voice.lang != 'zh-CN') {
            //语言种类
            $(voices).each(function (i, n) {
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

speechSynthesis.speak() without user activation is no longer allowed since M71, around December 2018. See https://www.chromestatus.com/feature/5687444770914304 for more details

语音合成在线演示网址：

https://codepen.io/SteveJRobertson/pen/emGWaR

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
