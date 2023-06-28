## 安装

- Ubuntu `sudo apt install git`
- CentOS `sudo yum install git`
- Windows 下载 git 安装文件进行安装

## 配置

_全局配置_

- `git config --global user.name "username"`
- `git config --global user.email "email@example.com"`

_为单个仓库配置需要先进入到该仓库_

- `git config user.name "UserName"`

_配置查看_

- `git config -l`
  如果不是在仓库目录下则显示全局的配置信息，如果是在仓库目录下则显示该仓库的配置信息。

## git 的常用命令

### `git init`

- 作用： 初始化一个仓库
- 参数：

### `git clone`

- 作用： 将远程仓库克隆到本地
- 参数：

### `git status`

- 作用： 查看当前仓库的状态(即文件的变动)
- 参数：

### `git diff filename`

- 作用： 查看 filename 文件和远程服务器上的不同之处
- 参数：

### `git add filename`

- 作用： 添加 filename 文件到暂存区(`git add .` 添加所有改动到暂存区)
- 参数：

### `git commit -m "message"`

- 作用： 提交到本地仓库且提交信息为 message
- 参数：

### `git log`

- 作用： 查看提交日志
- 参数：

### `git relog`

- 作用： 查看命令历史
- 参数：

### `git reset --hard commit_id`

- 作用： 变更版本
- 参数：

### `git checkout -- filename`

- 作用： 将 filename 文件恢复到最近一次`git add`或`git commit`的状态(取决于最近执行的命令)
- 参数：

### `git reset HEAD filename`

- 作用： 撤销暂存区的修改
- 参数：

### `git remote add origin url`

- 作用： 与远程仓库关联
- 参数：

### `git push origin master`

- 作用： 推送本地更改到远程仓库
- 参数：

### `git branch`

- 作用： 查看分支
- 参数：

### `git branch <name>`

- 作用： 创建分支
- 参数：

### `git checkout <name>`

- 作用： 切换分支
- 参数：

### `git checkout -b <name>`

- 作用： 创建+切换分支
- 参数：

### `git merge <name>`

- 作用： 合并某分支到当前分支
- 参数：

### `git branch -d <name>`

- 作用： 删除分支
- 参数：

### `git tag <tagname>`

- 作用： 用于新建一个标签，默认为 HEAD，也可以指定一个 commit_id；
- 参数：

### `git tag -a <tagname> -m "message"`

- 作用： 可以指定标签信息；
- 参数：

### `git tag`

- 作用： 可以查看所有标签。
- 参数：
