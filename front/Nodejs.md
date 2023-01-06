## 设置

### 设置全局模块路径和缓存

在软件的安装目录下新建两个文件夹 node_global 和 node_cache

进入 cmd 命令行，输入一下的命令设置全局模块的安装路径到 node_global 文件夹，缓存到 node_cache 文件夹：

```bash
npm config set prefix "安装目录路径\node_global"
npm config set cache "安装目录路径\node_cache"
```

### 升级node到最新版本

1. 清除缓存信息

    ```bash
    sudo npm cache clean -f
    ```

2. 下载node安装包
    
    ```bash
    sudo npm install -g n
    ```

3. 升级到nodejs最新稳定版本

    ```bash
    sudo n stable
    ```

4. 查看当前版本
    
    ```bash
    node -v
    ```

                