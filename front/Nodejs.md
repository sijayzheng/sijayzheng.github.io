## 设置

### 设置全局模块路径和缓存

在软件的安装目录下新建两个文件夹 node_gobal 和 node_cache

进入 cmd 命令行，输入一下的命令设置全局模块的安装路径到 node_gobal 文件夹，缓存到 node_cache 文件夹：

```bash
npm config set prefix "安装目录路径\node_gobal"
npm config set cache "安装目录路径\node_cache"
```
