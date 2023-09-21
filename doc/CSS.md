## 选择器

| 选择器                       | 示例                      | 示例说明                                |
|---------------------------|-------------------------|-------------------------------------|
| `.class`                  | `.intro`                | 选择所有 `class="intro"`的元素             |
| `#id`                     | `#firstname`            | 选择所有 `id="firstname"`的元素            |
| `*`                       | `*`                     | 选择所有元素                              |
| `element`                 | `p`                     | 选择所有`<p>`元素                         |
| `element,element`         | `div,p`                 | 选择所有`<div>`元素和`<p>`元素               |
| `element element`         | `div p`                 | 选择`<div>`元素内的所有`<p>`元素              |
| `element>element`         | `div>p`                 | 选择所有父级是 `<div>` 元素的 `<p>` 元素        |
| `element+element`         | `div+p`                 | 选择所有紧跟在 `<div>` 元素之后的第一个 `<p>` 元素   |
| `[attribute]`             | `[target]`              | 选择所有带有 target 属性元素                  |
| `[attribute=value]`       | `[target=-blank]`       | 选择所有使用`target="-blank"`的元素          |
| `[attribute~=value]`      | `[title~=flower]`       | 选择标题属性包含单词`flower`的所有元素             |
| `[attribute\| =language]` | `[lang\|=en]`           | 选择 `lang` 属性等于`en`，或者以`en-`为开头的所有元素 |
| `:link`                   | `a:link`                | 选择所有未访问链接                           |
| `:visited`                | `a:visited`             | 选择所有访问过的链接                          |
| `:active`                 | `a:active`              | 选择活动链接                              |
| `:hover`                  | `a:hover`               | 选择鼠标在链接上面时                          |
| `:focus`                  | `input:focus`           | 选择具有焦点的输入元素                         |
| `:first-letter`           | `p:first-letter`        | 选择每一个`<p>`元素的第一个字母                  |
| `:first-line`             | `p:first-line`          | 选择每一个`<p>`元素的第一行                    |
| `:first-child`            | `p:first-child`         | 指定只有当`<p>`元素是其父级的第一个子级的样式。          |
| `:before`                 | `p:before`              | 在每个`<p>`元素之前插入内容                    |
| `:after`                  | `p:after`               | 在每个`<p>`元素之后插入内容                    |
| `:lang(language)`         | `p:lang(it)`            | 选择一个 `lang` 属性的起始值为`it`的所有`<p>`元素   |
| `element1~element2`       | `p~ul`                  | 选择`p`元素之后的每一个`ul`元素                 |
| `[attribute^=value]`      | `a[src^="https"]`       | 选择每一个`src`属性的值以`https`开头的元素         |
| `[attribute$=value]`      | `a[src$=".pdf"]`        | 选择每一个`src`属性的值以`.pdf`结尾的元素          |
| `[attribute*=value]`      | `a[src*="srcattr"]`     | 选择每一个`src`属性的值包含子字符串`srcattr`的元素    |
| `:first-of-type`          | `p:first-of-type`       | 选择每个`p`元素是其父级的第一个`p`元素              |
| `:last-of-type`           | `p:last-of-type`        | 选择每个`p`元素是其父级的最后一个`p`元素             |
| `:only-of-type`           | `p:only-of-type`        | 选择每个`p`元素是其父级的唯一`p`元素               |
| `:only-child`             | `p:only-child`          | 选择每个`p`元素是其父级的唯一子元素                 |
| `:nth-child(n)`           | `p:nth-child(2)`        | 选择每个`p`元素是其父级的第二个子元素                |
| `:nth-last-child(n)`      | `p:nth-last-child(2)`   | 选择每个`p`元素的是其父级的第二个子元素，从最后一个子项计数     |
| `:nth-of-type(n)`         | `p:nth-of-type(2)`      | 选择每个`p`元素是其父级的第二个`p`元素              |
| `:nth-last-of-type(n)`    | `p:nth-last-of-type(2)` | 选择每个`p`元素的是其父级的第二个`p`元素，从最后一个子项计数   |
| `:last-child`             | `p:last-child`          | 选择每个`p`元素是其父级的最后一个子级。               |
| `:root`                   | `:root`                 | 选择文档的根元素                            |
| `:empty`                  | `p:empty`               | 选择每个没有任何子级的`p`元素（包括文本节点）            |
| `:target`                 | `#news:target`          | 选择当前活动的`#news`元素（包含该锚名称的点击的 URL）    |
| `:enabled`                | `input:enabled`         | 选择每一个已启用的输入元素                       |
| `:disabled`               | `input:disabled`        | 选择每一个禁用的输入元素                        |
| `:checked`                | `input:checked`         | 选择每个选中的输入元素                         |
| `:not(selector)`          | `:not(p)`               | 选择每个并非`p`元素的元素                      |
| `::selection`             | `::selection`           | 匹配元素中被用户选中或处于高亮状态的部分                |
| `:out-of-range`           | `:out-of-range`         | 匹配值在指定区间之外的`input`元素                |
| `:in-range`               | `:in-range`             | 匹配值在指定区间之内的`input`元素                |
| `:read-write`             | `:read-write`           | 用于匹配可读及可写的元素                        |
| `:read-only`              | `:read-only`            | 用于匹配设置 `readonly`（只读） 属性的元素         |
| `:optional`               | `:optional`             | 用于匹配可选的输入元素                         |
| `:required`               | `:required`             | 用于匹配设置了 `required` 属性的元素            |
| `:valid`                  | `:valid`                | 用于匹配输入值为合法的元素                       |
| `:invalid`                | `:invalid`              | 用于匹配输入值为非法的元素                       |

