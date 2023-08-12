```bash
#1.生成私钥文件 会要求设置密码
openssl genrsa -out CA.key -des3 2048
#2.生成CA根证书
#会先验证上一步设置的密码,然后需要按提示输入
#Country Name (2 letter code) [AU]:
#State or Province Name (full name) [Some-State]:
#Locality Name (eg, city) []:
#Organization Name (eg, company) [Internet Widgits Pty Ltd]:
#Organizational Unit Name (eg, section) []:
#Common Name (e.g. server FQDN or YOUR name) []:
#Email Address []:
openssl req -x509 -sha256 -new -nodes -days 3650 -key CA.key -out CA.pem
#3.创建ext文件 localhost.ext 文件内容
authorityKeyIdentifier = keyid,issuer
basicConstraints = CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names

[alt_names]
DNS.1 = localhost
IP.1 = 127.0.0.1
5 创建CSR文件的key文件

openssl genrsa -out localhost.key -des3 2048
6 生成CSR文件

openssl req -new -key localhost.key -out localhost.csr
7 给CSR文件签名，生成证书crt

openssl x509 -req -in localhost.csr -CA CA.pem -CAkey CA.key -CAcreateserial -days 3650 -sha256 -extfile localhost.ext -out localhost.crt
8 生成证书的key文件

openssl rsa -in localhost.key -out localhost.decrypted.key
格式转换
Convert CRT to PEM:

openssl x509 -in cert.crt -out cert.pem
Convert CER to PEM

openssl x509 -in cert.cer -out cert.pem
Convert DER to PEM

openssl x509 -in cert.der -out cert.pem
Convert CRT to CER

Double-click on the file labeled .crt to open it into the certificate display.
Select the Details tab, and then click Copy to File.
Click the Next option in the certificate wizard.
Choose Base-64 encoded X.509 (.cer), and then click on Next.
Now, browse to store your file and type in the filename that you want to keep
Finally, save the file.
```
