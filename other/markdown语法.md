# 一级标题

## 二级标题

### 三级标题

#### 四级标题

##### 五级标题

###### 六级标题

_斜体文本_
_斜体文本_
**粗体文本**
**粗体文本**
**_粗斜体文本_**
**_粗斜体文本_**

---

---

---

---

---

~~BAIDU.COM~~

<u>dsfsdf</u>

创建脚注格式类似这样 [^tt]。

[^tt]: 这个是脚注

- 第一项
- 第二项

* 第二项
* 第三项

- 第二项
- 第三项

1. 第一项
2. 第二项
3. 第三项

4. 第一项：
   - 第一项嵌套的第一个元素
   - 第一项嵌套的第二个元素
5. 第二项：
   - 第二项嵌套的第一个元素
   - 第二项嵌套的第一个元素

> 最外层
>
> > 第一层嵌套
> >
> > > 第二层嵌套

> 最外层
>
> > 第一层嵌套
> >
> > > 第二层嵌套

> 区块中使用列表
>
> 1. 第一项
> 2. 第二项
>
> - 第一项
> - 第二项
> - 第三项

- 第一项

  > 菜鸟教程

  > 学的不仅是技术更是梦想

- 第二项

  > 菜鸟教程

  > 学的不仅是技术更是梦想

`printf()`

```javascript
;(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined'
    ? factory(exports)
    : typeof define === 'function' && define.amd
    ? define(['exports'], factory)
    : ((global = global || self), factory((global.fx = {})))
})(this, function (exports) {
  'use strict'
  Object.defineProperty(exports, '__esModule', {
    value: true,
  })
})
```

[链接名称](http://www.baidu.com)

<http://www.baidu.com>

这个链接用 1 作为网址变量 [Google][1]

![RUNOOB 图标](http://static.runoob.com/images/runoob-logo.png 'RUNOOB')
![RUNOOB 图标][tu]

| 左对齐     |       右对齐 |  居中对齐  |
| :--------- | -----------: | :--------: |
| 单元格 111 | 单元格 22222 | 单元格 333 |
| 单元格     |       单元格 |   单元格   |

流程图

```graph
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->E;
    E-->F;
    D-->F;
    F-->G;
```

时序图

```graph
sequenceDiagram
    participant Alice
    participant Bob
    Alice->John: Hello John, how are you?
    loop Healthcheck
        John->John: Fight against hypochondria
    end
    Note right of John: Rational thoughts
prevail...
    John-->Alice: Great!
    John->Bob: How about you?
    Bob-->John: Jolly good!
```

甘特图

```graph
gantt
        dateFormat  YYYY-MM-DD
        title Adding GANTT diagram functionality to mermaid
        section A section
        Completed task            :done,    des1, 2014-01-06,2014-01-08
        Active task               :active,  des2, 2014-01-09, 3d
        Future task               :         des3, after des2, 5d
        Future task2               :         des4, after des3, 5d
        section Critical tasks
        Completed task in the critical line :crit, done, 2014-01-06,24h
        Implement parser and jison          :crit, done, after des1, 2d
        Create tests for parser             :crit, active, 3d
        Future task in critical line        :crit, 5d
        Create tests for renderer           :2d
        Add to mermaid                      :1d
```

[1]: http://www.google.com/
[tu]: http://static.runoob.com/images/runoob-logo.png

## git 提交小图标

### 开发

⭐️ 增加新功能 :star:

🎨 界面样式相关更新 :art:

🔥 重大更新，比如修改字段等 :fire:

👕 包括文件代码结构以及代码风格的重构 :shirt:

🚀 重大改进，提升性能如修改某个方法或算法 :rocket:

### Bug

🐛 修复 bug :bug:

🚑 修复重大 bug :ambulance:

### 版本

🎉 发布 :tada:

📦 构建 :package:

➕ 添加依赖 :heavy_plus_sign:

➖ 减少依赖 :heavy_minus_sign:

⬆️ 升级依赖库 :arrow_up:

⬇️ 降级依赖库 :arrow_down:

### 其他

🔧 修改配置文件 :wrench:

📝 添加文档说明 :memo:

✅ 添加测试用例 :white_check_mark:

🔒 提高代码的安全性 :lock:
