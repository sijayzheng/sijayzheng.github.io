## Docker 命令大全

### 容器生命周期管理

#### run

从镜像创建一个新容器并启动，可指定容器的相关参数

`docker run [OPTIONS] IMAGE [COMMAND] [ARG...]`

**OPTIONS：**

-a stdin: 指定标准输入输出内容类型，可选 STDIN/STDOUT/STDERR 三项；

-d: 后台运行容器，并返回容器 ID；

-i: 以交互模式运行容器，通常与 -t 同时使用；

-P: 随机端口映射，容器内部端口随机映射到主机的端口

-p: 指定端口映射，格式为：主机(宿主)端口:容器端口

-t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；

--name="nginx-lb": 为容器指定一个名称；

--dns 8.8.8.8: 指定容器使用的 DNS 服务器，默认和宿主一致；

--dns-search example.com: 指定容器 DNS 搜索域名，默认和宿主一致；

-h "mars": 指定容器的 hostname；

-e username="ritchie": 设置环境变量；

--env-file=[]: 从指定文件读入环境变量；

--cpuset="0-2" or --cpuset="0,1,2": 绑定容器到指定 CPU 运行；

-m :设置容器使用内存最大值；

--net="bridge": 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型；

--link=[]: 添加链接到另一个容器；

--expose=[]: 开放一个端口或一组端口；

--volume , -v: 绑定一个卷

#### start/stop/restart

启动/停止/重启 容器

`docker start [OPTIONS] CONTAINER [CONTAINER...]`

`docker stop [OPTIONS] CONTAINER [CONTAINER...]`

`docker restart [OPTIONS] CONTAINER [CONTAINER...]`

**OPTIONS：**

-a stdin: 指定标准输入输出内容类型，可选 STDIN/STDOUT/STDERR 三项；

-d: 后台运行容器，并返回容器 ID；

-i: 以交互模式运行容器，通常与 -t 同时使用；

-P: 随机端口映射，容器内部端口随机映射到主机的端口

-p: 指定端口映射，格式为：主机(宿主)端口:容器端口

-t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；

--name="nginx-lb": 为容器指定一个名称；

--dns 8.8.8.8: 指定容器使用的 DNS 服务器，默认和宿主一致；

--dns-search example.com: 指定容器 DNS 搜索域名，默认和宿主一致；

-h "mars": 指定容器的 hostname；

-e username="ritchie": 设置环境变量；

--env-file=[]: 从指定文件读入环境变量；

--cpuset="0-2" or --cpuset="0,1,2": 绑定容器到指定 CPU 运行；

-m :设置容器使用内存最大值；

--net="bridge": 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型；

--link=[]: 添加链接到另一个容器；

--expose=[]: 开放一个端口或一组端口；

--volume , -v: 绑定一个卷

#### kill

杀死一个容器

`docker kill [OPTIONS] CONTAINER [CONTAINER...]`

**OPTIONS：**

-s :向容器发送一个信号

#### rm

删除一个或多个容器

`docker rm [OPTIONS] CONTAINER [CONTAINER...]`

**OPTIONS：**

-f :通过 SIGKILL 信号强制删除一个运行中的容器

-l :移除容器间的网络连接，而非容器本身

-v :删除与容器关联的卷

#### pause/unpause

暂停/恢复 容器中的所有进程

`docker pause CONTAINER [CONTAINER...]`

`docker unpause CONTAINER [CONTAINER...]`

#### create

从镜像创建一个新容器，可指定容器的相关参数

`docker create [OPTIONS] IMAGE [COMMAND] [ARG...]`

**OPTIONS：**

#### exec

在运行的容器中执行命令

`docker exec [OPTIONS] CONTAINER COMMAND [ARG...]`

**OPTIONS：**

-d :分离模式: 在后台运行

-i :即使没有附加也保持 STDIN 打开

-t :分配一个伪终端

### 容器操作

#### ps

列出容器

`docker ps [OPTIONS]`

**OPTIONS：**

-a :显示所有的容器，包括未运行的

-f :根据条件过滤显示的内容

--format :指定返回值的模板文件

-l :显示最近创建的容器

-n :列出最近创建的 n 个容器

--no-trunc :不截断输出

-q :静默模式，只显示容器编号

-s :显示总的文件大小

#### inspect

获取容器/镜像的元数据

`docker inspect [OPTIONS] NAME|ID [NAME|ID...]`

**OPTIONS：**

-f :指定返回值的模板文件

-s :显示总的文件大小

--type :为指定类型返回 JSON

#### top

查看容器中运行的进程信息，支持 ps 命令参数

`docker top [OPTIONS] CONTAINER [ps OPTIONS]`

#### attach

连接到正在运行中的容器

`docker attach [OPTIONS] CONTAINER`

#### events

从服务器获取实时事件

`docker events [OPTIONS]`

**OPTIONS：**

-f ：根据条件过滤事件；

--since ：从指定的时间戳后显示所有事件;

--until ：流水时间显示到指定的时间为止；

#### logs

获取容器的日志

`docker logs [OPTIONS] CONTAINER`

**OPTIONS：**

-f : 跟踪日志输出

--since :显示某个开始时间的所有日志

-t : 显示时间戳

--tail :仅列出最新 N 条容器日志

#### wait

阻塞运行直到容器停止，然后打印出它的退出代码

`docker wait [OPTIONS] CONTAINER [CONTAINER...]`

#### export

将文件系统作为一个 tar 归档文件导出到 STDOUT

`docker export [OPTIONS] CONTAINER`

**OPTIONS：**

-o :将输入内容写到文件

