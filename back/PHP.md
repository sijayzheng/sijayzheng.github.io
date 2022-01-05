## 环境搭建

1. 下载并解压 php 环境

2. 进入 php 文件夹
3. 复制 php.ini-development 并重命名为 php.ini 并打开
4. 将; extension_dir = "ext"改为 extension_dir = "D:/php/ext"，
5. 将;extension=bz2 ;extension=curl ;extension=gd2 ;extension=mbstring ;extension=mysqli ;extension=pdo_mysql 前的分号去掉。
6. 重启 apache2.4 服务，并在 Apache24/htdocs 中新建文件 index.php 并写入

```php
<?php
echo phpinfo();
```

7. 在浏览器中打开 localhost/index.php,显示 PHP 的信息则证明 PHP 配置成功。
