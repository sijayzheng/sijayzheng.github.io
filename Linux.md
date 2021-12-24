# 一、尽可能搞清楚问题的前因后果

不要一下子就扎到服务器前面，你需要先搞明白对这台服务器有多少已知的情况，还有故障的具体情况。不然你很可能就是在无的放矢

必须搞清楚的问题有：

故障的表现是什么？无响应？报错？

故障是什么时候发现的？

故障是否可重现？

有没有出现的规律（比如每小时出现一次）

最后一次对整个平台进行更新的内容是什么（代码、服务器等）？

故障影响的特定用户群是什么样的(已登录的, 退出的, 某个地域的…)?

基础架构（物理的、逻辑的）的文档是否能找到?

是否有监控平台可用? （比如Munin、Zabbix、 Nagios、 New Relic… 什么都可以）

是否有日志可以查看?. （比如Loggly、Airbrake、 Graylog…）

最后两个是最方便的信息来源，不过别抱太大希望，基本上它们都不会有。只能再继续摸索了。

# 二、有谁在?

```
$ w  
$ last • 1
• 2
```

用这两个命令看看都有谁在线，有哪些用户访问过。这不是什么关键步骤，不过最好别在其他用户正干活的时候来调试系统。有道是一山不容二虎嘛。（ne cook in the kitchen is enough.）

# 三、之前发生了什么?

```
$ history • 1
```

查看一下之前服务器上执行过的命令。看一下总是没错的，加上前面看的谁登录过的信息，应该有点用。另外作为admin要注意，不要利用自己的权限去侵犯别人的隐私哦。

到这里先提醒一下，等会你可能会需要更新 HISTTIMEFORMAT 环境变量来显示这些命令被执行的时间。对要不然光看到一堆不知道啥时候执行的命令，同样会令人抓狂的。

# 四、现在在运行的进程是啥?

```
$ pstree -a  
$ ps aux• 1
• 2
```

这都是查看现有进程的。 ps aux 的结果比较杂乱， pstree -a 的结果比较简单明了，可以看到正在运行的进程及相关用户。

# 五、监听的网络服务

```
$ netstat -ntlp  
$ netstat -nulp  
$ netstat -nxlp• 1
• 2
• 3
```

我一般都分开运行这三个命令，不想一下子看到列出一大堆所有的服务。netstat -nalp倒也可以。不过我绝不会用 numeric 选项 （鄙人一点浅薄的看法：IP 地址看起来更方便）。

找到所有正在运行的服务，检查它们是否应该运行。查看各个监听端口。在netstat显示的服务列表中的PID 和 ps aux 进程列表中的是一样的。

如果服务器上有好几个Java或者Erlang什么的进程在同时运行，能够按PID分别找到每个进程就很重要了。

通常我们建议每台服务器上运行的服务少一点，必要时可以增加服务器。如果你看到一台服务器上有三四十个监听端口开着，那还是做个记录，回头有空的时候清理一下，重新组织一下服务器。

# 六、CPU 和内存

```
$ free -m  
$ uptime  
$ top  
$ htop• 1
• 2
• 3
• 4
```

注意以下问题:

还有空余的内存吗? 服务器是否正在内存和硬盘之间进行swap?

还有剩余的CPU吗? 服务器是几核的? 是否有某些CPU核负载过多了?

服务器最大的负载来自什么地方? 平均负载是多少?

# 七、硬件

```
$ lspci  
$ dmidecode  
$ ethtool• 1
• 2
• 3
```

有很多服务器还是裸机状态，可以看一下：

找到RAID 卡 (是否带BBU备用电池?)、 CPU、空余的内存插槽。根据这些情况可以大致了解硬件问题的来源和性能改进的办法。

网卡是否设置好? 是否正运行在半双工状态? 速度是10MBps? 有没有 TX/RX 报错?

# 八、IO 性能

```
$ iostat -kx 2  
$ vmstat 2 10  
$ mpstat 2 10  
$ dstat --top-io --top-bio• 1
• 2
• 3
• 4
```

这些命令对于调试后端性能非常有用。

检查磁盘使用量：服务器硬盘是否已满?

是否开启了swap交换模式 (si/so)?

CPU被谁占用：系统进程? 用户进程? 虚拟机?

dstat 是我的最爱。用它可以看到谁在进行 IO： 是不是MySQL吃掉了所有的系统资源? 还是你的PHP进程?

# 九、挂载点和文件系统

