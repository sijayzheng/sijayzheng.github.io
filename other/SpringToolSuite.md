> Spring Tool Suite（sts）

## 错误解决

### 安装 Lombok 后无法编译

报错类下

```log
Errors occurred during the build.
Errors running builder 'Java Builder' on project 'ruoyi-common'.
Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module @322ba549
```

安装 Lombok 插件时未关闭 sts，关闭 sts 后重新安装，在启动 sts 后 clean Project
