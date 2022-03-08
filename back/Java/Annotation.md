## 注解

- 不是程序本身，可对程序作出解释
- 可被其他程序读取
- 格式为`@注解名`，还可以添加参数
- 可以在 package，class，method，field 等地方使用，通过反射读取注解的数据
- 本质为继承了`java.lang.annotation.Annotation`的接口
- 注解中的方法名就是参数名
- 返回值的类型就是参数的类型（类型包括基本类型、Class、String、enum）
- 可通过default声明参数的默认值
- 如果只有一个参数成员，一般参数名为value，此时可以赋值时省略
- 注解元素必须要有值，通常使用空字符串、0作为默认值
- 注解格式 `public @interface 注解名` 
- 注解参数格式 `参数类型 参数名()`;

## 内置注解

- `@Override`重写
- `@Deprecated`已过时
- `@SuppressWarnings`抑制警告
-

## 元注解

- `@Target` 注解范围
- `@Retention` 注解生命周期（SOURCE<CLASS<RUNTIME）
- `@Documented` 注解将包含在 javadoc 中
- `@Inherited` 子类可继承父类的注解
