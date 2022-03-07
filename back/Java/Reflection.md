## Class 类的常用方法

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
