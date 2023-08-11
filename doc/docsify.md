## 环境配置

安装工具

```bash
npm i docsify-cli -g
```

初始化项目

```bash
docsify init ./docs
```

启动预览服务器(默认访问地址 http://localhost:3000)

```bash
docsify serve docs
```

## 目录结构

- index.html 入口文件
- README.md 会做为主页内容渲染
- .nojekyll 用于阻止 GitHub Pages 忽略掉下划线开头的文件
