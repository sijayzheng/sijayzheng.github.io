使用 [EditorConfig](https://editorconfig.org) 配置代码风格

## 配置文件`.editorconfig`

- 每一节使用`[]`作为节名
- 节名为文件名称或通配符
- 路径使用`/`做分隔符
- 注释使用`#`或`;`
- 每条规则都应单独一行
- 使用 UTF8 编码
- 使用 CRLF 或 LF 换行
- 后续规则优先，会对前述规则进行覆盖

## 通配符

| 通配符       | 说明                                                                 |
| ------------ | -------------------------------------------------------------------- |
| \*           | 匹配除路径分隔符 （/） 之外的任何字符串                              |
| \*\*         | 匹配任何字符串                                                       |
| ?            | 匹配任何单个字符                                                     |
| [name]       | 匹配 name 中的任何单个字符                                           |
| [!name]      | 匹配 name 中未包含的任何单个字符                                     |
| {s1,s2,s3}   | 匹配给定的任何字符串（以逗号分隔）                                   |
| {num1..num2} | 匹配 num1 和 num2 之间的任何整数，其中 num1 和 num2 可以是正数或负数 |

特殊字符可用`\`进行转义

## 规则属性

| 属性                     | 说明                     | 值                                       |
| ------------------------ | ------------------------ | ---------------------------------------- |
| indent_style             | 缩进样式                 | tab space                                |
| indent_size              | 缩进大小                 | 正整数 tab                               |
| tab_width                | tab 大小                 | 正整数                                   |
| end_of_line              | 换行符                   | lf crlf cr                               |
| charset                  | 文件编码                 | latin1 utf-8 utf-16be utf-16le utf-8-bom |
| trim_trailing_whitespace | 删除行尾空格             | true false                               |
| insert_final_newline     | 文件是否以换行符结尾     | true false                               |
| max_line_length          | 强制换行(部分编辑器支持) | 正整数 off                               |
