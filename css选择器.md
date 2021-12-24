# CSS 选择器

------
| 选择器                                                       | 示例                  | 示例说明                                                  |
| :----------------------------------------------------------- | :-------------------- | :-------------------------------------------------------- |
| [.*class*]     | .intro                | 选择所有class="intro"的元素                               |
| [#*id*]           | #firstname            | 选择所有id="firstname"的元素                              |
| [*]              | *                     | 选择所有元素                                              |
| *[element]*  | p                     | 选择所有<p>元素                                           |
| *[element,element]* | div,p                 | 选择所有<div>元素和<p>元素                                |
| [*element* *element*] | div p                 | 选择<div>元素内的所有<p>元素                              |
| [*element*>*element*] | div>p                 | 选择所有父级是 <div> 元素的 <p> 元素                      |
| [*element*+*element*] | div+p                 | 选择所有紧接着<div>元素之后的<p>元素                      |
| [[*attribute*\]] | [target]              | 选择所有带有target属性元素                                |
| [[*attribute*=*value*\]] | [target=-blank]       | 选择所有使用target="-blank"的元素                         |
| [[*attribute*~=*value*\]] | [title~=flower]       | 选择标题属性包含单词"flower"的所有元素                    |
| [[*attribute*\|=*language*\]] | [lang\|=en]           | 选择 lang 属性以 en 为开头的所有元素                      |
| [:link]         | a:link                | 选择所有未访问链接                                        |
| [:visited]   | a:visited             | 选择所有访问过的链接                                      |
| [:active]     | a:active              | 选择活动链接                                              |
| [:hover]       | a:hover               | 选择鼠标在链接上面时                                      |
| [:focus]       | input:focus           | 选择具有焦点的输入元素                                    |
| [:first-letter] | p:first-letter        | 选择每一个<p>元素的第一个字母                             |
| [:first-line] | p:first-line          | 选择每一个<p>元素的第一行                                 |
| [:first-child] | p:first-child         | 指定只有当<p>元素是其父级的第一个子级的样式。             |
| [:before]     | p:before              | 在每个<p>元素之前插入内容                                 |
| [:after]       | p:after               | 在每个<p>元素之后插入内容                                 |
| [:lang(*language*)] | p:lang(it)            | 选择一个lang属性的起始值="it"的所有<p>元素                |
| [*element1*~*element2*] | p~ul                  | 选择p元素之后的每一个ul元素                               |
| [[*attribute*^=*value*\]] | a[src^="https"]       | 选择每一个src属性的值以"https"开头的元素                  |
| [[*attribute*$=*value*\]] | a[src$=".pdf"]        | 选择每一个src属性的值以".pdf"结尾的元素                   |
| [[*attribute**=*value*\]] | a[src*="runoob"]      | 选择每一个src属性的值包含子字符串"runoob"的元素           |
| [:first-of-type] | p:first-of-type       | 选择每个p元素是其父级的第一个p元素                        |
| [:last-of-type] | p:last-of-type        | 选择每个p元素是其父级的最后一个p元素                      |
| [:only-of-type] | p:only-of-type        | 选择每个p元素是其父级的唯一p元素                          |
| [:only-child] | p:only-child          | 选择每个p元素是其父级的唯一子元素                         |
| [:nth-child(*n*)] | p:nth-child(2)        | 选择每个p元素是其父级的第二个子元素                       |
| [:nth-last-child(*n*)] | p:nth-last-child(2)   | 选择每个p元素的是其父级的第二个子元素，从最后一个子项计数 |
| [:nth-of-type(*n*)] | p:nth-of-type(2)      | 选择每个p元素是其父级的第二个p元素                        |
| [:nth-last-of-type(*n*)] | p:nth-last-of-type(2) | 选择每个p元素的是其父级的第二个p元素，从最后一个子项计数  |
| [:last-child] | p:last-child          | 选择每个p元素是其父级的最后一个子级。                     |
| [:root]         | :root                 | 选择文档的根元素                                          |
| [:empty]       | p:empty               | 选择每个没有任何子级的p元素（包括文本节点）               |
| [:target]     | #news:target          | 选择当前活动的#news元素（包含该锚名称的点击的URL）        |
| [:enabled]   | input:enabled         | 选择每一个已启用的输入元素                                |
| [:disabled] | input:disabled        | 选择每一个禁用的输入元素                                  |
| [:checked]   | input:checked         | 选择每个选中的输入元素                                    |
| [:not(*selector*)] | :not(p)               | 选择每个并非p元素的元素                                   |
| [::selection] | ::selection           | 匹配元素中被用户选中或处于高亮状态的部分                  |
| [:out-of-range] | :out-of-range         | 匹配值在指定区间之外的input元素                           |
| [:in-range] | :in-range             | 匹配值在指定区间之内的input元素                           |
| [:read-write] | :read-write           | 用于匹配可读及可写的元素                                  |
| [:read-only] | :read-only            | 用于匹配设置 "readonly"（只读） 属性的元素                |
| [:optional] | :optional             | 用于匹配可选的输入元素                                    |
| [:required] | :required             | 用于匹配设置了 "required" 属性的元素                      |
| [:valid]       | :valid                | 用于匹配输入值为合法的元素                                |
| [:invalid]   | :invalid              | 用于匹配输入值为非法的元素                                |