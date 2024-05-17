## 安装字体

1. 将需要安装字体的ttf文件上传到Ubuntu中
2. sudo mkfontscale
3. sudo mkfontdir
4. sudo fc-cache -fv

- 下载在线字体，以Monaco为例

```shell
wget https://github.com/fangwentong/dotfiles/raw/master/ubuntu-gui/fonts/Monaco.ttf
```

## 问题

### root 启用命令提示

`sudo vi /etc/bash.bashrc`

将如下内容取消注释

![img.png](img/img.png)

### 设置 DNS NameServer

`cd /etc/netplan`

![b7319eea](img/b7319eea.png)

### 安装 zsh 后再安装 docker，提示 `compinit:503: no such file or directory: /usr/share/zsh/vendor-completions/\_docker`

在用户的`.zshrc`文件中添加一行`plugins=(docker)`
