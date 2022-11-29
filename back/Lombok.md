## 特点

- java 库
- 使用注解自动插入 getter 或 setter 等方法

## 使用步骤

1. IDE 安装 Lombok 插件
2. 项目中导入 lombok 包
3. 为 bean 添加相应的注解

## 插件安装方法

下载 Lombok.jar，双击运行然后选择 IDE 的安装路径，点击 Install/Update

## 注解

- @Getter
  作用域：类 字段
  作用：生成 getter 方法
- @Setter
  作用域：类 字段
  作用：生成 setter 方法
- @NonNull
  作用域：字段
  作用：将标注这个字段不应为 null，初始化的时候会检查是否为空，否则抛出 NullPointException
- @ToString
  作用域：类
  作用：生成 toString 方法
- @EqualsAndHashCode
  作用域：类
  作用：生成 equals 和 hashCode 方法
- @NoArgsConstructor
  作用域：类
  作用：无参构造函数
- @RequiredArgsConstructor
  作用域：类
  作用：必填字段的构造函数
- @AllArgsConstructor
  作用域：类
  作用：全部字段的构造函数
- @Data
  作用域：类
  作用：包括 @Getter，@Setter，@RequiredArgsConstructor，@ToString，@EqualsAndHashCode
- @Build
  作用域：类
  作用：类似于@Data，并提供 build 方法
