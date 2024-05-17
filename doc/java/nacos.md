## 安装

1. 源码方式

```bash
从 Github 上下载源码方式
git clone https://github.com/alibaba/nacos.git
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U
ls -al distribution/target/

// 将$version改为你编译的版本
cd distribution/target/nacos-server-$version/nacos/bin
```

2. 编译完成的压缩包

```bash
  unzip nacos-server-$version.zip 或者 tar -xvf nacos-server-$version.tar.gz
  cd nacos/bin
```

## 启动

```bash
sh startup.sh -m standalone
```

