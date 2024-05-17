## 安装

1. 确保系统中已安装过 Ruby、RubyGems，如果没有，按以下命令安装

```bash
sudo apt install ruby-rubygems -y
```

2. 安装 Jekyll

```bash
sudo gem install jekyll
```

## 例子

生成并运行一个简单模板

```bash
jekyll new myblog
cd myblog
jekyll serve
```

然后就可以在 http://localhost:4000 访问这个模板

## 常用命令

- 当前文件夹中的内容将会生成到 ./\_site 文件夹中。

```bash
jekyll build
```

- 当前文件夹中的内容将会生成到目标文件夹<destination>中。

```bash
jekyll build --destination <destination>
```

- 指定源文件夹<source>中的内容将会生成到目标文件夹<destination>中。

```bash
jekyll build --source <source> --destination <destination>
```

- 当前文件夹中的内容将会生成到 ./\_site 文件夹中，文件内容改变后，并自动更新。

```bash
jekyll build --watch
```

- 启动服务器，默认端口为 4000 并自动更新文件内容

```bash
jekyll serve
```

- 启动服务器，默认端口为 4000 并自动更新文件内容，但是会脱离终端在后台运行。

```bash
jekyll serve --detach
#关闭服务器，可以使用如下命令
kill `ps -ef|grep java|sed -n '1p'|awk '{print $2}'`
```

- 启动服务器，默认端口为 4000 但不自动更新文件内容

```bash
jekyll serve --no-watch
```

## 配置项

可以在根目录中的`_config.yml`添加相关配置

### 服务选项 Permalink

除了下边的选项， serve 命令还可以接收 build 的选项，当运行网站服务之前的编译时候使用。

| 设置                                         | 选项                         |
| -------------------------------------------- | ---------------------------- |
| 修改 Jekyll 读取文件的路径                   | source: DIR                  |
| 修改 Jekyll 写入文件的路径                   | destination: DIR             |
| 禁用自定义插件                               | safe: BOOL                   |
| 转换时排除某些文件、文件夹                   | exclude: [DIR, FILE, ...]    |
| 转换时强制包含某些文件、文件夹               | include: [DIR, FILE, ...]    |
| 当生成站点时，保留选择的文件。               | keep_files: [DIR, FILE, ...] |
| 设置时区，默认值为操作系统的时区。           | timezone: TIMEZONE           |
| 设置文件的编码。                             | encoding: ENCODING           |
| 用将来的日期发布文章                         | future: BOOL                 |
| 为相关文章生成索引                           | lsi: BOOL                    |
| 限制文章的数量                               | limit_posts: NUM             |
| 生成一个 Liquid 概述文档来帮助你发现性能瓶颈 | profile: BOOL                |
| 监听端口                                     | port: PORT                   |
| 监听主机名                                   | host: HOSTNAME               |
| 网站的根路径                                 | baseurl: URL                 |
| 从终端命令行中分离出来                       | detach: BOOL                 |

## 目录结构

```
.
├── \_config.yml              保存配置数据。
├── \_drafts                  是未发布的文章
├── \_includes                可重用文件
├── \_layouts                 包裹在文章外部的模板
├── \_posts                   文章（名称格式：yyyy-MM-dd-title.md）
├── \_site                    默认生成页面的位置
├── .jekyll-metadata
└── index.html
```

## 问题

1. exit code 1

```log
ERROR:  Error installing jekyll:
        ERROR: Failed to build gem native extension.

    current directory: /var/lib/gems/3.0.0/gems/ffi-1.15.5/ext/ffi_c
/usr/bin/ruby3.0 -I /usr/lib/ruby/vendor_ruby -r ./siteconf20221128-1219-b5kdxo.rb extconf.rb
mkmf.rb can't find header files for ruby at /usr/lib/ruby/include/ruby.h

You might have to install separate package for the ruby development
environment, ruby-dev or ruby-devel for example.

extconf failed, exit code 1
```

解决方法：

执行`sudo apt install ruby-dev`
