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

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## BigDecimal

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## BigInteger

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Arrays

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

static String toString( type[ ] a) 5. O
返回包含 a 中数据元素的字符串， 这些数据元素被放在括号内， 并用逗号分隔。
参数： a 类型为 int、 long、 short、 char、 byte、 boolean、 float 或 double 的数组。
• static type copyOf ( typeLJ a, int length) 6
• static type copyOfRange( type[ ] a, int start , int end) 6
返回与 a 类型相同的一个数组， 其长度为 length 或者 end-start, 数组元素为 a 的值。
参数： a 类型为 int、 long、 short、 char、 byte、 boolean、 float 或 double 的数组。
start 起始下标 (包含这个值 )。
end 终止下标 (不包含这个值)。 这个值可能大于 ahngth。 在这种情况
下， 结果为 0 或 false。
length 拷贝的数据元素 长度。 如果 length 值大于 a.length, 结果为 0 或 false ；
否则， 数组中只有前面 length 个数据元素的拷贝值。
• static void sort(typell a )
采用优化的快速排序算法对数组进行排序。
参数： a 类型为 int、 long、 short、 char、 byte、 boolean、 float 或 double 的数组
°
• static int binarySearch( ] a, type v )第 3 章 Java 的基本程序设计结构 85
• static int binarySearch（ type[ ] a, int start, int end, type v） 6
采用二分搜索算法查找值 v。 如果查找成功， 则返回相应的下标值； 否则， 返回一个
负数值 r。 T-1是为保持 a 有序 v 应插入的位置。
参数： a 类型为 int、 long、 short、 char、 byte、 boolean、 float 或 double 的有
序数组。
start 起始下标（包含这个值）。
end 终止下标（不包含这个值）。
v 同 a 的数据元素类型相同的值。
• static void fill(七 a, type v)
将数组的所有数据元素值设置为 Vo
参数： a 类型为 int、 long、 short、 char、 byte、 boolean、 float 或 double 的数组。
v 与 a 数据元素类型相同的一个值。
• static boolean equal s（ type[ ] a, type[] b）
如果两个数组大小相同， 并且下标相同的元素都对应相等， 返回 true。
参数： a、 b 类型为 int、 long、 short 、 char、 byte、 boolean 、 float 或 double 的两个数组。

## ArrayList

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## LinkedList

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## HashMap

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Objects

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Collectors

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## LocalDateTime

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## LocalDate

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## LocalTime

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Object

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Objects

### 位置

### 底层实现

### 创建方式

### 方法

#### 构造方法

#### 静态方法

#### 其他方法

## Integer

## Long

## Float

## Double

## Short

## Byte

## Character

## Boolean

## Comparator

## 1

## 1

## 1

## 1

## 1

## 1