## grid 布局

### Container 属性

- display
  - grid 块级网格
  - inline-grid 内联网格
  - grid-template-columns
  - [line-name] 网格线名称(可选)
  - [track-size] 宽度大小(长度、百分比、fr)
  - grid-template-rows
  - [line-name] 网格线名称(可选)
  - [track-size] 高度大小(长度、百分比、fr)
  - grid-template-areas
  - [grid-area-name] 网格区域名称
  - . 空网格单元
  - none 不定义网格区域
  - grid-template `grid-template-rows,grid-template-columns,grid-template-areas`的简写
- grid-column-gap 网格线宽度/列间距
  - [line-size]
  - grid-row-gap 网格线宽度/行间距
  - [line-size]
  - grid-gap 网格线宽度/网格单元间距`grid-column-gap,grid-row-gap`的简写
  - [row-line-size] [column-line-size] / [line-size]
  - justify-items 左右对齐
  - start 将网格项对齐到其单元格的左侧起始边缘（左侧对齐）
  - end 将网格项对齐到其单元格的右侧结束边缘（右侧对齐）
  - center 将网格项对齐到其单元格的水平中间位置（水平居中对齐）
  - stretch 填满单元格的宽度（默认值）
  - align-items 上下对齐
  - start：将网格项对齐到其单元格的顶部起始边缘（顶部对齐）
  - end：将网格项对齐到其单元格的底部结束边缘（底部对齐）
  - center：将网格项对齐到其单元格的  垂直中间位置（垂直居中对齐）
  - stretch：填满单元格的高度（默认值）
  - place-items
- justify-content
- align-content
- place-content
- grid-auto-columns
- grid-auto-rows
- grid-auto-flow
- grid

### Items 属性

- grid-column-start
- grid-column-end
- grid-row-start
- grid-row-end
- grid-column
- grid-row
- grid-area
- justify-self
- align-self
- place-self

## flex 布局

### Container

- `flex-direction` 主轴的方向（即项目的排列方向）
  - `row`（默认值）：主轴为水平方向，起点在左端。
  - `row-reverse`：主轴为水平方向，起点在右端。
  - `column`：主轴为垂直方向，起点在上沿。
  - `column-reverse`：主轴为垂直方向，起点在下沿。
- `flex-wrap` 换行方式
  - `nowrap`（默认）：不换行。
  - `wrap`：换行，第一行在上方。
  - `wrap-reverse`：换行，第一行在下方。
