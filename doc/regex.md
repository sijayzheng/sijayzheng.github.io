## 语法

所有 ASCII 码为\x00-\x7f,\x80-\xff 为扩展 ASCII 码会随语言编码不同而不同

1. 匹配策略

- 一般匹配 `[abc]` 匹配 abc 中任意一个
- 非匹配 `[^abc]` 匹配 abc 之外的任意一个 ，`[a^bc]` 匹配 a^bc 中的一个

2. 匹配量词：

- `{m,n}` 匹配 m 到 n 次，m 和 n 均可取到
- `*` 0 次或多次
- `+` 1 次或多次
- `？` 0 次或 1 次

3. 匹配模式：

- `/i` 不区分大小写 insensitive
- `/g` 全局匹配 global
- `/m` 多行模式 multi

4. 语法

| 字符             | 说明                                                                                                                                                |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| `\`            | 转义符。                                                                                                                                              |
| `^`            | 匹配开始。                                                                                                                                             |
| `$`            | 匹配结束。                                                                                                                                             |
| `*`            | 匹配零次或多次。\* 等价于{0,}。                                                                                                                               |
| `\+`           | 匹配一次或多次。+ 等价于 {1,}。                                                                                                                               |
| `?`            | 匹配零次或一次。? 等价于 {0,1}。                                                                                                                              |
| `{n}`          | n 是一个非负整数。匹配 n 次。                                                                                                                                 |
| `{n,}`         | n 是一个非负整数。至少匹配 n 次。`o{1,}` 等价于 `o+`。`o{0,}` 则等价于 `o*`。                                                                                            |
| `{n,m}`        | m 和 n 均为非负整数，其中 n <= m。最少匹配 n 次且最多匹配 m 次。注意在逗号和两个数之间不能有空格。                                                                                        |
| `?`            | 当该字符紧跟在任何一个其他限制符 (\*, +, ?, {n}, {n,}, {n,m}) 后面时，匹配模式是非贪婪的。                                                                                      |
| `.`            | 匹配除换行符（\n、\r）之外的任何单个字符。                                                                                                                           |
| `()`           | 增加优先级                                                                                                                                             |
| `(?:pattern)`  | 匹配含有 pattern 并获取含 pattern 在内的内容                                                                                                                   |
| `(?=pattern)`  | 匹配含有 pattern 并获取不含 pattern 在内的内容                                                                                                                  |
| `(?!pattern)`  | 匹配不含 pattern 并获取不含 pattern 在内的内容                                                                                                                  |
| `(?<=pattern)` | 与(?=pattern)方向相反。                                                                                                                                 |
| `(?<!pattern)` | 与(?!pattern)方向相反。                                                                                                                                 |
| `x\| y`        | 匹配 x 或 y。                                                                                                                                         |
| `[xyz]`        | 匹配其中任一字符。                                                                                                                                         |
| `[^xyz]`       | 匹配未在其中的任意字符。                                                                                                                                      |
| `[a-z]`        | 匹配指定范围内的任意字符。                                                                                                                                     |
| `[^a-z]`       | 匹配任何不在指定范围内的任意字符。                                                                                                                                 |
| `\b`           | 匹配一个单词边界，即紧邻空格。                                                                                                                                   |
| `\B`           | 匹配非单词边界。即不与空格紧邻。                                                                                                                                  |
| `\cx`          | 匹配由 x 指明的控制字符(ASCII 码)。例如， \cM 匹配一个 Control-M 或回车符。x 的值必须为 A-Z 或 a-z 之一。否则，将 c 视为一个原义的 `c` 字符。                                                    |
| `\d`           | 匹配一个数字字符。等价于 [0-9]。                                                                                                                               |
| `\D`           | 匹配一个非数字字符。等价于 [^0-9]。                                                                                                                             |
| `\f`           | 匹配一个换页符。等价于 \x0c 和 \cL。                                                                                                                           |
| `\n`           | 匹配一个换行符。等价于 \x0a 和 \cJ。                                                                                                                           |
| `\p`           | Unicode 字符，`\P`为`\p`取反                                                                                                                            |
| `\r`           | 匹配一个回车符。等价于 \x0d 和 \cM。                                                                                                                           |
| `\s`           | 匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [\f\n\r\t\v]。                                                                                                         |
| `\S`           | 匹配任何非空白字符。等价于 [^\f\n\r\t\v]。                                                                                                                      |
| `\t`           | 匹配一个制表符。等价于 \x09 和 \cI。                                                                                                                           |
| `\v`           | 匹配一个垂直制表符。等价于 \x0b 和 \cK。                                                                                                                         |
| `\w`           | 匹配字母、数字、下划线。等价于`[A-Za-z0-9_]`。                                                                                                                    |
| `\W`           | 匹配非字母、数字、下划线。等价于 `[^A-Za-z0-9_]`。                                                                                                                 |
| `\xn`          | 匹配 n，其中 n 为十六进制转义值。十六进制转义值必须为确定的两个数字长。例如，`\x41` 匹配 "A"。`\x041` 则等价于 `\x04` & "1"。正则表达式中可以使用 ASCII 编码。                                             |
| `\num`         | 匹配 num，其中 num 是一个正整数。对所获取的匹配的引用。例如，`(.)\1` 匹配两个连续的相同字符。                                                                                           |
| `\n`           | 标识一个八进制转义值或一个向后引用。如果 \n 之前至少 n 个获取的子表达式，则 n 为向后引用。否则，如果 n 为八进制数字 (0-7)，则 n 为一个八进制转义值。                                                             |
| `\nm`          | 标识一个八进制转义值或一个向后引用。如果 \nm 之前至少有 nm 个获得子表达式，则 nm 为向后引用。如果 \nm 之前至少有 n 个获取，则 n 为一个后跟文字 m 的向后引用。如果前面的条件都不满足，若 n 和 m 均为八进制数字 (0-7)，则 \nm 将匹配八进制转义值 nm。 |
| `\nml`         | 如果 n 为八进制数字 (0-3)，且 m 和 l 均为八进制数字 (0-7)，则匹配八进制转义值 nml。                                                                                            |
| `\un`          | 匹配 n，其中 n 是一个用四个十六进制数字表示的 Unicode 字符。例如， \u00A9 匹配版权符号 (?)。                                                                                       |

### Unicode语法类别属性

语法：`\p{Attr}`

[unicode属性说明](https://unicode.org/reports/tr18/#General_Category_Property)

| 属性(Attr) | 说明                     |
|----------|------------------------|
| `L`      | 字母                     |
| `Lu`     | 大写字母                   |
| `Ll`     | 小写字母                   |
| `Lt`     | Titlecase 字母           |
| `Lm`     | Modifier 字母            |
| `Lo`     | 其他 字母                  |
| `M`      | Mark                   |
| `Mn`     | Non-Spacing Mark       |
| `Mc`     | Spacing Combining Mark |
| `Me`     | Enclosing Mark         |
| `N`      | 数字                     |
| `Nd`     | Decimal Digit 数字       |
| `Nl`     | 字母 数字                  |
| `No`     | 其他 数字                  |
| `S`      | Symbol                 |
| `Sm`     | Math Symbol            |
| `Sc`     | Currency Symbol        |
| `Sk`     | Modifier Symbol        |
| `So`     | 其他 Symbol              |
| `P`      | Punctuation            |
| `Pc`     | Connector Punctuation  |
| `Pd`     | Dash Punctuation       |
| `Ps`     | Open Punctuation       |
| `Pe`     | Close Punctuation      |
| `Pi`     | Initial Punctuation    |
| `Pf`     | Final Punctuation      |
| `Po`     | 其他 Punctuation         |
| `Z`      | Separator              |
| `Zs`     | Space Separator        |
| `Zl`     | Line Separator         |
| `Zp`     | Paragraph Separator    |
| `C`      | 其他                     |
| `Cc`     | Control                |
| `Cf`     | Format                 |
| `Cs`     | Surrogate              |
| `Co`     | Private Use            |
| `Cn`     | Unassigned             |
| `-`      | Any\*                  |
| `-`      | Assigned\*             |
| `-`      | ASCII\*                |

## 常用正则

| 正则表达式                                                                                                                                              | 作用                                                                                   |
|----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| ^(0\|[1-9][0-9]\*)$                                                                                                                                | 零和非零开头的数字                                                                            |
| ^([1-9][0-9]\*)+(\.[0-9]{1,2})?$                                                                                                                   | 非零开头的最多带两位小数的数字                                                                      |
| ^(\-)?\d+(\.\d{1,2})$                                                                                                                              | 带 1-2 位小数的正数或负数                                                                      |
| ^(\-\|\+)?\d+(\.\d+)?$                                                                                                                             | 正数、负数、和小数                                                                            |
| ^[0-9]+(\.[0-9]{1,3})?$                                                                                                                            | 有 1~3 位小数的正实数                                                                        |
| ^[1-9]\d*$                                                                                                                                         | 非零的正整数                                                                               |
| ^\-[1-9]\d*$                                                                                                                                       | 非零的负整数                                                                               |
| ^\d+$                                                                                                                                              | 非负整数                                                                                 |
| ^-[1-9]\d\*\|0$ 或 ^((-\d+)\|(0+))$                                                                                                                 | 非正整数                                                                                 |
| ^\d+(\.\d+)?$ 或 ^[1-9]\d*\.\d*\|0\.\d*[1-9]\d*\|0?\.0+\|0$                                                                                         | 非负浮点数                                                                                |
| ^((-\d+(\.\d+)?)\|(0+(\.0+)?))$ 或 ^(-([1-9]\d*\.\d*\|0\.\d*[1-9]\d*))\|0?\.0+\|0$                                                                  | 非正浮点数                                                                                |
| ^[1-9]\d*\.\d*\|0\.\d*[1-9]\d*$ 或 ^(([0-9]+\.[0-9]_[1-9][0-9]_)\|([0-9]_[1-9][0-9]_\.[0-9]+)\|([0-9]_[1-9][0-9]_))$                                | 正浮点数                                                                                 |
| ^-([1-9]\d*\.\d*\|0\.\d*[1-9]\d*)$ 或 ^(-(([0-9]+\.[0-9]_[1-9][0-9]_)\|([0-9]_[1-9][0-9]_\.[0-9]+)\|([0-9]_[1-9][0-9]_)))$                          | 负浮点数                                                                                 |
| ^(-?\d+)(\.\d+)?$ 或 ^-?([1-9]\d*\.\d*\|0\.\d*[1-9]\d*\|0?\.0+\|0)$                                                                                 | 浮点数                                                                                  |
| ^[\u4e00-\u9fa5]{0,}$                                                                                                                              | 汉字                                                                                   |
| ^[A-Za-z0-9]+$ 或 ^[A-Za-z0-9]{4,40}$                                                                                                               | 英文和数字                                                                                |
| ^\w+$ 或 ^\w{3,20}$                                                                                                                                 | 由数字、26 个英文字母或者下划线组成的字符串                                                              |
| ^[\u4E00-\u9FA5A-Za-z0-9_]+$                                                                                                                       | 中文、英文、数字包括下划线                                                                        |
| [^%&amp;',;=?$\x22~]+                                                                                                                              | 可以输入含有^%&amp;',;=?$\"等字符                                                             |
| ^\w+([-+.]\w+)_@\w+([-.]\w+)_\.\w+([-.]\w+)\_$                                                                                                     | Email 地址                                                                             |
| [a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?                                                                                 | 域名                                                                                   |
| [a-zA-z]+://[^\s]\_ 或 ^http://([\w-]+\.)+[\w-]+(/[\w-./?%&amp;=]\*)?$                                                                              | InternetURL                                                                          |
| ^(13[0-9]\|14[01456879]\|15[0-35-9]\|16[2567]\|17[0-8]\|18[0-9]\|19[0-35-9])\d{8}$                                                                 | 手机号码                                                                                 |
| ^(\(\d{3,4}-)\|\d{3.4}-)?\d{7,8}$                                                                                                                  | 电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX) |
| \d{3}-\d{8}\|\d{4}-\d{7}                                                                                                                           | 国内电话号码(0511-4405222、021-87888822)                                                    |
| ((\d{11})\|^((\d{7,8})\|(\d{4}\|\d{3})-(\d{7,8})\|(\d{4}\|\d{3})-(\d{7,8})-(\d{4}\|\d{3}\|\d{2}\|\d{1})\|(\d{7,8})-(\d{4}\|\d{3}\|\d{2}\|\d{1}))$) | 电话号码正则表达式（支持手机号码，3-4 位区号，7-8 位直播号码，1－4 位分机号）:                                        |
| ^\d{4}-\d{1,2}-\d{1,2}                                                                                                                             | 日期格式                                                                                 |
| ^(0?[1-9]\|1[0-2])$                                                                                                                                | 一年的 12 个月(01 ～ 09 和 1 ～ 12)                                                          |
| ^((0?[1-9])\|((1\|2)[0-9])\|30\|31)$                                                                                                               | 一个月的 31 天(01 ～ 09 和 1 ～ 31)                                                          |
| ^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x\|X][m\|M][l\|L]$                                                                                                  | xml 文件                                                                               |
| [^\x00-\xff] (包括汉字在内，可以用来计算字符串的长度(一个双字节字符长度计 2，ASCII 字符计 1))                                                                                       | 双字节字符                                                                                |
| \n\s\*\r (可以用来删除空白行)                                                                                                                               | 空白行的正则表达式                                                                            |
| &lt;(\S*?)[^&gt;]*&gt;._?<!--\1-->\|&lt;._? /&gt;                                                                                                  | HTML 标记的正则表达式                                                                        |
| [1-9]\d{5}(?!\d)                                                                                                                                   | 中国邮政编码                                                                               |
| ((2(5[0-5]\|[0-4]\d))\|[0-1]?\d{1,2})(\.((2(5[0-5]\|[0-4]\d))\|[0-1]?\d{1,2})){3}                                                                  | IPv4 地址                                                                              |
| ^[1-9][0-9]\_$                                                                                                                                     | 有四种钱的表示形式我们可以接受:"10000.00" 和 "10,000.00", 和没有 "分" 的 "10000" 和 "10,000"               |
| ^(0\|[1-9][0-9]\_)$                                                                                                                                | 这表示任意一个不以 0 开头的数字,但是,这也意味着一个字符"0"不通过,所以我们采用下面的形式                                     |
| ^(0\|-?[1-9][0-9]\_)$                                                                                                                              | 一个 0 或者一个不以 0 开头的数字.我们还可以允许开头有一个负号                                                   |
| ^[0-9]+(.[0-9]+)?$                                                                                                                                 | 这表示一个 0 或者一个可能为负的开头不为 0 的数字.让用户以 0 开头好了.把负号的也去掉,因为钱总不能是负的吧。下面我们要加的是说明可能的小数部分         |
| ^[0-9]+(.[0-9]{2})?$                                                                                                                               | 必须说明的是,小数点后面至少应该有 1 位数,所以"10."是不通过的,但是 "10" 和 "10.2" 是通过的                            |
| ^[0-9]+(.[0-9]{1,2})?$                                                                                                                             | 这样我们规定小数点后面必须有两位,如果你认为太苛刻了,可以这样                                                      |
| ^[0-9]{1,3}(,[0-9]{3})\_(.[0-9]{1,2})?$                                                                                                            | 这样就允许用户只写一位小数.下面我们该考虑数字中的逗号了,我们可以这样                                                  |
| ^([0-9]+\|[0-9]{1,3}(,[0-9]{3})\_)(.[0-9]{1,2})?$                                                                                                  | 1 到 3 个数字,后面跟着任意个 逗号+3 个数字,逗号成为可选,而不是必须                                              |