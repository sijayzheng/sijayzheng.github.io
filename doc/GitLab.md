## 安装 gitlab

### 安装要求

|     |                     |
|-----|---------------------|
| 硬盘  | 取决于存储库，至少 50GB      |
| CPU | 取决于用户数量，至少 2U       |
| 内存  | 取决于用户数量，至少 4G 可物理内存 |
| 交换区 | 与内存的总和至少为 8G        |

### 安装过程

_**安装并配置必需的依赖项**_

- 安装依赖

```bash
sudo yum install -y curl policycoreutils-python openssh-server
```

- 配置防火墙，启用 ssh、http、https

```bash
sudo systemctl enable sshd
sudo systemctl start sshd
sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo systemctl reload firewalld
```

_**安装 Gitlab**_

- 添加包存储库

> **centos/redhat**
>
>```bash
>curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.rpm.sh | sudo bash
>```

> **ubuntu**
>
> ```bash
> curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash
> ```

- 安装，将命令中的 `https://gitlab.example.com` 改为服务器的访问地址（如http://192.168.32.21）

```bash
sudo EXTERNAL_URL="https://gitlab.example.com" yum install -y gitlab-ce
```

2.3 初次访问

通过浏览器访问 Gitlab 的访问地址`https://gitlab.example.com`

初始密码位于`/etc/gitlab/initial_root_password`中

通过使用用户名 root 和初始密码登录。

_**配置**_

- 设置 SMTP

打开 gitlab 的配置文件

```
sudo vim /etc/gitlab/gitlab.rb
```

修改如下内容

```
gitlab_rails['smtp_enable'] = true
gitlab_rails['smtp_address'] = "smtp.163.com"
gitlab_rails['smtp_port'] = 25
gitlab_rails['smtp_user_name'] = "xxuser@163.com"
gitlab_rails['smtp_password'] = "xxpassword"
gitlab_rails['smtp_domain'] = "163.com"
gitlab_rails['smtp_authentication'] = :login
gitlab_rails['smtp_enable_starttls_auto'] = true

gitlab_rails['gitlab_email_from'] = "xxuser@163.com"
gitlab_rails['gitlab_email_display_name'] = '版本管理系统'
user['git_user_name'] = "版本管理系统"
user["git_user_email"] = "xxuser@163.com"
```

其中xxuser@163.com为发送邮件所使用的邮箱，xxpassword 为发送邮件使用的邮箱的客户端授权码

完成后执行 ` sudo gitlab-ctl reconfigure`

- 配置外观

使用管理员用户登录到系统后，打开管理中心

点击左侧的外观，然后根据页面的提示信息进行系统的界面外观配置

## 备份

命令

```
gitlab-rake gitlab:backup:create
```

备份路径为 `/var/opt/gitlab/backups`
