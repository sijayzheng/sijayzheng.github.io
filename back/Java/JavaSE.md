## Class 类

### 简述

- 本身是一个类

- 只能由系统建立

- 一个类在 JVM 中只有一个 Class 实例

- 一个 Class 对象对应一个加载到 JVM 中的 class 文件

- 每个类的实例都会记得自己由哪个 Class 实例生成

- 通过 Class 可以完整的得到一个类中所有被加载的结构

### 常用方法

| 方法名                                   | 说明                                                              |
| ---------------------------------------- | ----------------------------------------------------------------- |
| Class forName(String name)               | 返回指定类名 name 的 Class 对象                                   |
| Object newInstance()                     | 调用缺省构造函数，返回 Class 对象的一个实例                       |
| String getName()                         | 返回此 Class 对象所表示的实体（类，接口，数组类 或 void）的名称。 |
| Class getSuperClass()                    | 返回当前 Class 对象的父类的 Class 对象                            |
| Class[] getinterfaces()                  | 获取当前 Class 对象的接口                                         |
| ClassLoader getClassLoader()             | 返回该类的类加载器                                                |
| Constructor[] getConstructors            | 返回一个包含某些 Constructor 对象的数组                           |
| Method getMothed(String name,Class... T) | 返回一个 Method 对象，此对象的形参类型为 paramType                |
| Field[] getDeclaredFields()              | 返回 Field 对象的一个数组                                         |

### 可包含 Class 的对象

- 类
- 接口
- 一维数组
- 二维数组
- 注解
- 枚举
- 基本数据类型
- void
- Class

## 内存

### 堆

- 存放 new 的对象和数组
- 可被所有线程共享。不会存放别的对象的引用

### 栈

- 存放基本变量类型及其值
- 引用对象的变量（存放这个引用在堆里的具体地址）

### 方法区

- 可被所有线程共享
- 包含所有的 class 和 static 变量

## 类加载

- 加载：将 class 文件加载到内存并将静态数据转换成方法区的运行时数据结构，然后生成一个代表这个类的 Class 对象
- 连接：将 Java 类的二进制代码合并到 JVM 的运行状态之中

  - 验证：确保加载的类信息符号 JVM 规范，没有安全方面的问题
  - 准备：正式类变量（static）在方法区中分配内存并设置默认初始值
  - 解析：常量池中的符号（常量名）引用替换为直接（地址）引用

- 初始化：
  - 执行类构造器<clinit>()方法。该方法由编译期自动收集类中所有的变量赋值动作和静态代码块中的语句合并产生
  - 初始化一个类时，如果父类还没初始化，则触发父类初始化
  - 虚拟机保证类的<clinit>()方法在多线程中正确的加锁及同步
