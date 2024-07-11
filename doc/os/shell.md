### java程序的启停

```shell
#---------stop-------------
#!/bin/bash

app_path=/home/appusr/app/comm-service
app_name=comm-service.jar
PID=$(ps -ef | grep $app_name | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
echo $app_name is already stopped
else
echo kill $PID
kill -9 $PID
fi

#--------start--------------
#!/bin/bash
app_path=/home/appusr/app/comm-service
app_name=comm-service.jar
cd $app_path
nohup java -Xms512m -Xmx2048m -Xss5m -XX:-UseGCOverheadLimit -jar -Dspring.config.location=config/application.yml -Duser.timezone=GMT+08 $app_name >$app*path/startup*`date+%Y%m%d%H`.log 2>&1 &
```

### 数组循环输出

```shell
#!/bin/bash
arr=('aa bb' 'cc dd')
for a in ${arr[*]}
do
  echo $a
done
echo '--------------------------'
for b in ${arr[@]}
do
  echo $b
done
```

### 文件批量改名

```shell
#!/bin/bash
for file in $(ls)
do
    f=${file:0:1}
    echo 将$file改名为${f^^}${file:1}
    mv $file ${f^^}${file:1}
done
```

### 根据正则表达式批量更改文件内容

```shell
for file in $(ls)
do
    echo 读取文件 ${file}
    sed -i -E "s/<script setup name=\"\w+\">/<script setup name=\"${file/%.vue/}\">/g" $file
done
```

### 删除空文件夹

```shell
find -type d -empty -exec rm -rf {} \;
```
