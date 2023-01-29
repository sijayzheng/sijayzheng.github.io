## 异常

### org.apache.catalina.session.StandardManager doLoad

tomcat 的 work 目录下面的东西删一遍。

### Request Entity Too Large

请求体过大，多见于文件上传时触发，设置 connector 的 maxPostSize 为大点的值即可，单位 byte，默认值：2097152 (2 megabytes)。若想将其设为无限制，则将其设置为负数即可。

```xml
<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" maxPostSize="209715200"/>
```

### Request header is too large

从字面意思可知，是请求头过大，设置 connector 的 maxHttpHeaderSize 为大点的值即可，单位 byte，默认值：8192 (8 KB)

设置 maxHttpHeaderSize 限制为 16KB，可根据需求适当加到更大

```xml
<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" maxHttpHeaderSize="16384"/>
```

该值设置过大，容易造成内存溢出的问题。

### URL 传参错误

```log
信息: Error parsing HTTP request header
 Note: further occurrences of HTTP header parsing errors will be logged at DEBUG level.
java.lang.IllegalArgumentException: Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
    at org.apache.coyote.http11.InternalAprInputBuffer.parseRequestLine(InternalAprInputBuffer.java:238)
    at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1028)
    at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:637)
    at org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.doRun(AprEndpoint.java:2555)
    at org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.run(AprEndpoint.java:2544)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.lang.Thread.run(Thread.java:745)
```

RFC3986(RFC7230 是 RFC3986 的补充完善)中的一下规定：

1、保留字符包含 gen-decimal(包含 `: / ? # [ ] @` 7 个) 和 sub-decimal(包含 `! $ & ' ( ) \* + , ; =`) 11 个。

2、URI 中允许但没有保留的字符，被称为无保留。这些包括`大写和小写字母(a-zA-Z)，十进制数字(0-9)，连字符，句点，下划线和波形符号(- \_ . ~)`

不安全字符是指那些在 URL 中没有特殊含义，但在 URL 所在的上下文中可能具有特殊意义的字符 `` 空格、引号、< > # % { } | \ ^ [ ] ` ~ `` 其中的这些不安全字符一般都需要编码

特别注意 `+` 需要使用 encodeURIComponent()转码
