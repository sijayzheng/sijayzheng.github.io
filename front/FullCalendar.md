## fullcalendar 基本目录结构

> core _核心_
>
> main.js
>
> main.css
>
> locales-all.js _本地化_
>
> daygrid _日视图_
>
> main.js
>
> main.css
>
> interaction _交互_
>
> main.js
>
> list _列表视图_
>
> main.js
>
> main.css
>
> timegrid _时间视图_
>
> main.js
>
> main.css

## 最简单的日历初始化

只需要加载 core 和 daygrid 的 js 和 css 即可

```
<html lang="zh">
    <head>

    </head>
    <body>
        <div id='calendar'></div>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                var calendar = new FullCalendar.Calendar(document.getElementById('calendar'), {
                    plugins: ['dayGrid'],
                });
                calendar.render();
            });
        </script>
    </body>
</html>
```

## 文档

Fullcalendar 中文文档：通用显示

Fullcalendar 提供了丰富的选项参数设置，在日历显示的时候，可以通过设置参数、调用方法对日历视图进行各种外观设置，包括日历头部、主体部分等等各个细节都涉及到了。

header

用于定义日历头部的按钮和标题。默认代码是：

{

left: 'title',

center: '',

right: 'today prev,next'

}

查看 demo。

如果直接将 header 选项设置为 false，则不会显示头部内容。使用类似上面的一个对象，包含 left，center 和 right，值是字符串类型的，使用逗号或者空格分隔开，区别在于：空格分隔，按钮之间有间隙；逗号分隔，按钮之间没有间隙，即紧邻。字符串的值可以使用下面的：

title：包含当前月/周/日内容

prev：按钮，用于切换到上一 月/周/日

next： 按钮，用于切换到下一 月/周/日

prevYear：按钮，用于切换到上一年

nextYear ：按钮，用于切换到下一年

today：按钮，返回当前月/周/日

a View Name：用于切换到指定 View

如果为这些属性指定一个空字符串，则不会显示任何内容。

footer

footer 用于定义日历尾部内容，默认 false，即不显示尾部信息。footer 和上面的 header 一样，也支持 header 的属性参数。

customButtons

可以为 header 部分自定义按钮。自定义按钮，然后在 header 部分引用：

$('#calendar').fullCalendar({

customButtons: {

myCustomButton: {

text: '自定义按钮',

click: function() {

alert('点击了自定义按钮!');

}

}

},

header: {

left: 'prev,next today myCustomButton',

center: 'title',

right: 'month,agendaWeek,agendaDay'

}

});

查看 demo，可以看到日历头部多了一个自定义按钮，点击这个按钮试试。

每个自定义按钮都可以接受以下几个属性：

text：按钮本身显示的内容

click：点击按钮时触发的回调函数

icon：按钮图标，见 buttonIcons，可选

themeIcon ：见 themeButtonIcons，可选

bootstrapGlyphicon ：见 bootstrapGlyphicon，可选

buttonIcons

定义头部按钮显示的图标。object 对象形式，以下是默认值

{

prev: 'left-single-arrow',

next: 'right-single-arrow',

prevYear: 'left-double-arrow',

nextYear: 'right-double-arrow'

}

这些属性值都是 Fullcalendar 提供的 CSS 名称，这些 css 名称绑定了字体图标。

如果不想显示任何图标，则可以将 buttonIcons 选项设置为 false。

themeSystem

指定日历使用哪种风格皮肤。有以下三种皮肤供选择：

standard：默认，Fullcalendar 自带皮肤

bootstrap3：使用 bootstrap3 风格，需要下载 css 样式

jquery-ui：使用 jquery-ui 风格，需要下载对应的 css 样式

此外，Fullcalendar 还支持 Bootstrap 其他第三方风格。

$('#calendar').fullCalendar({

themeSystem: 'bootstrap3' // 使用 bootstrap3 风格

})

查看 demo。

themeButtonIcons

当使用 jQuery UI 风格时，设置 header 部分的按钮图标。

{

prev: 'circle-triangle-w',

next: 'circle-triangle-e',

prevYear: 'seek-prev',

nextYear: 'seek-next'

}

以上设置要生效，必须设置 themeSystem: 'jquery-ui'。

