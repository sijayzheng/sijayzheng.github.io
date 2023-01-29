## API

### 舞台(Stage)：

#### 属性：

- frames 播放的帧数，默认 24，0 为不自动绘制需调用 paint()方法触发，小于 0 表示键盘鼠标有动作才会重绘
- canvas canvas 对象的属性列表
- width 舞台宽度
- height 舞台高度
- mode 舞台模式，默认为 normal，【normal：可以点击选中单个节点（按住 Ctrl 可以选中多个），点中空白处可以拖拽整个画面；drag: 该模式下不可以选择节点，只能拖拽整个画面；select: 可以框选多个节点、可以点击单个节点；edit: 在默认基础上增加了：选中节点时可以通过 6 个控制点来调整节点的宽、高】
- childs 场景对象列表
- eagleEye 缩略图，eagleEye.vasible 是否打开缩略图，默认为 false
- wheelZoom 鼠标滚轮缩放操作比例，当为 null 时不进行缩放，当为负值时在缩放同时会进行位置的变换并旋转 180 度，数值大于 1 和小于 1 时缩放方向相反

#### 方法：

- add(Scene) 将场景添加进舞台
- remove(Scene) 将场景从舞台移除
- clear() 将所有的场景从舞台移除
- paint() 执行舞台的重绘
- zoom(scale) 缩放
- zoomOut(scale) 放大
- zoomIn(scale) 缩小
- centerAndZoom(scale) 缩放并居中显示所有元素
- toJson() 把当前对象的属性序列化成 json 数据

#### 事件：

- addEventListener(eventName, eventHandler) 监听事件，evevtName 取值有:click,dbclick,mousedown,mouseup,mouseover,mouseout,mousemove,mousedrag

- removeEventListener(eventName) 移除监听事件，eventName 取值同上

- removeAllEventListener 移除所有监听事件

- click(eventHandler) 监听鼠标单击事件

- dbclick(eventHandler) 监听鼠标双击事件

- mousedown(eventHandler) 监听鼠标按下事件

- mouseup(eventHandler) 监听鼠标松开事件

- mouseover(eventHandler) 监听鼠标进入事件

- mouseout(eventHandler) 监听鼠标离开事件

- mousemove(eventHandler) 监听鼠标移动事件

- mousedrag(eventHandler) 监听鼠标拖拽事件

### 场景(Scene)：

#### 属性：

- alpha 场景透明度，0-1，默认为 0 完全透明
- backgroundColor 背景颜色
- background 背景图片，设置后覆盖背景颜色设置
- visible 场景是否可见，默认为 true
- mode 默认为 normal，【normal：可以点击选中单个节点（按住 Ctrl 可以选中多个），点中空白处可以拖拽整个画面；drag: 该模式下不可以选择节点，只能拖拽整个画面；select: 可以框选多个节点、可以点击单个节点；edit: 在默认基础上增加了：选中节点时可以通过 6 个控制点来调整节点的宽、高】
- scaleX x 轴方向缩放比例，默认为 1
- scaleY y 轴方向缩放比例，默认为 1
- selectedElements 当前场景中被选中的元素对象
- translateX 水平场景偏移量
- translateY 垂直场景偏移量

#### 方法：

- show() 显示
- hide() 隐藏
- add(element) 把 element 对象添加到场景
- remove(element) 移除场景中的 element 对象
- clear() 移除场景中的所有元素
- getDisplayedElement() 获取场景中可见元素
- getDisplayedNodes() 获取场景中可见的节点对象
- findElements(cond) 查找场景中的对象

#### 事件：

- addEventListener(eventName, eventHandler) 监听事件，evevtName 取值有:click,dbclick,mousedown,mouseup,mouseover,mouseout,mousemove,mousedrag
- removeEventListener(eventName) 移除监听事件，eventName 取值同上
- removeAllEventListener 移除所有监听事件
- click(eventHandler) 监听鼠标单击事件
- dbclick(eventHandler) 监听鼠标双击事件
- mousedown(eventHandler) 监听鼠标按下事件
- mouseup(eventHandler) 监听鼠标松开事件
- mouseover(eventHandler) 监听鼠标进入事件
- mouseout(eventHandler) 监听鼠标离开事件
- mousemove(eventHandler) 监听鼠标移动事件
- mousedrag(eventHandler) 监听鼠标拖拽事件