- `flex-flow` 是`flex-direction`属性和`flex-wrap`属性的简写形式 `flex-flow: <flex-direction> || <flex-wrap>`
- `justify-content` 项目在主轴上的对齐方式
  - `flex-start`（默认值）：左对齐
  - `flex-end`：右对齐
  - `center`： 居中
  - `space-between`：两端对齐，项目之间的间隔都相等。
  - `space-around`：每个项目两侧的间隔相等。所以，项目之间的间隔比项目与边框的间隔大一倍。
- `align-items`项目在交叉轴上如何对齐。
  - `flex-start`：交叉轴的起点对齐。
  - `flex-end`：交叉轴的终点对齐。
  - `center`：交叉轴的中点对齐。
  - `baseline`: 项目的第一行文字的基线对齐。
  - `stretch`（默认值）：如果项目未设置高度或设为 auto，将占满整个容器的高度。
- `align-content`多根轴线的对齐方式
  - `flex-start`：与交叉轴的起点对齐。
  - `flex-end`：与交叉轴的终点对齐。
  - `center`：与交叉轴的中点对齐。
  - `space-between`：与交叉轴两端对齐，轴线之间的间隔平均分布。
  - `space-around`：每根轴线两侧的间隔都相等。所以，轴线之间的间隔比轴线与边框的间隔大一倍。
  - `stretch`（默认值）：轴线占满整个交叉轴。
  ### Item
- `order`排列顺序。数值越小，排列越靠前，默认为 0
- `flex-grow`放大比例，默认为`0`，即如果存在剩余空间，也不放大。
- `flex-shrink`缩小比例，默认为 1，即如果空间不足，该项目将缩小。
- `flex-basis`在分配多余空间之前，项目占据的主轴空间（main
  size）。浏览器根据这个属性，计算主轴是否有多余空间。它的默认值为`auto`，即项目的本来大小。
- `flex`是`flex-grow`, `flex-shrink` 和 `flex-basis`的简写，默认值为`0 1 auto`。后两个属性可选。
- `align-self`单个项目有与其他项目不一样的对齐方式，可覆盖`align-items`属性。默认值为`auto`，表示继承父元素的`align-items`
  属性，如果没有父元素，则等同于`stretch`。

  - `flex-start`：交叉轴的起点对齐。
  - `flex-end`：交叉轴的终点对齐。
  - `center`：交叉轴的中点对齐。
  - `baseline`: 项目的第一行文字的基线对齐。
  - `stretch`（默认值）：如果项目未设置高度或设为 auto，将占满整个容器的高度。

## css 超长字符串省略显示

```html

<div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis"></div>
```

## 垂直水平居中

```html

<div style="width:500px;height:500px;display: flex;justify-content: center;align-items: center;">
    <div style="width:100px;height:100px;background:red;">middle center</div>
</div>
```

## 清除浮动

- 本质是清楚浮动元素造成的影响
- 如果盒子本身有高度，则不需要清楚浮动
- 清楚浮动之后，父级会根据浮动的子盒子自动检测高度，父级有了高度，就不会影响下面得标准流了

**方法：**

- 额外标签法

  浮动元素的末尾添加一个空的标签，例如 `<div styele="clear：both"></div>` 或者其他标签 （`<br/>`）

  缺点：增加较多无意义标签

  注意：添加的新标签必须是块级元素

- 父级添加overflow属性

  缺点：无法显示溢出的部分

- 父级添加after伪元素

```css
.clear:after {
    content: "";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}

/*ie6,ie7 专有*/
.clearfix {
    *zoom: 1;
}
```

`<div class="box clearfix"></div>`

- 父级添加双伪元素

```css
.clearfix:before, .clearfix:after {
    conter: "";
    display: table;
}

.clearfix:after {
    clear: both;
}

.clearfix {
    *zoom: 1
}
```

**总结**

_为什么清除浮动？_

1. 父级没有高度 、 子盒子浮动了、影响下面布局了
2. 浮动的盒子不会有塌陷（外边距合并问题）
3. 行高会继承给孩子

