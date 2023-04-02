## 安装 

- Ubuntu `sudo apt install git`
- CentOS `sudo yum install git`
- Windows 下载 git 安装文件进行安装

## 配置
*全局配置*
- `git config --global user.name "username"`
- `git config --global user.email "email@example.com"`

*为单个仓库配置需要先进入到该仓库*

- `git config user.name "UserName"`

*配置查看*

- `git config -l`
  如果不是在仓库目录下则显示全局的配置信息，如果是在仓库目录下则显示该仓库的配置信息。

## git 的常用命令

- `git init` 初始化一个仓库
- `git status` 查看当前仓库的状态(即文件的变动)
- `git diff filename` 查看 filename 文件和远程服务器上的不同之处
- `git add filename` 添加 filename 文件到暂存区(`git add .` 添加所有改动到暂存区)
- `git commit -m "message"` 提交到本地仓库且提交信息为 message
- `git log` 查看提交日志
- `git relog` 查看命令历史
- `git reset --hard commit_id` 变更版本
- `git checkout -- filename` 将 filename 文件恢复到最近一次`git add`或`git commit`的状态(取决于最近执行的命令)
- `git reset HEAD filename` 撤销暂存区的修改
- `git remote add origin url` 与远程仓库关联
- `git push origin master` 推送本地更改到远程仓库
- `git branch` 查看分支
- `git branch <name>` 创建分支
- `git checkout <name>` 切换分支
- `git checkout -b <name>` 创建+切换分支
- `git merge <name>` 合并某分支到当前分支
- `git branch -d <name>` 删除分支
- `git tag <tagname>` 用于新建一个标签，默认为 HEAD，也可以指定一个 commit_id；
- `git tag -a <tagname> -m "message"` 可以指定标签信息；
- `git tag` 可以查看所有标签。
