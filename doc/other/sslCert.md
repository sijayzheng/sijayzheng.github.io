## 本地生成 ssl 证书

```bash
#1.生成私钥文件 会要求设置密码
openssl genrsa -out CA.key -des3 2048
#2.生成CA根证书
openssl req -x509 -sha256 -new -nodes -days 3650 -key CA.key -out CA.pem
#3.创建ext文件 localhost.ext 文件内容
authorityKeyIdentifier = keyid,issuer
basicConstraints = CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names
[alt_names]
DNS.1 = localhost
IP.1 = 127.0.0.1
#4.创建CSR文件的key文件
openssl genrsa -out private.key -des3 2048
#5.生成CSR文件
openssl req -new -key private.key -out cert.csr
#6.给CSR文件签名，生成证书crt
openssl x509 -req -in cert.csr -CA CA.pem -CAkey CA.key -CAcreateserial -days 3650 -sha256 -extfile cert.ext -out cert.crt
#7.生成证书的key文件
openssl rsa -in private.key -out cert.key

#8.格式转换
# CRT to PEM:
openssl x509 -in cert.crt -out cert.pem
# CER to PEM
openssl x509 -in cert.cer -out cert.pem
# DER to PEM
openssl x509 -in cert.der -out cert.pem
# CRT to CER
openssl x509 -in cert.crt -out cert.cer
```

_**注意：**_：在生成过程中会有以下内容需要填写

- Country Name (2 letter code) [AU]: **_国家_**
- State or Province Name (full name) [Some-State]: **_州或省_**
- Locality Name (eg, city) []: **_市_**
- Organization Name (eg, company) [Internet Widgits Pty Ltd]: **_组织名称_**
- Organizational Unit Name (eg, section) []: **_组织单位名称_**
- Common Name (e.g. server FQDN or YOUR name) []: **_域名_**
- Email Address []: **_电子邮件地址_**

## 使用 certbot 生成证书

```bash
 install certbot
```

### 使用 nginx 服务器时

```bash
apt python2-certbot-nginx
certbot --nginx
```

## 续租

```bash
certbot renew
```
