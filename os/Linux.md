## centos 扩充主分区容量

1. 通过 fdisk 添加分区

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

1. 添加新 LVM 到已有的 LVM 组，实现扩容

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