#### port

列出指定的容器的端口映射

`docker port [OPTIONS] CONTAINER [PRIVATE_PORT[/PROTO]]`

#### stats

显示容器资源的使用情况，包括：CPU、内存、网络 I/O 等

`docker stats [OPTIONS] [CONTAINER...]`

**OPTIONS：**

--all , -a :显示所有的容器，包括未运行的。

--format :指定返回值的模板文件。

--no-stream :展示当前状态就直接退出了，不再实时更新。

--no-trunc :不截断输出。

### 容器 rootfs 命令

#### commit

从容器创建一个新的镜像

`docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]`

**OPTIONS：**

-a :提交的镜像作者；

-c :使用 Dockerfile 指令来创建镜像；

-m :提交时的说明文字；

-p :在 commit 时，将容器暂停。

#### cp

用于容器与主机之间的数据拷贝

`docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-`

`docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH`

**OPTIONS：**

-L :保持源目标中的链接

#### diff

检查容器里文件结构的更改。

`docker diff [OPTIONS] CONTAINER`

### 镜像仓库

#### login/logout

登陆/登出 到一个 Docker 镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub

`docker login [OPTIONS] [SERVER]`
`docker logout [OPTIONS] [SERVER]`

**OPTIONS：**

-u :登陆的用户名

-p :登陆的密码

#### pull

从镜像仓库中拉取或者更新指定镜像

`docker pull [OPTIONS] NAME[:TAG|@DIGEST]`

**OPTIONS：**

-a :拉取所有 tagged 镜像

--disable-content-trust :忽略镜像的校验,默认开启

#### push

将本地的镜像上传到镜像仓库,要先登陆到镜像仓库

`docker push [OPTIONS] NAME[:TAG]`

**OPTIONS：**

--disable-content-trust :忽略镜像的校验,默认开启

#### search

从 Docker Hub 查找镜像

`docker search [OPTIONS] TERM`

**OPTIONS：**

--automated :只列出 automated build 类型的镜像；

--no-trunc :显示完整的镜像描述；

-f <过滤条件>:列出收藏数不小于指定值的镜像。

### 本地镜像管理

#### images

查看镜像信息

`docker images [OPTIONS] [REPOSITORY[:TAG]]`

**OPTIONS：**

-a :列出本地所有的镜像（含中间映像层，默认情况下，过滤掉中间映像层）；

--digests :显示镜像的摘要信息；

-f :显示满足条件的镜像；

--format :指定返回值的模板文件；

--no-trunc :显示完整的镜像信息；

-q :只显示镜像 ID

#### rmi

删除本地一个或多个镜像

`docker rmi [OPTIONS] IMAGE [IMAGE...]`

**OPTIONS：**

-f :强制删除；

--no-prune :不移除该镜像的过程镜像，默认移除；

#### tag

标记本地镜像，将其归入某一仓库。

`docker tag [OPTIONS] IMAGE[:TAG] [REGISTRYHOST/][USERNAME/]NAME[:TAG]`

**OPTIONS：**

#### build

用于使用 Dockerfile 创建镜像

`docker build [OPTIONS] PATH | URL | -`

**OPTIONS：**

--build-arg=[] :设置镜像创建时的变量；

--cpu-shares :设置 cpu 使用权重；

--cpu-period :限制 CPU CFS 周期；

--cpu-quota :限制 CPU CFS 配额；

--cpuset-cpus :指定使用的 CPU id；

--cpuset-mems :指定使用的内存 id；

--disable-content-trust :忽略校验，默认开启；

-f :指定要使用的 Dockerfile 路径；

--force-rm :设置镜像过程中删除中间容器；

--isolation :使用容器隔离技术；

--label=[] :设置镜像使用的元数据；

-m :设置内存最大值；

--memory-swap :设置 Swap 的最大值为内存+swap，"-1"表示不限 swap；

--no-cache :创建镜像的过程不使用缓存；

--pull :尝试去更新镜像的新版本；

--quiet, -q :安静模式，成功后只输出镜像 ID；

--rm :设置镜像成功后删除中间容器；

--shm-size :设置/dev/shm 的大小，默认值是 64M；

--ulimit :Ulimit 配置。

--squash :将 Dockerfile 中所有的操作压缩为一层。

--tag, -t: 镜像的名字及标签，通常 name:tag 或者 name 格式；可以在一次构建中为一个镜像设置多个标签。

--network: 默认 default。在构建期间设置 RUN 指令的网络模式

#### history

查看指定镜像的创建历史。

`docker history [OPTIONS] IMAGE`

**OPTIONS：**

-H :以可读的格式打印镜像大小和日期，默认为 true；

--no-trunc :显示完整的提交记录；

-q :仅列出提交记录 ID。

#### save

将指定镜像保存成 tar 归档文件。

`docker save [OPTIONS] IMAGE [IMAGE...]`

**OPTIONS：**

-o :输出到的文件。

#### load

导入使用 docker save 命令导出的镜像。

`docker load [OPTIONS]`

**OPTIONS：**

--input , -i : 指定导入的文件，代替 STDIN。

--quiet , -q : 精简输出信息。

#### import

从归档文件中创建镜像。

`docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]`

**OPTIONS：**

-c :应用 docker 指令创建镜像；

-m :提交时的说明文字；

### 信息

#### info

系统、容器、镜像信息

docker info

#### version

版本信息

docker version [OPTIONS]

-f：格式化输出 [pretty|json]

## 镜像

- gitlab/gitlab-ee
- ubuntu
- mysql
- redis
- nginx
- mongo
- postgres
