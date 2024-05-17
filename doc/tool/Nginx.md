## 概述

什么是 Nginx?
Nginx (engine x) 是一款轻量级的 Web 服务器 、反向代理服务器及电子邮件（IMAP/POP3）代理服务器。

## 反向代理

反向代理（Reverse Proxy）方式是指以代理服务器来接受 internet 上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给
internet 上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。
![image](img/pic9.webp)

## 使用

常用命令如下：

| 命令                      | 作用                             |
|-------------------------|--------------------------------|
| `nginx -?/-h`           | 帮助                             |
| `nginx -c [file]`       | 指定配置文件                         |
| `nginx -e [file]`       | 指定日志存储文件                       |
| `nginx -g [directives]` | 指定全局配置指令                       |
| `nginx -s s`top         | 快速关闭Nginx，可能不保存相关信息，并迅速终止web服务 |
| `nginx -s q`uit         | 平稳关闭Nginx，保存相关信息，有安排的结束web服务   |
| `nginx -s r`eload       | 重新加载配置                         |
| `nginx -s r`eopen       | 重新打开日志文件                       |
| `nginx -t`              | 检查配置语法并尝试打开配置引用到的文件            |
| `nginx -T`              | 检查配置语法并输出配置内容                  |
| `nginx -v`              | 打印Nginx版本.                     |
| `nginx -V`              | 打印Nginx版本, 编译版本, 配置参数.         |

## 配置

```ini
#运行用户
user root;
#worker进程数量,通常设置成和cpu的数量相等
worker_processes  1;
#全局错误日志
#工作模式及连接数上限
events {
  #单个后台worker process进程的最大并发链接数
  worker_connections 1024;
  #一次接受所有新连接
  multi_accept on;
  #工作进程将依次接受新连接
  accept_mutex on;
  accept_mutex_delay 500ms;
  worker_aio_requests 32;
}

#http服务器，做反向代理、负载均衡
http {
  #设定mime类型(邮件支持类型),类型由mime.types文件定义
  include mime.types;
  default_type  application/octet-stream;

  #设定日志
  log_format main '$remote_addr - $remote_user [$time_local]  $status '
  '"$request" $body_bytes_sent "$http_referer" '
  '"$http_user_agent" "$http_x_forwarded_for"';


  sendfile        on;
  tcp_nopush  on;
  keepalive_timeout  120;
  tcp_nodelay     on;
  types_hash_max_size 2048;
  server_name_in_redirect off;
  server_names_hash_bucket_size 128;

  ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
  ssl_ciphers HIGH:!aNULL:!MD5;
  ssl_prefer_server_ciphers on;

  #gzip压缩开关
  gzip on;
  gzip_vary on;
  gzip_proxied any;
  gzip_min_length 4k;
  gzip_comp_level 6;
  gzip_buffers 16 8k;
  gzip_http_version 1.1;
  gzip_types text/plain text/css application/json application/javascript application/x-javascript text/xml application/xml application/xml+rss text/javascript;


  client_body_buffer_size 8m;
  client_header_buffer_size 32k;
  client_max_body_size 1024m;

  proxy_headers_hash_max_size 51200;
  proxy_headers_hash_bucket_size 6400;

  map $http_upgrade $connection_upgrade {
    default upgrade;
    '' close;
  }


  #HTTP服务器
  server {
    #监听端口
    listen 80;
    listen [::]:80;
    listen 443 ssl;
    listen [::]:443 ssl;
    #定义使用www.xx.com访问
    server_name  _;
    #首页
    index index.html
    #指向webapp的目录
    root /var/www/html;
    #编码格式
    charset utf-8;

    ssl_certificate cert/localhost.crt;
    ssl_certificate_key cert/localhost.key;

    #代理配置参数
    proxy_connect_timeout 180;
    proxy_send_timeout 180;
    proxy_read_timeout 180;
    proxy_set_header Host $host;
    proxy_set_header X-Forwarder-For $remote_addr;
    #反向代理的路径（和upstream绑定），location 后面设置映射的路径
    location / {
      root /var/www/html;
      index index index.html;
      try_files $uri $uri/ =404;
    }

    location /docs {
      alias /mnt/c/users/sijay/code/z/;
      index index index.html;
    }

    location /sijay {
      proxy_pass      http://sijayzheng.github.io;
    }

    #错误处理页面（可选择性配置）
    error_page   404             /404.html;
    error_page   500 502 503 504  /50x.html;

    #静态文件，nginx自己处理
    #location ~ ^/(images|javascript|js|css|flash|media|static)/ {
    #    root /var/www/html/static;
    #    #过期30天，静态文件不怎么更新，过期可以设大一点，如果频繁更新，则可以设置得小一点。
    #    expires 30d;
    #}
    #设定查看Nginx状态的地址
    location /NginxStatus {
        stub_status         on;
    }
    #禁止访问 .htxxx 文件
    # location ~ /\.ht {
    #     deny all;
    # }
  }
}
```

## 负载均衡配置