如果你不想使用 themeButtonIcons，可以将 themeButtonIcons 设置为<false。

bootstrapGlyphicons

当使用 Bootstrap 风格时，设置 header 部分的按钮图标。

{

close: 'glyphicon-remove',

prev: 'glyphicon-chevron-left',

next: 'glyphicon-chevron-right',

prevYear: 'glyphicon-backward',

nextYear: 'glyphicon-forward'

}

以上设置要生效，必须设置 themeSystem: 'bootstrap3'。

如果你使用了 bootstrp3，而不想使用 themeButtonIcons，可以将 themeButtonIcons 设置为<false。

firstDay

设置每周第一天，数字 int 型，默认 0（周日）

firstDay 的值必须代表一周中的某一天，0=Sunday，1=Monday，2=Tuesday 以此类推

如果把 weekNumberCalculation 的值设为'ISO'，则默认值是 1（星期一）。

isRTL

将日历展示为从右向左显示。默认 false。这个选项主要用于一些如阿拉伯语的书写习惯。

weekends

用来设置是否在日历中显示周末，默认为 true。

hiddenDays

在日历中不显示一周中的某几天，数组形式，从 0-6 分别表示周日（Sunday）-周一（Monday），如：

hiddenDays: [ 2, 4 ] // 隐藏周二和周四

hiddenDays: [ 1, 3, 5 ] // 隐藏周一、周三和周五

默认不隐藏任何一天，除非 weekends 设置成 false。

columnHeader

在周/日试图中是否显示列头部的标题信息，默认 true。

fixedWeekCount

设置月试图时显示的周数，默认 true。如果是 true 则每次固定显示 6 周，如果设置为 false，则每月根据实际周数显示 4，5，6 周。

weekNumbers

是否在日历中显示第几周，默认 false。如果设置成 true，则会在月/基本视图中左边一列显示周数。

weekNumbersWithinDays

在月/基本视图中显示周数的样式，默认 false。

weekNumberCalculation

设置用于计算和显示周数的方法，默认是"local"，也可以设置成"ISO"，如果设置成 ISO 后，默认的每周第一天是 1（星期一）

businessHours

强调日历中的某些时间段，比如默认情况下，工作日周一-周五上午 9 点-下午 5 点。

你也可以自定义要强调的时间段：

businessHours: {

dow: [ 1, 2, 3, 4 ], // 周一 - 周四

start: '10:00', // 上午 10 点开始

end: '18:00', // 下午 18 点结束

}

你也可以定义多个不同日期不同时间段：

businessHours: [

{

dow: [ 1, 2, 3 ], // 周一 - 周三

start: '08:00', // 8am

end: '18:00' // 6pm

},

{

dow: [ 4, 5 ], // 周四，周五

start: '10:00', // 10am

end: '16:00' // 4pm

}

]

查看 demo。

showNonCurrentDates

在月视图中，是否显示上个月和下个月的日期，默认 true。

height

设置整个日历的高度（包括头），值为数字整型、函数、'parent'、'auto'。默认情况下，此值未设置，日程表的高度由 aspectRatio 决定。

如果设置了一个整数，日历的高度将被固定，如果内容太多，则会出现滚动条。

如果指定了一个函数，每次请求高度更新时都会调用这个函数。这个函数应该返回一个像素值。

如果设置为'parent'，日历的高度将会与父容器元素匹配。

如果设置为'auto'，日历高度将会自动适用高度，呈现自然高度并且没有滚动条。

使用例子：

$('#calendar').fullCalendar({

height: 650

});

也可以在初始化之后动态的设置日历高度：

$('#calendar').fullCalendar('option', 'height', 700);

查看 demo。

contentHeight

设置日历内容的高度，值可以为数字，函数 Function，'auto'。

默认情况下，此值未设定，日程表的高度由 aspectRatio 决定。

p>如果设置了一个整数，日历的高度将被固定，如果内容太多，则会出现滚动条。

如果指定了一个函数，每次请求高度更新时都会调用这个函数。这个函数应该返回一个像素值。

如果设置为'auto'，日历高度将会自动适用高度，呈现自然高度并且没有滚动条。

使用例子：

$('#calendar').fullCalendar({

contentHeight: 600

});

在日历初始化之后，可以动态设置：