```
$ mount  
$ cat /etc/fstab  
$ vgs  
$ pvs  
$ lvs  
$ df -h  
$ lsof +D / /* beware not to kill your box */ • 1
• 2
• 3
• 4
• 5
• 6
• 7
```

一共挂载了多少文件系统?

有没有某个服务专用的文件系统? (比如MySQL?)

文件系统的挂载选项是什么： noatime? default? 有没有文件系统被重新挂载为只读模式了？

磁盘空间是否还有剩余?

是否有大文件被删除但没有清空?

如果磁盘空间有问题，你是否还有空间来扩展一个分区？

# 十、内核、中断和网络

```
$ sysctl -a | grep ...  
$ cat /proc/interrupts  
$ cat /proc/net/ip_conntrack /* may take some time on busy servers */  
$ netstat  
$ ss -s• 1
• 2
• 3
• 4
• 5
```

你的中断请求是否是均衡地分配给CPU处理，还是会有某个CPU的核因为大量的网络中断请求或者RAID请求而过载了？

SWAP交换的设置是什么？对于工作站来说swappinness 设为 60 就很好, 不过对于服务器就太糟了：你最好永远不要让服务器做SWAP交换，不然对磁盘的读写会锁死SWAP进程。

conntrack_max 是否设的足够大，能应付你服务器的流量?

在不同状态下(TIME_WAIT, …)TCP连接时间的设置是怎样的？

如果要显示所有存在的连接，netstat 会比较慢， 你可以先用 ss 看一下总体情况。

你还可以看一下 Linux TCP tuning 了解网络性能调优的一些要点。

# 十一、系统日志和内核消息

```
$ dmesg  
$ less /var/log/messages  
$ less /var/log/secure  
$ less /var/log/auth• 1
• 2
• 3
• 4
```

查看错误和警告消息，比如看看是不是很多关于连接数过多导致？

看看是否有硬件错误或文件系统错误?

分析是否能将这些错误事件和前面发现的疑点进行时间上的比对。

# 十二、定时任务

```
$ ls /etc/cron* + cat  
$ for user in $(cat /etc/passwd | cut -f1 -d:); do crontab -l -u $user; done• 1
• 2
```

是否有某个定时任务运行过于频繁?

是否有些用户提交了隐藏的定时任务?

在出现故障的时候，是否正好有某个备份任务在执行？

# 十三、应用系统日志

这里边可分析的东西就多了, 不过恐怕你作为运维人员是没功夫去仔细研究它的。关注那些明显的问题，比如在一个典型的LAMP（Linux+Apache+Mysql+Perl）应用环境里:

Apache & Nginx; 查找访问和错误日志, 直接找 5xx 错误, 再看看是否有 limit_zone 错误。

MySQL; 在mysql.log找错误消息，看看有没有结构损坏的表， 是否有innodb修复进程在运行，是否有disk/index/query 问题.

PHP-FPM; 如果设定了 php-slow 日志, 直接找错误信息 (php, mysql, memcache, …)，如果没设定，赶紧设定。

Varnish; 在varnishlog 和 varnishstat 里, 检查 hit/miss比. 看看配置信息里是否遗漏了什么规则，使最终用户可以直接攻击你的后端？

HA-Proxy; 后端的状况如何？健康状况检查是否成功？是前端还是后端的队列大小达到最大值了？











## centos扩充主分区容量

1. 通过fdisk添加分区

```shell
# fdisk 
fdisk /div/sda

# 查看分区数量
p
# 增加新分区
n
# 分区类型设置为主分区
p
# 设置分区号
3
# 设置起始扇区
        # 回车保持默认
# 设置结束扇区
        # 回车保持默认
# 修改分区类型
t
# 选择要修改的分区
3
# 修改分区为LVM
8e
# 写分区表
w
# 退出fdisk
q
```

1. 重启虚拟机

reboot

1. 格式化分区

mkfs.ext3 /dev/sda3

1. 添加新LVM到已有的LVM组，实现扩容

```shell
# 进入lvm管理
lvm
# 初始化分区sda3
pvcreate /dev/sda3
# 将初始化过的分区加入到虚拟卷组centos
vgextend centos /dev/sda3
# 查看free PE /Site
vgdisplay -v
# 扩展已有卷的容量(20480为free PE的大小)
lvextend -l+20480 /dev/mapper/centos-root
# 查看卷容量
pvdisplay
# 退出
quit
```

1. 文件系统扩容

xfs_growfs /dev/mapper/centos-root











































