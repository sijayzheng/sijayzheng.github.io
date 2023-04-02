## 设置

### 设置全局模块路径和缓存

在软件的安装目录下新建两个文件夹 node_global 和 node_cache

进入 cmd 命令行，输入一下的命令设置全局模块的安装路径到 node_global 文件夹，缓存到 node_cache 文件夹：

```bash
npm config set prefix "安装目录路径\node_global"
npm config set cache "安装目录路径\node_cache"
```

### linux 升级 node 版本

```bash
# 第一步：先清除npm缓存：
npm cache clean -f
# 第二步：安装n模块(n模块专门用来管理nodejs的版本)：
npm install -g n
# 第三步：升级node.js到最新稳定版：
n stable
# 第四步：查看当前版本
node -v
```
