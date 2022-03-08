## 反射

> 正常方式：引入包类->通过 new 实例化->获取实例化对象

> 反射方式：实例化对象->getClass()方法->得到完整的包类名

### 优点

可以动态创建对象和编译，灵活

### 缺点

对性能有影响

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

#### 调用方法

- 使用 invoke(Object obj, Object[] args)调用，并向方法中传递 obj 对象的参数信息
- invoke(Object obj, Object[] args)方法的返回值为原方法的返回值，如果原方法没有返回值则为 null
- 如果原方法为静态方法则形参 Object obj 可为 null
- 如果原方法形参为空则 Object[] args 为 null
- 原方法为 private 时在 invoke 之前调用 setAccessible(true)将原方法设为可以访问

#### 操作泛型

### setAccessible(boolean flag)

- Method、Field、Constructor 都有 setAccessible 方法
- setAccessible 作用是开关安全访问检查
- 参数值为 true 时关闭安全访问检查，即允许访问私有成员

  - 提高反射效率
  - 使私有成员可以访问

- 参数值为 false 则开启安全访问检查，即不允许访问私有成员
