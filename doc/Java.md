## 配置开发环境

_以Java8为例_

- Windows
    - 下载jdk并安装
    - 新建系统变量,点击新建，变量名为`JAVA_HOME`，变量值为Java的安装路径。
    - 编辑环境变量Path,删除变量`C:\Program Files (x86)\Common Files\Oracle\Java\javapath`
      新增`%JAVA_HOME%\bin`和`%JAVA_HOME%\jre\bin`
    - 新建环境变量CLASSPATH，变量值为`.;%JAVA_HOME%\bin;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;`

- Linux
    - `sudo apt install openjdk-8-jdk`

## Java基础

### 命名

- 名字必须以字母开头，后面可以跟字母和数字的任意组合。长度基本上没有限制。
- 不能使用 Java 保留字作为类名
- 源代码的文件名必须与公共类的名字相同

**Java保留字**

| 关键字          | 含 义                            |
|--------------|--------------------------------|
| abstract     | 抽象类或方法                         |
| assert       | 用来查找内部程序错误                     |
| boolean      | 布尔类型                           |
| break        | 跳出一个 switch 或循环                |
| byte         | 8 位整数类型                        |
| case         | switch 的一个分支                   |
| catch        | 捕获异常的 try 块子句                  |
| char         | Unicode 字符类型                   |
| class        | 定义一个类类型                        |
| const        | 未使用                            |
| continue     | 在循环末尾继续                        |
| default      | switch 的缺省子句                   |
| do           | do/while 循环最前面的语句              |
| double       | 双精度浮点数类型                       |
| else         | if 语句的 else 子句                 |
| enum         | 枚举类型                           |
| extends      | 定义一个类的父类                       |
| final        | 一个常量， 或不能覆盖的一个类或方法             |
| finally      | try 块中总会执行的部分                  |
| float        | 单精度浮点数类型                       |
| for          | 一种循环类型                         |
| goto         | 未使用                            |
| if           | 一个条件语句                         |
| implements   | 定义一个类实现的接口                     |
| import       | 导入一个包                          |
| instanceof   | 测试一个对象是否为一个类的实例                |
| int          | 32 位整数类型                       |
| interface    | —种抽象类型， 其中包含可以由类实现的方法          |
| long         | 64 位长整数类型                      |
| native       | 由宿主系统实现的一个方法                   |
| new          | 分配一个新对象或数组                     |
| null         | 一个空引用（null 从技术上讲是一个直接量，而不是关键字） |
| package      | 包含类的一个包                        |
| private      | 这个特性只能由该类的方法访问                 |
| protected    | 这个特性只能由该类、 其子类以及同一个包中的其他类的方法访问 |
| public       | 这个特性可以由所有类的方法访问                |
| return       | 从一个方法返回                        |
| short        | 16 位整数类型                       |
| static       | 这个特性是这个类特有的， 而不属于这个类的对象        |
| strictfp     | 对浮点数计算使用严格的规则                  |
| super        | 超类对象或构造函数                      |
| switch       | 一个选择语句                         |
| synchronized | 对线程而言是原子的方法或代码块                |
| this         | 当前类的一个方法或构造函数的隐含参数             |
| throw        | 抛出一个异常                         |
| throws       | 一个方法可能拋出的异常                    |
| transient    | 标志非永久的数据                       |
| try          | 捕获异常的代码块                       |
| void         | 指示一个方法不返回任何值                   |
| volatile     | 确保一个字段可以由多个线程访问                |
| while        | 一种循环                           |

> 标准的命名规范为：类名是以大写字母开头的名词。如果名字由多个单词组成，每个单词的第一个字母都应该大写。

### 注释

#### 行注释

从`//`开始到本行结束

#### 块注释

`/*`到`*/`之间的内容都会被注释掉

`/*` `*/`不能嵌套使用

#### 文档注释

注释应该放置在所描述特性的前面。 注释以 `/**` 开始， 并以 `*/` 结束。

每个 `/** . . . */` 文档注释在标记之后紧跟着自由格式文本（free-form text）。

标记由@开始， 如`@author` 或`@param`。

自由格式文本的第一句应该是一个概要性的句子。javadoc 实用程序自动地将这些句子抽取出来形成概要页。

