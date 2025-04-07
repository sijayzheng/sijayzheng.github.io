环境 pnpm vite ts vue3

## 安装

```shell
pnpm i -D prettier
```

新建格式化配置文件`.prettierrc`

```shell
node --eval "fs.writeFileSync('.prettierrc','{}\n')"
```

新建过滤文件 `.prettierignore`

```shell
node --eval "fs.writeFileSync('.prettierignore','# Ignore artifacts:\nbuild\ncoverage\n')"
```

## 配置

| 属性                         | 默认值       | 说明                                    |
|----------------------------|-----------|---------------------------------------|
| experimentalTernaries      | false     |                                       |
| printWidth                 | 80        | 行长提示宽度                                |
| tabWidth                   | 2         | 指定 tab 宽度                             |
| useTabs                    | false     | 使用 tah 缩进                             |
| semi                       | true      | 添加行尾分号                                |
| singleQuote                | false     | 使用单引号                                 |
| quoteProps                 | as-needed | 对象 key 是否添加引号，默认仅需要时添加                |
| jsxSingleQuote             | false     | jsx 中使用单引号                            |
| trailingCommas             | all       | 尾随逗号                                  |
| bracketSpacing             | true      | 花括号内空格                                |
| bracketSameLine            | false     | 多行 HTML 元素的`>`是否换行，单标签元素除外            |
| arrowParens                | always    | 单参数箭头函数是否为参数添加括号                      |
| rangeStart                 | 0         | 格式化范围 起始                              |
| rangeEnd                   | Infinity  | 格式化范围 结束                              |
| parser                     |           | 使用的解释器                                |
| filepath                   |           | 指定用于推断解释器的文件名                         |
| requirePragma              | false     | 仅格式化带指定注释的代码                          |
| insertPragma               | false     | 自动插入`@format`指定该文件已被格式化               |
| proseWrap                  | preserve  | HTML 标签中内容是否换行                        |
| htmlWhitespaceSensitivity  | css       | HTML 中的空格是否有效                         |
| vueIndentScriptAndStyle    | false     | 缩进 Vue 文件中 `<script>` 和 `<style>`下的代码 |
| endOfLine                  | lf        | 换行符                                   |
| embeddedLanguageFormatting | auto      | 嵌入的代码是否格式化                            |
| singleAttributePerLine     | false     | HTML 元素多个属性是否单独一行                     |

## 与 eslint 集成

```shell
pnpm i -D eslint-config-prettier eslint-plugin-prettier prettier-eslint
```
