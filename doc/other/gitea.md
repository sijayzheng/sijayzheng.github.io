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

1. [下载最新的runner](https://gitea.com/gitea/runner/releases)
2. 上传到服务器
3. 添加可执行权限
4. 注册`./runner register --no-interactive --instance <instance> --token <token>`
5. 运行`./runner daemon`

> **注意**：instance使用访问地址或局域网地址（如：http://192.168.8.8:3000），不要使用回环地址。
> 全局注册token使用管理员账户登录后在工作流→运行器下可找到。
> 需要docker

### 配置为服务

```shell
install -m 0755 "gitea-runner-$VERSION-linux-amd64" /usr/local/bin/gitea-runner
useradd --system --home-dir /var/lib/gitea-runner --create-home gitea-runner
install -d /etc/gitea-runner
sudo -u gitea-runner gitea-runner config generate | tee /etc/gitea-runner/config.yaml >/dev/null
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
systemctl daemon-reload
systemctl enable --now gitea-runner
systemctl status gitea-runner
```

## 更新

使用[脚本](https://github.com/go-gitea/gitea/blob/main/contrib/upgrade.sh)进行升级

```shell
#!/usr/bin/env bash
# This is an update script for gitea installed via the binary distribution
# from dl.gitea.com on linux as systemd service. It performs a backup and updates
# Gitea in place.
# NOTE: This adds the GPG Signing Key of the Gitea maintainers to the keyring.
# Depends on: bash, curl, xz, sha256sum. optionally jq, gpg
#   See section below for available environment vars.
#   When no version is specified, updates to the latest release.
# Examples:
#   upgrade.sh 1.15.10
#   giteahome=/opt/gitea giteaconf=$giteahome/app.ini upgrade.sh

# Check if gitea service is running
if ! pidof gitea &> /dev/null; then
  echo "Error: gitea is not running."
  exit 1
fi

# Continue with rest of the script if gitea is running
echo "Gitea is running. Continuing with rest of script..."

# apply variables from environment
: "${giteabin:="/usr/local/bin/gitea"}"
: "${giteahome:="/var/lib/gitea"}"
: "${giteaconf:="/etc/gitea/app.ini"}"
: "${giteauser:="git"}"
: "${sudocmd:="sudo"}"
: "${arch:="linux-amd64"}"
: "${service_start:="$sudocmd systemctl start gitea"}"
: "${service_stop:="$sudocmd systemctl stop gitea"}"
: "${service_status:="$sudocmd systemctl status gitea"}"
: "${backupopts:=""}" # see `gitea dump --help` for available options

function giteacmd {
  if [[ $sudocmd = "su" ]]; then
    # `-c` only accept one string as argument.
    "$sudocmd" - "$giteauser" -c "$(printf "%q " "$giteabin" "--config" "$giteaconf" "--work-path" "$giteahome" "$@")"
  else
    "$sudocmd" --user "$giteauser" "$giteabin" --config "$giteaconf" --work-path "$giteahome" "$@"
  fi
}

function require {
  for exe in "$@"; do
    command -v "$exe" &>/dev/null || (echo "missing dependency '$exe'"; exit 1)
  done
}

# parse command line arguments
while true; do
  case "$1" in
    -v | --version ) giteaversion="$2"; shift 2 ;;
    -y | --yes ) no_confirm="yes"; shift ;;
    --ignore-gpg) ignore_gpg="yes"; shift ;;
    "" | -- ) shift; break ;;
    * ) echo "Usage:  [<environment vars>] upgrade.sh [-v <version>] [-y] [--ignore-gpg]"; exit 1;; 
  esac
done

# exit once any command fails. this means that each step should be idempotent!
set -euo pipefail

if [[ -f /etc/os-release ]]; then
  os_release=$(cat /etc/os-release)

  if [[ "$os_release" =~ "OpenWrt" ]]; then
    sudocmd="su"
    service_start="/etc/init.d/gitea start"
    service_stop="/etc/init.d/gitea stop"
    service_status="/etc/init.d/gitea status"
  else
    require systemctl
  fi
fi

require curl xz sha256sum "$sudocmd"

# select version to install
if [[ -z "${giteaversion:-}" ]]; then
  require jq
  giteaversion=$(curl --connect-timeout 10 -sL https://dl.gitea.com/gitea/version.json | jq -r .latest.version)
  echo "Latest available version is $giteaversion"
fi

# confirm update
echo "Checking currently installed version..."
current=$(giteacmd --version | cut -d ' ' -f 3)
[[ "$current" == "$giteaversion" ]] && echo "$current is already installed, stopping." && exit 0
if [[ -z "${no_confirm:-}"  ]]; then
  echo "Make sure to read the changelog first: https://github.com/go-gitea/gitea/blob/main/CHANGELOG.md"
  echo "Are you ready to update Gitea from ${current} to ${giteaversion}? (y/N)"
  read -r confirm
  [[ "$confirm" == "y" ]] || [[ "$confirm" == "Y" ]] || exit 1
fi

echo "Upgrading gitea from $current to $giteaversion ..."

pushd "$(pwd)" &>/dev/null
cd "$giteahome" # needed for gitea dump later

# download new binary
binname="gitea-${giteaversion}-${arch}"
binurl="https://dl.gitea.com/gitea/${giteaversion}/${binname}.xz"
echo "Downloading $binurl..."
curl --connect-timeout 10 --silent --show-error --fail --location -O "$binurl{,.sha256,.asc}"

# validate checksum & gpg signature
sha256sum -c "${binname}.xz.sha256"
if [[ -z "${ignore_gpg:-}" ]]; then
  require gpg
  # try to use curl first, it uses standard tcp 443 port and works better behind strict firewall rules
  curl -fsSL --connect-timeout 10 "https://keys.openpgp.org/vks/v1/by-fingerprint/7C9E68152594688862D62AF62D9AE806EC1592E2" | gpg --import \
    || gpg --keyserver keys.openpgp.org --recv 7C9E68152594688862D62AF62D9AE806EC1592E2
  gpg --verify "${binname}.xz.asc" "${binname}.xz" || { echo 'Signature does not match'; exit 1; }
fi
rm "${binname}".xz.{sha256,asc}

# unpack binary + make executable
xz --decompress --force "${binname}.xz"
chown "$giteauser" "$binname"
chmod +x "$binname"

# stop gitea, create backup, replace binary, restart gitea
echo "Flushing gitea queues at $(date)"
giteacmd manager flush-queues
echo "Stopping gitea at $(date)"
$service_stop
echo "Creating backup in $giteahome"
# shellcheck disable=SC2086 # flag string
giteacmd dump $backupopts
echo "Updating binary at $giteabin"
cp -f "$giteabin" "$giteabin.bak" && mv -f "$binname" "$giteabin"
# Restore SELinux context if applicable (e.g. RHEL/Fedora)
command -v restorecon &>/dev/null && restorecon -v "$giteabin" || true
$service_start
$service_status

echo "Upgrade to $giteaversion successful!"

popd
```