### 容器(Container)：

#### 属性：

- alpha 节点透明度，取值 0-1
- borderColor 节点边框颜色，rgb
- borderRadius 节点边框圆角，任意数值，当不大于 0 时无圆角，当超过某一值时会导致节点变形
- borderWidth 节点边框宽度，任意数值，默认为 0，当值为负时边框会进入到节点内
- childDragable 子对象可拖动，默认为 true
- dragable 可拖动，默认为 true
- elementType 元素类型，场景为 scene，容器为 container，节点为 node，连线为 link
- fillColor 节点颜色，默认为 22,124,255，当设置节点图片后失效
- font 节点文本大小及字体，默认为 12px Consolas
- fontColor 节点文本颜色，rgb，默认为 255,255,255
- height 节点高度，默认为 32
- rotate 旋转角度（顺时针），值为弧度
- scaleX x 轴方向缩放比例，默认为 1
- scaleY y 轴方向缩放比例，默认为 1
- selected 选中，默认为 false
- serializedProperties 属性数组，可通过 push 方法添加自定义的属性
- shadow 是否显示阴影，默认为 false
- shadowBlur 模糊度，默认 5
- shadowColor 阴影颜色，rgba，默认为 rgba(0,0,0,0.5)
- shadowOffsetX 阴影 x 轴偏移量，默认为 3
- shadowOffsetY 阴影 y 轴偏移量，默认为 6
- showSelected 显示选中后的描边，默认为 true
- strokeColor 描边颜色，rgb，默认为 22,124,255
- text 节点文本
- textOffsetX 节点文本在 x 轴方向的偏移量
- textOffsetY 节点文本在 y 轴方向的偏移量
- textPosition 节点文本位置，默认为 Bottom_Center，取值为 Top,Middle,Bottom 与 Left,Right,Center 的组合
- transformAble 是否可变行
- visible 是否可见，默认为 true
- width 节点宽度，默认为 32
- x 节点位置的 x 坐标，以画布左上角为原点
- y 节点位置的 y 坐标，以画布左上角为原点
- zIndex 图层位置，值越大位置越靠上，默认容器 Container 为 1，连线 Link 为 2，节点 Node 为 3，范围[10-999]

#### 方法：

- add(element) 把 element 对象添加到容器
- remove(element) 移除场景中的 element 对象
- removeAll() 移除场景中的所有元素
- setLocation(x,y) 设置容器的坐标

#### 事件：

- addEventListener(eventName, eventHandler) 监听事件，evevtName 取值有:click,dbclick,mousedown,mouseup,mouseover,mouseout,mousemove,mousedrag
- removeEventListener(eventName) 移除监听事件，eventName 取值同上
- removeAllEventListener 移除所有监听事件
- click(eventHandler) 监听鼠标单击事件
- dbclick(eventHandler) 监听鼠标双击事件
- mousedown(eventHandler) 监听鼠标按下事件
- mouseup(eventHandler) 监听鼠标松开事件
- mouseover(eventHandler) 监听鼠标进入事件
- mouseout(eventHandler) 监听鼠标离开事件
- mousemove(eventHandler) 监听鼠标移动事件
- mousedrag(eventHandler) 监听鼠标拖拽事件

### 节点(node):

#### 分类：

- Node()默认节点，当不设置 setImage 时，默认显示为矩形
- TextNode()文字节点
- LinkNode()超链接节点
- CircleNode()圆形节点

#### 属性：

