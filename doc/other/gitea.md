## 安装

在Ubuntu中，参考[官方文档](https://docs.gitea.com/zh-cn)

### 下载

```shell
wget -O gitea https://dl.gitea.com/gitea/1.27.3/gitea-1.27.3-linux-amd64
chmod +x gitea
```

### 环境准备

1. git版本需要2.0及以上，通过`git --version`查看
2. 创建用户git

```shell
# On Ubuntu/Debian:
adduser \
   --system \
   --shell /bin/bash \
   --gecos 'Git Version Control' \
   --group \
   --disabled-password \
   --home /home/git \
   git

# On Fedora/RHEL/CentOS:
groupadd --system git
adduser \
   --system \
   --shell /bin/bash \
   --comment 'Git Version Control' \
   --gid git \
   --home-dir /home/git \
   --create-home \
   git
```

3. 创建工作路径

```shell
mkdir -p /var/lib/gitea/{custom,data,log}
chown -R git:git /var/lib/gitea/
chmod -R 750 /var/lib/gitea/
mkdir /etc/gitea
chown root:git /etc/gitea
chmod 770 /etc/gitea
```

4. 配置工作路径`export GITEA_WORK_DIR=/var/lib/gitea/`
5. 复制二进制文件到全局位置`cp gitea /usr/local/bin/gitea`

### 创建服务

```shell
vim /etc/systemd/system/gitea.service
```

[gitea.service](https://github.com/go-gitea/gitea/blob/main/contrib/service/systemd/gitea.service)内容

```shell
[Unit]
Description=Gitea (Git with a cup of tea)
After=network.target
###
# Don't forget to add the database service dependencies
###
#
#Wants=mysql.service
#After=mysql.service
#
#Wants=mariadb.service
#After=mariadb.service
#
#Wants=postgresql.service
#After=postgresql.service
#
#Wants=memcached.service
#After=memcached.service
#
#Wants=redis.service
#After=redis.service
#
###
# If using socket activation for main http/s
###
#
#After=gitea.main.socket
#Requires=gitea.main.socket
#
###
# (You can also provide gitea an http fallback and/or ssh socket too)
#
# An example of /etc/systemd/system/gitea.main.socket
###
##
## [Unit]
## Description=Gitea Web Socket
## PartOf=gitea.service
##
## [Socket]
## Service=gitea.service
## ListenStream=<some_port>
## NoDelay=true
##
## [Install]
## WantedBy=sockets.target
##
###

[Service]
# Uncomment the next line if you have repos with lots of files and get a HTTP 500 error because of that
# LimitNOFILE=524288:524288
RestartSec=2s
Type=simple
User=git
Group=git
WorkingDirectory=/var/lib/gitea/
# If using Unix socket: tells systemd to create the /run/gitea folder, which will contain the gitea.sock file
# (manually creating /run/gitea doesn't work, because it would not persist across reboots)
#RuntimeDirectory=gitea
ExecStart=/usr/local/bin/gitea web --config /etc/gitea/app.ini
Restart=always
Environment=USER=git HOME=/home/git GITEA_WORK_DIR=/var/lib/gitea
# If you install Git to directory prefix other than default PATH (which happens
# for example if you install other versions of Git side-to-side with
# distribution version), uncomment below line and add that prefix to PATH
# Don't forget to place git-lfs binary on the PATH below if you want to enable
# Git LFS support
#Environment=PATH=/path/to/git/bin:/bin:/sbin:/usr/bin:/usr/sbin
# If you want to bind Gitea to a port below 1024, uncomment
# the two values below, or use socket activation to pass Gitea its ports as above
###
#CapabilityBoundingSet=CAP_NET_BIND_SERVICE
#AmbientCapabilities=CAP_NET_BIND_SERVICE
###
# In some cases, when using CapabilityBoundingSet and AmbientCapabilities option, you may want to
# set the following value to false to allow capabilities to be applied on gitea process. The following
# value if set to true sandboxes gitea service and prevent any processes from running with privileges
# in the host user namespace.
###
#PrivateUsers=false
###

[Install]
WantedBy=multi-user.target
```

启动

```shell
systemctl enable gitea
systemctl start gitea
```

### 配置

服务启动后，通过ip:3000访问，在页面上按提示完成配置工作，并创建管理员用户

### 安装完成

在安装结束后将配置文件的权限设置为只读

```shell
chmod 750 /etc/gitea
chmod 640 /etc/gitea/app.ini
```

## 安装runner

1. 从https://gitea.com/gitea/runner/releases下载最新的runner
2. 上传到服务器
3. 添加可执行权限
4. 注册`./runner register --no-interactive --instance <instance> --token <token>`
5. 运行`./runner daemon`

> **注意**：instance使用访问地址或局域网地址（如：http://192.168.8.8:3000），不要使用回环地址。
> 全局注册token使用管理员账户登录后在工作流→运行器下可找到。
> 需要docker

### 配置为服务

```shell
install -m 0755 "gitea-runner-$VERSION-linux-amd64^C/usr/local/bin/gitea-runner
sudo useradd --system --home-dir /var/lib/gitea-runner --create-home gitea-runner
sudo install -d /etc/gitea-runner
sudo -u gitea-runner gitea-runner config generate | sudo tee /etc/gitea-runner/config.yaml >/dev/null
cd /var/lib/gitea-runner
cd /var/lib/gitea-runner
sudo -u gitea-runner gitea-runner register -c /etc/gitea-runner/config.yaml --instance <instance> --token <token>
gitea-runner -c config.yaml daemon #测试运行
```

新建服务`/etc/systemd/system/gitea-runner.service`，并写入以下内容

```shell
[Unit]
Description=Gitea Actions runner
Documentation=https://gitea.com/gitea/runner
After=network-online.target
Wants=network-online.target
# Uncomment when jobs use the local Docker daemon:
# After=docker.service
# Requires=docker.service

[Service]
Type=simple
ExecStart=/usr/local/bin/gitea-runner daemon --config /etc/gitea-runner/config.yaml
WorkingDirectory=/var/lib/gitea-runner
User=gitea-runner
Group=gitea-runner
Restart=on-failure
RestartSec=5s
# Allow running jobs to finish before the runner is stopped. Keep this in sync
# with runner.shutdown_timeout in the config.
TimeoutStopSec=3h

[Install]
WantedBy=multi-user.target
```

```shell
sudo systemctl daemon-reload
sudo systemctl enable --now gitea-runner
sudo systemctl status gitea-runner
```

## 更新