@echo off

mysqldump -uroot -proot

mvn clean package -Dmaven.test.skip=true