- \_id 节点 id，默认为当前时间的时间戳
- alarm 警告文本
- alarmAlpha 警告文本的背景透明度，取值 0-1
- alarmColor 警告文本的背景颜色，rgb，默认为红色
- alpha 节点透明度，取值 0-1
- borderColor 节点边框颜色，rgb
- borderRadius 节点边框圆角，任意数值，当不大于 0 时无圆角，当超过某一值时会导致节点变形
- borderWidth 节点边框宽度，任意数值，默认为 0，当值为负时边框会进入到节点内
- dragable 可拖动，默认为 true
- editable 可编辑，默认为 false，当为 true 时可通过拖动改变大小，当点击可编辑节点后点击节点外时节点会变为不可编辑
- elementType 元素类型，场景为 scene，容器为 container，节点为 node，连线为 link
- fillColor 节点颜色，默认为 22,124,255，当设置节点图片后失效
- font 节点文本大小及字体，默认为 12px Consolas
- fontColor 节点文本颜色，rgb，默认为 255,255,255
- height 节点高度，默认为 32
- inLinks 进入此节点的连线数组
- isMouserOver 鼠标是否进入，默认为 false
- messageBus 信息总线
- outLinks 从此节点发出的连线数组
- rotate 旋转角度（顺时针），值为弧度
- scaleX x 轴方向缩放比例，默认为 1
- scaleY y 轴方向缩放比例，默认为 1
- selected 选中，默认为 false
- serializedProperties 属性数组，可通过 push 方法添加自定义的属性
- shadow 是否显示阴影，默认为 false
- shadowBlur 模糊度，默认 5
- shadowColor 阴影颜色，rgba，默认为 rgba(0,0,0,0.5)
- shadowOffsetX 阴影 x 轴偏移量，默认为 3
- shadowOffsetY 阴影 y 轴偏移量，默认为 6
- showSelected 显示选中后的描边，默认为 true
- strokeColor 描边颜色，rgb，默认为 22,124,255
- text 节点文本
- textOffsetX 节点文本在 x 轴方向的偏移量
- textOffsetY 节点文本在 y 轴方向的偏移量
- textPosition 节点文本位置，默认为 Bottom_Center，取值为 Top,Middle,Bottom 与 Left,Right,Center 的组合
- transformAble 是否可变行
- visible 是否可见，默认为 true
- width 节点宽度，默认为 32
- x 节点位置的 x 坐标，以画布左上角为原点
- y 节点位置的 y 坐标，以画布左上角为原点
- zIndex 图层位置，值越大位置越靠上，默认容器 Container 为 1，连线 Link 为 2，节点 Node 为 3，范围[10-999]

#### 方法：

- setImage(url) 设置节点图片
- setSize(width,height) 设置节点的宽、高
- getSize() 获取节点的宽、高
- setBound(x,y,width,height) 设置节点的坐标、宽、高
- getBound() 获取节点的上下左右四边的位置及宽、高
- setLocation(x,y) 设置节点在场景中的位置的坐标（左上角）
- setCenterLocation(x,y) 设置节点在场景中的位置坐标（中心）

#### 事件：

- addEventListener(eventName, eventHandler) 监听事件，evevtName 取值有:click,dbclick,mousedown,mouseup,mouseover,mouseout,mousemove,mousedrag
- removeEventListener(eventName) 移除监听事件，eventName 取值同上
- removeAllEventListener 移除所有监听事件
- click(eventHandler) 监听鼠标单击事件
- dbclick(eventHandler) 监听鼠标双击事件
- mousedown(eventHandler) 监听鼠标按下事件
- mouseup(eventHandler) 监听鼠标松开事件
- mouseover(eventHandler) 监听鼠标进入事件
- mouseout(eventHandler) 监听鼠标离开事件
- mousemove(eventHandler) 监听鼠标移动事件
- mousedrag(eventHandler) 监听鼠标拖拽事件

### 连线(Link):

#### 分类：

- Link()默认连线，默认为直线
- FoldLink() 折线
- FlexionalLink() 二次折线
- CurveLink() 曲线

#### 属性：

