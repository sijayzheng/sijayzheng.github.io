## GitLab-runner 安装、注册

1. 添加官方存储库：

> - 对于 Debian/Ubuntu/Mint
>
> ```bash
> curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash
> ```
>
> - 对于 RHEL/CentOS/Fedora
>
> ```bash
> curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.rpm.sh | sudo bash
> ```

2. 安装

> **注：Debian buster 用户应禁用 skel 以防止没有此类文件或目录作业失败**
>
> - 安装最新版本
>
>   - 对于 Debian/Ubuntu/Mint
>
>   ```bash
>   export GITLAB_RUNNER_DISABLE_SKEL=true; sudo -E apt-get install gitlab-runner
>   ```
>
>   - 对于 RHEL/CentOS/Fedora
>
>   ```bash
>   export GITLAB_RUNNER_DISABLE_SKEL=true; sudo -E yum install gitlab-runner
>   ```
>
> - 安装指定版本
>
>   - 对于 DEB based systems
>
>   ```bash
>   apt-cache madison gitlab-runner
>   export GITLAB_RUNNER_DISABLE_SKEL=true; sudo -E apt-get install gitlab-runner=10.0.0
>   ```
>
>   - 对于 RPM based systems
>
>   ```bash
>   yum list gitlab-runner --showduplicates | sort -r
>   export GITLAB_RUNNER_DISABLE_SKEL=true; sudo -E yum install gitlab-runner-10.0.0-1
>   ```

3. 注册

> 向 GitLab-CI 注册一个 Runner 需要两样东西：GitLab 访问地址和 gitlab-ci token。
>
> 1. 注册
>
> ```bash
> # 在命令中指定GitLab访问地址和gitlab-ci token
> sudo gitlab-runner register --url <url> --registration-token <token>
> # 不指定GitLab访问地址和gitlab-ci token
> gitlab-runner register
> ```
>
> 2. 输入 `http://gitlab.domain.com`或`http://ip:port` 就是 GitLab 访问地址
>
> 3. Please enter the gitlab-ci token for this runner 要求输入 gitlab-ci token
>
> 在项目的 管理区域->runners 中可以找到(这里注册的是 share 类型 runner)
>
> ![image](../img/pic12.png)
>
> 4. 输入描述（可以留空）
>
> 5. 输入 tag（可以留空）
>
> 6. 选择当遇到没有打标签的提交时是否会执行，我们选 true
>
> 7. 是否锁定此 runner 到当前项目， 我们选 false
>
> 8. 选一个执行者 executor (ssh, docker+machine, docker-ssh+machine, kubernetes, docker, parallels, virtualbox, docker-ssh, shell)
>
> 选 shell（表示使用当前机器执行）
>
> 9. 在注册完之后，我们可以在 GitLab 获取 gitlab-ci token 的页面看到我们刚刚注册的这个 runner