假设应用部署在 192.168.1.11:80、192.168.1.12:80、192.168.1.13:80 三台服务器上。 网站域名为 www.helloworld.com。在公网可访问的服务器上部署nginx，对所有请求做负载均衡处理。nginx.conf
配置如下：

```ini
http {
  ...
  #设定负载均衡的服务器列表
  upstream load_balance_server {
    #weigth参数表示权值，权值越高被分配到的几率越大
    server 192.168.1.11:80   weight=5;
    server 192.168.1.12:80   weight=1;
    server 192.168.1.13:80   weight=6;
  }
  #HTTP服务器
  server {
    #侦听80端口
    listen       80;
    #定义使用www.xx.com访问
    server_name  www.helloworld.com;
    #对所有请求进行负载均衡请求
    location / {
      root        /root;
      #定义服务器的默认网站根目录位置
      index       index.html index.htm;
      #定义首页索引文件的名称
      proxy_pass  http://load_balance_server ;
      ...
    }
  }
}
```

## 反向代理配置

假如 www.helloworld.com 站点有 finance（金融）、product（产品）、admin（用户中心）。

```
http {
  ...
  upstream product_server{
    server www.helloworld.com:8081;
  }
  upstream admin_server{
    server www.helloworld.com:8082;
  }
  upstream finance_server{
    server www.helloworld.com:8083;
  }
  server {
    ...
    #默认指向product的server
    location / {
      proxy_pass http://product_server;
    }
    location /product/{
      proxy_pass http://product_server;
    }
    location /admin/ {
      proxy_pass http://admin_server;
    }
    location /finance/ {
      proxy_pass http://finance_server;
    }
  }
}
```

## nginx 配置 https ：

- HTTPS 的固定端口号是 443，不同于 HTTP 的 80 端口
- SSL 标准需要引入安全证书，所以在 nginx.conf 中你需要指定证书和它对应的 key

```ini
server { 
  #监听 443 端口。443 为知名端口号，主要用于 HTTPS 协议
  listen 443 ssl; #定义使用www.xx.com访问
  server_name www.helloworld.com;
  #ssl 证书文件位置(常见证书文件格式为：crt/pem)
  ssl_certificate cert.pem;
  #ssl 证书 key 位置
  ssl_certificate_key cert.key;
  #ssl 配置参数（选择性配置）
  ssl_session_cache shared:SSL:1m;
  ssl_session_timeout 5m; 
  #数字签名，此处使用 MD5
  ssl_ciphers HIGH:!aNULL:!MD5;
  ssl_prefer_server_ciphers on;
  location / {
    root /root;
    index index.html index.htm;
  } 
}
```

## 静态站点配置

```ini
...
http {
  ...
  server {
    listen 80;
    server_name hello.world;
    location / {
      root /app/dist;
      index index.html;
    }
  }
}

```

添加 HOST：`127.0.0.1 hello.world`后,在本地浏览器访问 hello.world ，就可以访问静态站点了。

## 搭建文件服务器

- 将 autoindex 开启可以显示目录，默认不开启。
- 将 autoindex_exact_size 开启可以显示文件的大小。
- 将 autoindex_localtime 开启可以显示文件的修改时间。
- root 用来设置开放为文件服务的根路径。
- charset 设置为 charset utf-8,gbk;，可以避免中文乱码问题

```ini
...
  autoindex on;
  # 显示目录
  autoindex_exact_size on;
  # 显示文件大小
  autoindex_localtime on;
  # 显示文件时间
  server { 
    charset utf-8,gbk; 
    listen 80;
    listen [::]:80;
    server_name _;
    root /share/fs;
  }
...
```

## 跨域解决方案

### CORS

在后端服务器设置 HTTP 响应头，把你需要运行访问的域名加入加入 Access-Control-Allow-Origin 中。

### jsonp

首先，在 enable-cors.conf 文件中设置 cors ：

```
# allow origin listset $ACAO '\*';
# set single origin
if ($http_origin ~* (www.helloworld.com)$) {
  set $ACAO $http_origin;
}
if ($cors = "trueget") {
  add_header 'Access-Control-Allow-Origin' "$http_origin";
  add_header 'Access-Control-Allow-Credentials' 'true';
  add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
  add_header 'Access-Control-Allow-Headers' 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type';
}
if ($request_method = 'OPTIONS') {
  set $cors "${cors}options";
}
if ($request_method = 'GET') {
  set $cors "${cors}get";
}
if ($request_method = 'POST') {
  set $cors "${cors}post";
}

# 此文件为项目 nginx 配置片段
# 可以直接在 nginx config 中 include（推荐）
# 或者 copy 到现有 nginx 中，自行配置
# www.helloworld.com 域名需配合 dns hosts 进行配置
# 其中，api 开启了 cors，需配合本目录下另一份配置文件

server {
  listen 80;
  server_name www.helloworld.com;
  location ~ ^/api/ {
    include enable-cors.conf;
    proxy_pass http://www.helloworld.com:8080;
    rewrite "^/api/(.\*)$" /$1 break;
  }
}
```

## Lvs

## Lua