- \_id 节点 id，当不对其赋值时会自动赋值
- alpha 连线透明度，取值 0-1
- arrowsOffset 箭头偏移量，离开连线的距离，默认为 0
- arrowsRadius 箭头大小，默认为 null
- borderColor 连线颜色，rgb，默认为 22,124,255
- bundleGap 线条之间的间隔，默认 12
- bundleOffset 折线拐角处的长度，默认 20
- dashedPattern 虚线中每段的长度，默认为 null
- dragable 可拖动，默认为 false
- elementType 元素类型，场景为 scene，容器为 container，连线为 node，连线为 link
- fillColor 连线颜色，默认为 22,124,255
- font 连线文本大小及字体，默认为 12px Consolas
- fontColor 连线文本颜色，rgb，默认为 255,255,255
- isMouserOver 鼠标是否进入，默认为 false
- lineJoin ，默认 miter
- lineWidth 连线宽度，默认 2
- nodeA 起始节点
- nodeIndex 默认为 0，改变后会影响连线之间的间距及形状
- nodeZ 终止节点
- path 连线路径数组
- rotate 旋转角度（顺时针），值为弧度
- scaleX x 轴方向缩放比例，默认为 1
- scaleY y 轴方向缩放比例，默认为 1
- selected 选中，默认为 false
- serializedProperties 属性数组，可通过 push 方法添加自定义的属性
- shadow 是否显示阴影，默认为 false
- shadowBlur 模糊度，默认 5
- shadowColor 阴影颜色，rgba，默认为 rgba(0,0,0,0.5)
- shadowOffsetX 阴影 x 轴偏移量，默认为 3
- shadowOffsetY 阴影 y 轴偏移量，默认为 6
- showSelected 显示选中后的描边，默认为 true
- strokeColor 描边颜色，rgb，默认为 22,124,255
- text 连线文本
- textOffsetX 连线文本在 x 轴方向的偏移量
- textOffsetY 连线文本在 y 轴方向的偏移量
- transformAble 是否可变行
- visible 是否可见，默认为 true
- zIndex 图层位置，值越大位置越靠上，默认容器 Container 为 1，连线 Link 为 2，节点 Node 为 3，范围[10-999]

#### 方法：

- getEndPosition() 得到终点位置
- getStartPosition() 得到起点位置
- getPath() 得到 path 数组

#### 事件：

- addEventListener(eventName, eventHandler) 监听事件，evevtName 取值有:click,dbclick,mousedown,mouseup,mouseover,mouseout,mousemove,mousedrag
- removeEventListener(eventName) 移除监听事件，eventName 取值同上
- removeAllEventListener 移除所有监听事件
- click(eventHandler) 监听鼠标单击事件
- dbclick(eventHandler) 监听鼠标双击事件
- mousedown(eventHandler) 监听鼠标按下事件
- mouseup(eventHandler) 监听鼠标松开事件
- mouseover(eventHandler) 监听鼠标进入事件
- mouseout(eventHandler) 监听鼠标离开事件
- mousemove(eventHandler) 监听鼠标移动事件
- mousedrag(eventHandler) 监听鼠标拖拽事件

## 主要参数及方法

- zIndex_Container: 1//默认容器图层
- zIndex_Link: 2//默认连线图层
- zIndex_Node: 3//默认节点图层

### 对象

- Stage

- - 用法:new JTopo.Stage(a)创建舞台
  - 参数:canvasDOM 对象
  - 结果:Stage 舞台对象

- Scene

- - 用法:new JTopo.Scene(a)创建场景
  - 参数:Stage 舞台对象
  - 结果:Scene 场景对象

- Container

- - 用法:new JTopo.Container(a)创建容器
  - 参数:[节点文本]
  - 结果:Container 容器对象

### 节点

- AnimateNode//动画节点

- - 用法:new JTopo.AnimateNode()
  - 参数:无
  - 结果:

- CircleNode//圆形节点

- - 用法:new JTopo.CircleNode(a)
  - 参数:[节点文本]
  - 结果:

- LinkNode//超链接节点

- - 用法:new JTopo.LinkNode(a,b,c)
  - 参数:节点文本，URL，[页面打开位置]
  - 结果:

- Node//普通节点

- - 用法:new JTopo.Node(a)
  - 参数:[节点文本]
  - 结果:

- TextNode//文本节点

- - 用法:new JTopo.TextNode(a)
  - 参数:节点文本
  - 结果:

- BarChartNode//柱状图

- - 用法:new JTopo.BarChartNode()
  - 参数:无
  - 结果:

