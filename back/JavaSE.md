## JavaSE

### 概述

#### Java 跨平台的原理

- 编译之后会生成与平台无关的字节码文件
- 依赖不同平台的虚拟机（JVM）

#### 配置环境

- JAVA_HOME = D:\Java\jdk1.7.0
- PATH = %JAVA_HOME%\bin

#### Hello World

```java
public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }
}
```

#### javac 命令

- `javac [-d <目录>] HelloWorld.java`：编译 Java 源文件，并指定放置生成的字节码文件的位置，一点（ . ）表示当前路径（会自动生成相应的包目录）

#### java 命令

- `java [-options] class [args…]`：执行类
- `java [-options] -jar jarfile [args…]`：执行 jar 文件
- 常见 options
  - `java -version`
  - `java -classpath <目录> com.demo.hello.HelloWorld`：运行指定目录下的字节码文件
  - `java -cp .;myClass.jar com.demo.hello.HelloWorld`：指定各个 JAR 文件具体的存放路径（用 ; 分隔）
  - `java -D<名称>=<值> com.demo.hello.HelloWorld`： 设置系统属性（Java system property）

#### Java 基本语法

- Java 语言严格区分大小写
- 一个 Java 源文件（ .java ）里可以定义多个 Java 类，但其中最多**只能有一个**类被定义成 public 类；若源文件中包含 public 类定义，则该源文件的文件名必须与该 public 类的类名相同
- 一个源文件中包含 N 个 Java 类时，成功编译后会生成 N 份字节码文件（ .class），即每个类都会生成一份单独的 class 文件，且字节码文件名和其对应的类名相同
- 若一个类必须运行，则必须拥有 main 方法，因为 main 方法是程序的入口

### 数据类型

#### 基本数据类型

| 类型    | 占位（byte） | 数据范围                                                     |
| ------- | ------------ | ------------------------------------------------------------ |
| byte    | 1            | [-128, 127]                                                  |
| short   | 2            | [-32768,32767]                                               |
| int     | 4            | [-2^31, 2^31-1]                                              |
| long    | 8            | [-2^63, 2^63-1]                                              |
| float   | 4            | [2<sup>-149</sup>,(2-2<sup>-23</sup>)&middot;2<sup>127</sup>] |
| double  | 8            | [2<sup>-1074</sup>.,(2-2<sup>-52</sup>)&middot;2<sup>1023</sup>] |
| char    | 2            | [\u0000,\uFFFF]                                              |
| boolean | 1            | true,false                                                   |
