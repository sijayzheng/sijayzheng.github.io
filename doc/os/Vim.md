## 命令

### 编辑模式

按`a、i`进入

### 命令行模式

按`:`进入

| 命令 | 英文       | 中文     |
| ---- | ---------- | -------- |
| q    | quit       | 退出     |
| q!   |            | 强制退出 |
| wq   | write quit | 保存退出 |

### 正常模式

| 命令          | 英文        | 中文                   |
| ------------- | ----------- | ---------------------- |
| i             | insert      | 在光标前插入           |
| I             |             | 在最前插入             |
| a             | append      | 在光标后插入           |
| A             |             | 在最后插入             |
| h             |             | 光标左移               |
| j             |             | 光标下移               |
| k             |             | 光标上移               |
| l             |             | 光标右移               |
| b             |             | 光标退到上一个单词前   |
| e             |             | 光标前进到下一个单词后 |
| w             |             | 光标前进到下一个单词前 |
| o             |             | 下方新增一行           |
| O             |             | 上方新一行             |
| G             |             | 到尾行                 |
| gg            |             | 到首行                 |
| yy            | yank        | 辅助当前行             |
| dd            |             | 剪切当前行             |
| .             |             | 重复当前行             |
| u             | undo        | 撤回                   |
| ctrl + r      |             | 重做                   |
| dw            | delete word | 删除单词               |
| /             |             | 搜索                   |
| :%s/old/new/g |             | 全局替换               |
| yw            |             | 复制单词               |
| p             |             | 粘贴                   |
| ci[{(         |             | 修改括号内的内容       |
| ctrl+v        |             | 可视化块               |
| shift+v       |             | 可视化模式             |

## VIM 配置

.vimrc 文件

```
syntax on
map Q gq
set scrolloff=5
set incsearch
set number
set relativenumber
set autoindent
set expandtab
set cursorline
set hlsearch
set smartcase
set showmode
set showcmd
set clipboard=unnamedplus

nnoremap L $
nnoremap H ^
```

## 操作

### 正则表达式替换内容

```
:%s/old/new/g
```

| 元字符 | 通用 | 作用      |
| ------ | ---- | --------- |
| `*`    | `*`  | 0 或多个  |
| `\+`   | `+`  | 至少 1 个 |