$('#calendar').fullCalendar('option', 'contentHeight', 650);

aspectRatio

设置日历的宽高比，值为浮点型，默认 1.35。日历是块级元素，会尽量撑满宽度，日程表的高度则有 aspectRatio 决定（提示：aspectRatio 的值越大，高度越小）。

下面的例子会显示一个宽为高两倍的日历：

$('#calendar').fullCalendar({

aspectRatio: 2

});

可以在日程表初始化之后动态的设置 aspectRatio：

$('#calendar').fullCalendar('option', 'aspectRatio', 1.8);

查看 demo。

handleWindowResize

用来设置当浏览器 Resize（窗口大小改变）的时候，自动调整日历的大小。值为布尔类型，默认值为 true。

当设置为 true 的时候，浏览器 Resize 的时候会自动调整日程表的大小，并且会触发 windowResize 回调。

windowResizeDelay

时间，单位毫秒，默认值 100。当调整浏览器窗口大小后，延时多少秒后日历重新调整大小。

使用 windowResizeDelay。目的是避免过于频繁的调整大小，用户拖动和缩放浏览器窗口。

eventLimit

限制显示在一天的事件数。值可以为布尔型和数字整型，默认 false。

当事件太多时，会显示一个看起来像“+ 2 更多”的链接。这发生在当用户点击链接的具体行动是由 eventlimitclick。

false 值（默认值）将显示所有事件。

true 值将将事件数限制为日单元格的高度。

整数值将将事件限制为特定行数。

对于议程视图中的全天部分，true 值将限制事件数为 5。要微调这一点，请使用如下特定于视图 View 的选项：

$('#calendar').fullCalendar({

eventLimit: true, // for all non-agenda views

views: {

agenda: {

eventLimit: 6 // adjust to 6 only for agendaWeek/agendaDay

}

}

});

eventLimitClick

当用户点击“更多”链接时发生的动作，支持"popover", "week", "day", view name, function。

"popover"，默认，在单元格上显示一个矩形面板，并显示当天的全部事件列表。

"week"，进入周视图。

"day"，进入日视图。

view name，进入定义的任意可用视图。

function，触发回调函数。

viewRender

回调函数，当新的日期区间渲染后或者视图切换后触发：

function( view, element )

view：新视图的 View Object 对象

element：Jquery 对象，新视图的容器

当用户切换视图的时候，或者任何 Current Date 章节下的方法被调用的时候触发。

此函数会在视图被完全渲染后，日历时间被渲染前触发（见：eventAfterAllRender）。

viewDestroy

回调函数，当一个时间区间被隐藏的时候触发：

function( view, element )

view：被隐藏的视图的 View Object 对象

element：Jquery 对象，视图的容器

此回调函数会被立即触发在视图从 DOM 元素中移除之前。包括月/周/日之间切换，更换时间区间，或者 destroy 方法被调用。

dayRender

回调函数，当日单元格被修改时触发。

function( date, cell ) { }

此回调方法是在月视图、基本周视图和基本月视图下修改日单元时触发。

date：给定日期的 moment

windowResize

当浏览器 Resize 窗口大小变化引起日程表 Resize 后，会触发 windowResize 回调函数。

function( view ) { }

当 windowResize 触发后，日历的大小会根据浏览器大小自适应改变。view 是当前的 View Object。

在函数体内，this 会被设置为当前日历的 DOM 元素。

$('#calendar').fullCalendar({

windowResize: function(view) {

alert('日历窗口随浏览器窗口变化 resize');

}

});

render

立即渲染日历或者调整它的大小，用法：

.fullCalendar( 'render' )

这个方法的使用场景主要是标签页切换，当标签页显示的时候调用此方法显示日程表。这是 jQuery UI tab plugin 的例子：

$('#my-tabs').tabs({

activate: function(event, ui) {

$('#calendar').fullCalendar('render');

}

});

需要注意的是，上例中，无论哪个标签页显示，都会调用 render 方法，这是没有问题的，因为 FullCalendar 足够智能，只会在显示给用户的时候执行 render。

destroy

将 FullCalendar 的容器 DOM 元素恢复到初始化之前的状态。

.fullCalendar( 'destroy' )

移除元素，事件和内部数据。