在自由格式文本中，可以使用 HTML 修饰符，但不可使用 `<hl>` 或`<hr>`, 因为它们会与文档的格式产生冲突。若要键入等宽代码， 需使用
{@code ... } 而不是`<code>...</code>`——如此就不用对代码中的 `<` 字符转义了。

> **标记**
>
> - 可用于类注释
>
> `@author 姓名`
> 产生一个 author (作者）条目。可以使用多个@author 标记，每个@author 标记对应一个作者
>
> `@version`
> 产生一个 version（版本）条目。这里的文本可以是对当前版本的任何描述。
>
> - 可用于所有的文档注释
>
> `@since 文本`
> 产生一个“ since” （始于）条目。这里的 text 可以是对引入特性的版本描述。例如，`@since version 1.7.10`
>
> `@deprecated`
> 对类、 方法或变量添加一个不再使用的注释。 文本中给出了取代的建议。
> 例如:`@deprecated Use <code> setVIsible(true)</code> instead`
>
> 通过@see 和@link标记， 可以使用超级链接， 链接到 javadoc 文档的相关部分或外
> 部文档。
>
> `@see` 引用
> 增加一个超级链接。它可以用于类中，也可以用于方法中。引用可以为超链接或类、方法
>
> `@link` 引用 使用方法同`@see`

- 类注释

对类的文档注释，类注释必须放在 import 语句之后，类定义之前

- 方法注释

对方法的文档注释，每一个方法注释必须放在所描述的方法之前。除了通用标记之外， 还可以使用下面的标记：

**@param 变量描述**

对当前方法的“ param” （参数）部分添加一个条目。这个描述可以占据多行， 并可以使用 HTML 标记。一个方法的所有@param
标记必须放在一起。

**@return 描述**

对当前方法添加“ return” （返回）部分。这个描述可以跨越多行， 并可以 使用 HTML 标记。

**©throws 类描述**

添加用于表示这个方法有可能抛出异常的一个注释。

- 域注释

对公有域（通常为静态常量）的文档注释

- 包和概述注释

包注释需要在每一个包目录中添加一个单独的文件。有两种方法：

1. 提供一个以 package.html 命名的 HTML 文件。在标记 `<body>—</body>` 之间的所有
   文本都会被抽取出来。
2. 提供一个以 package-info.java 命名的 Java 文件。这个文件必须包含一个初始的以 `/**`和 `*/` 界定的 Javadoc 注释，
   跟随在一个包语句之后。它不应该包含更多的代码或注释。

还可以为所有的源文件提供一个概述性的注释。这个注释将被放置在一个名为 overview.html
的文件中，这个文件位于包含所有源文件的父目录中。标记 `<body>... </body>` 之间的所
有文本将被抽取出来。当用户从导航栏中选择 `Overview` 时，就会显示出这些注释内容。

> **注释的抽取**
>
> 假设 HTML 文件将被存放在目录 docDirectory 下。
>
> 执行以下步骤：
> 1. 切换到包含想要生成文档的源文件目录。
> 2. 如果是一个包，应该运行命令:`javadoc -d docDirectory nameOfPackage`或对于多个包生成文档，
     运行:`javadoc -d docDirectory nameOfPackage\ nameOfPackage . . .`
     如果文件在默认包中， 就应该运行：
     `javadoc -d docDirectory *. java`
     如果省略了 `-d docDirectory` 选项， 那 HTML 文件就会被提取到当前目录下。这样有可能
     会带来混乱，因此不提倡这种做法。
     可以使用多种形式的命令行选项对 javadoc 程序进行调整。例如， 可以使用`-author` 和
     `-version` 选项在文档中包含@author 和@version 标记（默认情况下， 这些标记会被省
     略 )。另一个很有用的选项是 -link, 用来为标准类添加超链接。
     如果使用`-linksource` 选项， 则每个源文件被转换为 HTML (不对代码着色，但包含行编号) 并且每个类和方法名将转变为指向源代码的超链接。
     有关其他的选项，参考[javadoc](http://docs.orade.eom/javase/8/docs/guides/javadoc)

### 数据类型

8种基本数据类型：

- 4种整型（byte，shot，int，long）
    - 整形表示没有小数的数值，可以为负
    - long数值需要后缀`L`或`l`
    - 十六进制需要前缀`0X`或`0x`
    - 八进制需要一个前缀`0`
    - 二进制需要前缀`0B`或`0b`
    - 可以在数字中添加下划线便于阅读，如：`123_456_789`
- 2种浮点型（double，float）
    - double的数值精度为float的两倍
    - double数值需要后缀`0D`或`0d`
    - float数值需要后缀`0F`或`0f`
    - 特殊数值：正无穷（`POSITIVE_INFINITY = 1.0 / 0.0`）、负无穷（`NEGATIVE_INFINITY = -1.0 / 0.0`）、NaN（not a
      number  `NaN = 0.0d / 0.0`）
    - 判断一个值是否为NaN使用`Double.isNaN(x)`方法
    - 由于二进制无法准确的表示`1/10`，所有浮点数会存在舍入误差
- 1种字符型（char）
    - 表示一个Unicode字符（`\u0000-\uFFFF`之间）
    - 使用单引号包围
    - 转义序列`\u`还可以出现在加引号的字符常量或字符串之外（其他所有转义序列不可以）

**特殊字符转义序列及Unicode值**

| 字符名 | 转义序列 | Unicode值 |
|-----|------|----------|
| 退格  | `\b` | `\u0008` |
| 制表  | `\t` | `\u0009` |
| 换行  | `\n` | `\u000a` |
| 回车  | `\r` | `\u000d` |
| 双引号 | `\"` | `\u0022` |
| 单引号 | `\'` | `\u0027` |
| 反斜杠 | `\\` | `\u005c` |

- 1种布尔型（boolean）

> _使用十进制的科学记数法：_
>
> 0.125=2<sup>-3</sup>可以表示成 125.0e<sup>-3</sup>。
> 使用 `e` 表示指数。
>
> _使用十六进制的科学记数法：_
>
> 0.125=2<sup>-3</sup>可以表示成 0x1.0p<sup>-3</sup>。
> 在十六进制表示法中，使用 `p` 表示指数。
> **注意，尾数采用十六进制，指数采用十进制。**

**所有数据类型的值范围**

| 类型      | 占位（byte 字节） | 数据范围                                                             |
|---------|-------------|------------------------------------------------------------------|
| byte    | 1           | [-128, 127]                                                      |
| short   | 2           | [-32768,32767]                                                   |
| int     | 4           | [-2^31, 2^31-1]                                                  |
| long    | 8           | [-2^63, 2^63-1]                                                  |
| float   | 4           | [2<sup>-149</sup>,(2-2<sup>-23</sup>)&middot;2<sup>127</sup>]    |
| double  | 8           | [2<sup>-1074</sup>.,(2-2<sup>-52</sup>)&middot;2<sup>1023</sup>] |
| char    | 2           | [\u0000,\uFFFF]                                                  |
| boolean | 1           | true,false                                                       |

### 变量

- 声明

变量的声明需要指定变量的类型，需要以分号结尾

变量命名限制详见[命名](#命名)

不要在命名中使用`$`

- 初始化

变量声明后需要使用赋值语句进行显式初始化

通过`=`进行变量赋值

**常量**

常量就是对变量添加`final`关键字

常量只能被赋值一次，且无法改变值

常量通常使用大写加下划线命名

### 运算符

- 算术运算符

| 运算符 | 含义  |
|-----|-----|
| +   | 加   |
| -   | 减   | 
| *   | 乘   |
| /   | 除   | 

> 数值在进行算数运算时会发生数据类型转换
>
> 如果两个操作数中有一个是 double 类型， 另一个操作数就会转换为 double 类型。
> 否则， 如果其中一个操作数是 float 类型， 另一个操作数将会转换为 float 类型。
> 否则， 如果其中一个操作数是 long 类型， 另一个操作数将会转换为 long 类型。
> 否则， 两个操作数都将被转换为 int 类型

以下为合法数据类型转换，实线表示没有精度损失，虚线表示可能存在精度损失。**强制类型转换可能会导致信息丢失**
![数据类型转换](img/20230331150433.png)

- 赋值和运算符结合 `+=`、`-=`、`*=`、`/=`

- 自增`++` 自减`--`

```java
public class Main {
    public static void main(String[] args) {
        int n = 1;
        n += 2;// 等效于n = n + 2; 
        System.out.println(n);
        int a = n++; //a = n; 先用后加
        System.out.println(a);
        int b = ++n; //b = n + 1; 先加后用
        System.out.println(b);
    }
}
```

- 比较运算符

| 运算符  | 含义   |
|------|------|
| `==` | 相等   |
| `!=` | 不相等  |
| `<`  | 小于   |
| `>`  | 大于   |
| `<=` | 小于等于 |
| `>=` | 大于等于 |

- 逻辑运算符

| 运算符            | 含义  |
|----------------|-----|
| `&&`           | 短路与 |
| `&#124;&#124;` | 短路或 |
| `!`            | 非   |

- 三元操作符 `?:`

`condition ? expression1 : expression2`条件condition为true值为expression1，否则为expression2

- 位运算符

| 运算符      | 含义          |
|----------|-------------|
| `&`      | 与           |
| `&#124;` | 或           |
| `^`      | 异或          |
| `~`      | 非           |
| `<<`     | 左移          |
| `>>`     | 右移          |
| `>>>`    | 右移，并使用0填充高位 |

### 字符串

- String
  详见[java.lang.String](javaLib.md#string)
- 字符串构建器
    - StringBuilder
    - StringBuffer

| StringBuilder | StringBuffer |
|---------------|--------------|
| 线程不安全         | 线程安全         |
| 速度快           | 速度慢          |

| 方法                                                   | 作用                                        |
|------------------------------------------------------|-------------------------------------------|
| `int length()`                                       | 返回构建器或缓冲器中的代码单元数量                         |
| `String Builder append(String str)`                  | 追加一个字符串并返回 this                           |
| `String Builder append(char c)`                      | 追加一个代码单元并返回 this                          |
| `String Builder appendCodePoint(int cp)`             | 追加一个代码点，并将其转换为一个或两个代码单元并返回 this           |
| `void setCharAt(int i ,char c)`                      | 将第 i 个代码单元设置为 c                           |
| `String Builder insert(int offset,String str)`       | 在 offset 位置插入一个字符串并返回 this                |
| `String Builder insert(int offset,Char c)`           | 在 offset 位置插入一个代码单元并返回 this               |
| `String Builder delete(int startIndex,int endIndex)` | 删除偏移量从startIndex到endIndex-1 的代码单元并返回 this |
| `String toString()`                                  | 返回一个与构建器或缓冲器内容相同的字符串                      |

StringBuilder与StringBuffer的方法基本相同

### 输入输出

- 输出

    - 输出后换行 `System.out.println()`
    - 格式化输出 `System.out.printf()`

| printf转换符 | 类 型        | 举 例      |
|-----------|------------|----------|
| d         | 十进制整数      | 159      |
| x         | 十六进制整数     | 9f       |
| o         | 八进制整数      | 237      |
| f         | 定点浮点数      | 15.9     |
| e         | 指数浮点数      | 1.59e+01 |
| g         | 通用浮点数      | —        |
| a         | 十六进制浮点数    | Ox1.fdp3 |
| s         | 字符串        | Hello    |
| c         | 字符         | H        |
| b         | 布尔         | True     |
| h         | 散列码        | 42628b2  |
| %         | 百分号        | %        |
| n         | 与平台有关的行分隔符 | —        |

| printf 的标志    | 目 的         |
|---------------|-------------|
| +             | 打印正数和负数的符号  | 
| 空格            | 在正数之前添加空格   |
| 0             | 数字前面补 0     |
| -             | 左对齐         |
| (             | 将负数括在括号内    |
| ,             | 添加分组分隔符     |
| #（对于 f格式）     | 包含小数点       |
| #（对于 X 或0 格式） | 添加前缀 0x 或 0 |
| $             | 给定被格式化的参数索引 |
| <             | 格式化前面说明的数值  |

- 输入
    - > Scanner，并与“标准输人流”System.in关联。`Scanner scanner = new Scanner(System.in);`
      >
      > `nextLine()` 获取下一行；
      >
      > `next()` 获取截止到空白字符的内容

    - > Console类，`Console console = System.console();`
      >
      >    `readPassword()` 读取用户输入的密码
      >
      > `readLine()` 读取用户输入内容

### 控制流程





