| 样式          | 代码            | 
|-------------|---------------|
| # 一级标题      | `# 一级标题`      |
| ## 二级标题     | `## 二级标题`     |
| ### 三级标题    | `### 三级标题`    |
| #### 四级标题   | `#### 四级标题`   |
| ##### 五级标题  | `##### 五级标题`  |
| ###### 六级标题 | `###### 六级标题` |

*斜体文本*
_斜体文本_
**粗体文本**
__粗体文本__
***粗斜体文本***
___粗斜体文本___


***

* * *

*****

- - -

----------

~~BAIDU.COM~~

<u>dsfsdf</u>

创建脚注格式类似这样 [^tt]。

[^tt]: 这个是脚注

* 第一项
* 第二项

+ 第二项
+ 第三项

- 第二项
- 第三项

1. 第一项
2. 第二项
3. 第三项

1. 第一项：

- 第一项嵌套的第一个元素
- 第一项嵌套的第二个元素

2. 第二项：

- 第二项嵌套的第一个元素
- 第二项嵌套的第一个元素

> 最外层
> > 第一层嵌套
> >
> > > 第二层嵌套

> 最外层
> > 第一层嵌套
> >
> > > 第二层嵌套

> 区块中使用列表
> 1. 第一项
> 2. 第二项
> + 第一项
> + 第二项
> + 第三项

* 第一项

  > 菜鸟教程

  > 学的不仅是技术更是梦想
* 第二项

  > 菜鸟教程

  > 学的不仅是技术更是梦想

`printf()`

```javascript
(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
        typeof define === 'function' && define.amd ? define(['exports'], factory) :
            (global = global || self, factory(global.fx = {}));
}(this, function (exports) {
    'use strict';
    Object.defineProperty(exports, '__esModule', {
        value: true
    });
}));
```

[链接名称](http://www.baidu.com)

<http://www.baidu.com>

这个链接用 1 作为网址变量 [Google][1]

![RUNOOB 图标](http://static.runoob.com/images/runoob-logo.png "RUNOOB")
![RUNOOB 图标][picture]

| 左对齐    |      右对齐 |  居中对齐  |
|:-------|---------:|:------:|
| 单元格111 | 单元格22222 | 单元格333 |
| 单元格    |      单元格 |  单元格   |

[1]: http://www.google.com/

[picture]:http://static.runoob.com/images/runoob-logo.png

## 小图标

- :star:️ 增加新功能 `:star:`
- :art: 界面样式相关更新 `:art:`
- :fire: 重大更新，比如修改字段等 `:fire:`
- :shirt: 包括文件代码结构以及代码风格的重构 `:shirt:`
- :rocket: 重大改进，提升性能如修改某个方法或算法 `:rocket:`
- :bug: 修复bug `:bug:`
- :ambulance: 修复重大bug `:ambulance:`
- :tada: 发布 `:tada:`
- :package: 构建 `:package:`
- :heavy_plus_sign: 添加依赖 `:heavy_plus_sign:`
- :heavy_minus_sign: 减少依赖 `:heavy_minus_sign:`
- :arrow_up:️ 升级依赖库 `:arrow_up:`
- :arrow_down:️ 降级依赖库 `:arrow_down:`
- :wrench: 修改配置文件 `:wrench:`
- :memo: 添加文档说明 `:memo:`
- :white_check_mark: 添加测试用例 `:white_check_mark:`
- :lock: 提高代码的安全性 `:lock:`
