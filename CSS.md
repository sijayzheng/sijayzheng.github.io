# cssgrid 布局

## Container 属性

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

## Items 属性

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

# flex 布局

## Container

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
  ## Item
- `order`排列顺序。数值越小，排列越靠前，默认为 0
- `flex-grow`放大比例，默认为`0`，即如果存在剩余空间，也不放大。
- `flex-shrink`缩小比例，默认为 1，即如果空间不足，该项目将缩小。
- `flex-basis`在分配多余空间之前，项目占据的主轴空间（main size）。浏览器根据这个属性，计算主轴是否有多余空间。它的默认值为`auto`，即项目的本来大小。
- `flex`是`flex-grow`, `flex-shrink` 和 `flex-basis`的简写，默认值为`0 1 auto`。后两个属性可选。
- `align-self`单个项目有与其他项目不一样的对齐方式，可覆盖`align-items`属性。默认值为`auto`，表示继承父元素的`align-items`属性，如果没有父元素，则等同于`stretch`。

  - `flex-start`：交叉轴的起点对齐。
  - `flex-end`：交叉轴的终点对齐。
  - `center`：交叉轴的中点对齐。
  - `baseline`: 项目的第一行文字的基线对齐。
  - `stretch`（默认值）：如果项目未设置高度或设为 auto，将占满整个容器的高度。

  # table 表格

  细边框表格

```html
<table border="1" style="border-collapse:collapse;">
  <tr>
    <td>1.1</td>
    <td>1.2</td>
  </tr>
  <tr>
    <td>2.1</td>
    <td>2.2</td>
  </tr>
  <table></table>
</table>
```

# css 超长字符串省略显示

```html
<div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis"></div>
```

# 垂直水平居中

```html
<div style="width:500px;height:500px;display: flex;justify-content: center;align-items: center;">
  <div style="width:100px;height:100px;background:red;">middle center</div>
</div>
```
