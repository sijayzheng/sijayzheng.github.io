## String

### 位置：

`java.lang.String`

### 底层实现：

[String源码](javaSourceCode.md#string)

### 创建方式：

1. 直接将字符串赋值给变量
2. 通过构造函数

区别：直接赋值创建的字符串存储在公共池中，而 new 创建的字符串对象在堆上

> **String是不可变的：**
>
> String类的定义为`public final class String`
>
> 内部数据为`private final char[] value`（Java9之后为`private final byte[] value`）并且没有提供修改的方法

### 方法

#### 构造方法

| 方法                                                                  |
|---------------------------------------------------------------------|
| ` String()`                                                         |
| ` String(String original)`                                          |
| ` String(char value[])`                                             |
| ` String(char value[], int offset, int count)`                      |
| ` String(int[] codePoints, int offset, int count)`                  |
| ` String(byte ascii[], int hibyte, int offset, int count)`          |
| ` String(byte ascii[], int hibyte)`                                 |
| ` String(byte[] bytes, int offset, int length, String charsetName)` |
| ` String(byte[] bytes, int offset, int length, Charset charset)`    |
| ` String(byte bytes[], String charsetName)`                         |
| ` String(byte bytes[], Charset charset)`                            |
| ` String(byte[] bytes, int offset, int length)`                     |
| ` String(byte[] bytes)`                                             |
| ` String(StringBuffer buffer)`                                      |
| ` String(StringBuilder builder)`                                    |

#### 静态方法

| 方法                                                                         | 用途  |
|----------------------------------------------------------------------------|-----|
| `join(CharSequence delimiter, CharSequence... elements)`                   |
| `join(CharSequence delimiter, Iterable<? extends CharSequence> elements) ` |
| `format(String format, Object... args)`                                    |
| `format(Locale l, String format, Object... args)`                          |
| `valueOf(Object obj)`                                                      |
| `valueOf(char data[])`                                                     |
| `valueOf(char data[], int offset, int count)`                              |
| `copyValueOf(char data[], int offset, int count)`                          |
| `copyValueOf(char data[])`                                                 |
| `valueOf(boolean b)`                                                       |
| `valueOf(char c)`                                                          |
| `valueOf(int i)`                                                           |
| `valueOf(long l)`                                                          |
| `valueOf(float f)`                                                         |
| `valueOf(double d)`                                                        |

#### 其他方法

| 方法                                                       | 返回值类型        | 用途                          |
|----------------------------------------------------------|--------------|-----------------------------|
| `length()`                                               | int          | 字符串长度                       |
| `isEmpty()`                                              | boolean      | 字符串长度是否为0                   |
| `charAt(int index)`                                      | char         | 返回`index`处的字符               |
| `codePointAt(int index)`                                 | int          | 返回`index`处的码点               |
| `getBytes(String charsetName)`                           | byte[]       | 指定编码名称，转为byte数组             |
| `getBytes(Charset charset)`                              | byte[]       | 指定编码，转为byte数组               |
| `getBytes()`                                             | byte[]       | 默认编码，转为byte数组               |
| `equals(Object anObject)`                                | boolean      | 同anObject进行比较是否相同           |
| `contentEquals(StringBuffer sb)`                         | boolean      | 和StringBuffer中的内容比较是否相同     |
| `contentEquals(CharSequence cs)`                         | boolean      | 和String中的内容比较是否相同           |
| `equalsIgnoreCase(String anotherString)`                 | boolean      | 和String中的内容比较是否相同并忽略大小写     |
| `compareTo(String anotherString)`                        | int          | 和anotherString比较字典顺序        |
| `compareToIgnoreCase(String str)`                        | int          | 和anotherString比较字典顺序并忽略大小写  |
| `startsWith(String prefix, int toffset)`                 | boolean      | 前缀是否为prefix                 |
| `startsWith(String prefix)`                              | boolean      | 前缀是否为prefix                 |
| `endsWith(String suffix)`                                | boolean      | 后缀是否为suffix                 |
| `indexOf(int ch)`                                        | int          | ch所在位置                      |
| `indexOf(int ch, int fromIndex)`                         | int          | 从fromIndex开始，ch所在位置         |
| `lastIndexOf(int ch)`                                    | int          | ch所在位置                      |
| `lastIndexOf(int ch, int fromIndex)`                     | int          | 从fromIndex开始，ch所在位置         |
| `indexOf(String str)`                                    | int          | str所在位置                     |
| `indexOf(String str, int fromIndex)`                     | int          | 从fromIndex开始，str所在位置        |
| `lastIndexOf(String str)`                                | int          | str所在位置                     |
| `lastIndexOf(String str, int fromIndex)`                 | int          | 从fromIndex开始，str所在位置        |
| `substring(int beginIndex)`                              | String       | 截取beginIndex到结尾间的内容         |
| `substring(int beginIndex, int endIndex)`                | String       | 截取[beginIndex,endIndex)间的内容 |
| `subSequence(int beginIndex, int endIndex)`              | CharSequence | 截取[beginIndex,endIndex)间的内容 |
| `concat(String str)`                                     | String       | 后面拼接str                     |
| `replace(char oldChar, char newChar)`                    | String       | 将oldChar替换为newChar          |
| `matches(String regex)`                                  | boolean      | 是否匹配regex                   |
| `contains(CharSequence s)`                               | boolean      | 是否包含s                       |
| `replaceFirst(String regex, String replacement)`         | String       | 将第一个符合regex的替换为replacement  |
| `replaceAll(String regex, String replacement)`           | String       | 将符合regex的替换为replacement     |
| `replace(CharSequence target, CharSequence replacement)` | String       | 将target替换为replacement       |
| `split(String regex, int limit)`                         | String[]     | 将字符串根据regex分割为字符串数组         |
| `split(String regex)`                                    | String[]     | 将字符串根据regex分割为字符串数组         |
| `toLowerCase(Locale locale)`                             | String       | 转小写                         |
| `toLowerCase()`                                          | String       | 转小写                         |
| `toUpperCase(Locale locale)`                             | String       | 转大写                         |
| `toUpperCase()`                                          | String       | 转大写                         |
| `trim()`                                                 | String       | 去除两端空格                      |
| `isBlank()`                                              | boolean      | 非空值                         |
| `indent(int n)`                                          | String       | 在字符串前加n个空格                  |
| `strip()`                                                | String       | 去除两端空格                      |
| `translateEscapes()`                                     | String       | 将转义字符转为字符                   |
| `toCharArray()`                                          | char[]       | 转为char数组                    |
| `formatted(Object... args)`                              | String       | 模板替换                        |
| `repeat(int count)`                                      | String       | 将字符串重复count次                |

## Math