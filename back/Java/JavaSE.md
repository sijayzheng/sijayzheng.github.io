## 多线程

















## 注解

- 不是程序本身，可对程序作出解释
- 可被其他程序读取
- 格式为`@注解名`，还可以添加参数
- 可以在 package，class，method，field 等地方使用，通过反射读取注解的数据
- 本质为继承了`java.lang.annotation.Annotation`的接口
- 注解中的方法名就是参数名
- 返回值的类型就是参数的类型（类型包括基本类型、Class、String、enum）
- 可通过 default 声明参数的默认值
- 如果只有一个参数成员，一般参数名为 value，此时可以赋值时省略
- 注解元素必须要有值，通常使用空字符串、0 作为默认值
- 注解格式 `public @interface 注解名`
- 注解参数格式 `参数类型 参数名()`;

### 内置注解

- `@Override`重写
- `@Deprecated`已过时
- `@SuppressWarnings`抑制警告

### 元注解

- `@Target` 注解范围
- `@Retention` 注解生命周期（SOURCE<CLASS<RUNTIME）
- `@Documented` 注解将包含在 javadoc 中
- `@Inherited` 子类可继承父类的注解

## 反射

> 正常方式：引入包类->通过 new 实例化->获取实例化对象

> 反射方式：实例化对象->getClass()方法->得到完整的包类名

### 优点

可以动态创建对象和编译，灵活

### 缺点

- 对性能有影响

- 破坏封装

### 应用场景

- 通用框架
- 动态代理
- 注解

### 主要 API

- java.lang.Class 一个类
- java.lang.reflect.Method 方法
- java.lang.reflect.Field 字段
- java.lang.reflect.Constructor 构造器

### 功能

- 在运行时判断任意一个对象所属的类
- 在运行时构造任意一个类的对象
- 在运行时判断任意一个类所具有的成员变量和方法
- 在运行时获取泛型信息
- 在运行时调用任意一个对象的成员变量和方法
- 在运行时处理注解
- 生成动态代理

### 反射应用

#### 创建类对象

- 通过构造器的 newInstance 实例化对象
- 类构造器访问权限足够

#### 获取构造器

- 通过 getConstructors()获取所有 public 构造器
- 通过 getConstructor(Class... parameterTypes) 获取指定 public 构造器
- 通过 getDeclaredConstructors()获取所有构造器
- 通过 getDeclaredConstructor(Class... parameterTypes) 获取指定构造器

#### 获取字段

- 通过 getFields()获取所有 public 字段
- 通过 getField(String name)获取指定的 public 字段
- 通过 getDeclaredFields()获取所有字段
- 通过 getDeclaredField(String name)获取指定字段

#### 获取方法

- 通过 getMethods()获取所有的 public 方法
- 通过 getMethod(String name, Class<?>... parameterTypes)获取指定的 public 方法
- 通过 getDeclaredMethods()获取所有的方法
- 通过 getDeclaredMethod(String name, Class<?>... parameterTypes)获取指定的方法

#### 获取注解

- 通过 getAnnotations()获取所有注解
- 通过 getAnnotation(Class<T> annotationClass)获取指定注解

#### 调用方法

- 使用 invoke(Object obj, Object[] args)调用，并向方法中传递 obj 对象的参数信息
- invoke(Object obj, Object[] args)方法的返回值为原方法的返回值，如果原方法没有返回值则为 null
- 如果原方法为静态方法则形参 Object obj 可为 null
- 如果原方法形参为空则 Object[] args 为 null
- 原方法为 private 时在 invoke 之前调用 setAccessible(true)将原方法设为可以访问

#### 操作泛型

- 通过 Method 对象的 getGenericParameterTypes() 获取方法带泛型的参数类型
- 通过 Method 对象的 getGenericExceptionTypes() 获取方法抛出的异常类型
- 通过 Method 对象的 getGenericReturnType() 获取方法带泛型的返回类型

### setAccessible(boolean flag)

- Method、Field、Constructor 都有 setAccessible 方法
- setAccessible 作用是开关安全访问检查
- 参数值为 true 时关闭安全访问检查，即允许访问私有成员

  - 提高反射效率
  - 使私有成员可以访问

- 参数值为 false 则开启安全访问检查，即不允许访问私有成员

## 常用类

### Class 类

#### 简述

- 本身是一个类

- 只能由系统建立

- 一个类在 JVM 中只有一个 Class 实例

- 一个 Class 对象对应一个加载到 JVM 中的 class 文件

- 每个类的实例都会记得自己由哪个 Class 实例生成

- 通过 Class 可以完整的得到一个类中所有被加载的结构

#### 常用方法

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

#### 可包含 Class 的对象

- 类
- 接口
- 一维数组
- 二维数组
- 注解
- 枚举
- 基本数据类型
- void
- Class

## JVM

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