- PieChartNode//饼图

- - 用法:new JTopo.PieChartNode()
  - 参数:无
  - 结果:

### 连线

- CurveLink//曲线

- - 用法:new JTopo.CurveLink(a,b,c)
  - 参数:起始节点，终止节点，[连线文本]
  - 结果:

- FlexionalLink//二次折线

- - 用法:new JTopo.FlexionalLink(a,b,c)
  - 参数:起始节点，终止节点，[连线文本]
  - 结果:

- FoldLink//折线

- - 用法:new JTopo.FoldLink(a,b,c)
  - 参数:起始节点，终止节点，[连线文本]
  - 结果:

- Link//直线

- - 用法:new JTopo.Link(a,b,c)
  - 参数:起始节点，终止节点，[连线文本]
  - 结果:

### 从 JSON 中创建整个拓扑

- createStageFromJson

- - 用法:JTopo.createStageFromJson(jsonStr, canvas)
  - 参数:JSON 字符串，canvasDOM 对象
  - 结果:

### 旋转动画

- rotate//自旋

- - 用法:JTopo.Animate.rotate(a,b).run()
  - 参数:需要自旋的节点，canvasDOM 对象/Stage 舞台对象/Scene 场景对+ 象/Container 容器对象
    结果:

- stepByStep

- - 用法:JTopo.Animate.rotate(a,b,c,d,e).start()
  - 参数:节点，动作方式，动作间隔，是否循环，[是否往复(默认 false)]
  - 结果:

### 布局方式

- AutoBoundLayout

- - 用法:
  - 参数:
  - 结果:布局方式

- CircleLayout

- - 用法:
  - 参数:
  - 结果:布局方式

- FlowLayout

- - 用法:
  - 参数:
  - 结果:布局方式

- GridLayout

- - 用法:
  - 参数:
  - 结果:布局方式

- TreeLayout

- - 用法:JTopo.layout.TreeLayout(a,b,c)
  - 参数:布局方向(down,up,right,left)，叶子节点间距，父子节点间距
  - 结果:布局方式

- springLayout

- - 用法:
  - 参数:
  - 结果:布局方式

- layoutNode

- - 用法:JTopo.layout.layoutNode(scene, rootNode, true)
  - 参数:场景，根节点，是否多层
  - 结果:

- adjustPosition

- - 用法:
  - 参数:
  - 结果:

- circleLayoutNodes

- - 用法:
  - 参数:
  - 结果:

- getNodeChilds

- - 用法:
  - 参数:
  - 结果:

- getNodesCenter

- - 用法:
  - 参数:
  - 结果:

- getRootNodes

- - 用法:
  - 参数:
  - 结果:

- getTreeDeep

- - 用法:
  - 参数:
  - 结果:

### 从 JSON 中创建舞台

- loadStageFromJson

- - 用法:JTopo.util.loadStageFromJson(jsonStr,canvas)
  - 参数:JSON 字符串，canvasDOM 对象
  - 结果:

### 其他方法

- randomColor//获得随机颜色

- - 用法:JTopo.util.randomColor()
  - 参数:
  - 结果:

- removeFromArray//从数组 a 中移除 b

- - 用法:JTopo.util.removeFromArray(a,b)
  - 参数:数组 a，元素 b
  - 结果:

- toJson//把舞台中所有对象输出为字符串

- - 用法:JTopo.util.toJson(stage)
  - 参数:Stage 舞台对象
  - 结果:

- isChrome//判断浏览器类型

- - 用法:JTopo.util.isChrome
  - 结果:true/false

- isF+ irefox//判断浏览器类型

- - 用法:JTopo.util.isFirefox
  - 结果:true/false

- isI+ E//判断浏览器类型

- - 用法:JTopo.util.isIE
  - 结果:true/false

- isP+ ointInLine//鼠标是否指向连线

- - 用法:JTopo.util.isPointInLine
  - 参数:a, b, c
  - 结果:

- isPointInRect//鼠标是否指向矩形（？？节点）

- - 用法:JTopo.util.isPointInRect
  - 参数:a, b
  - 结果:
