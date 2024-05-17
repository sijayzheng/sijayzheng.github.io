## 启动前端项目

### 问题

通过`npm run dev`启动项目后报错

错误的主要信息为：

```
  opensslErrorStack: [ 'error:03000086:digital envelope routines::initialization error' ],
  library: 'digital envelope routines',
  reason: 'unsupported',
  code: 'ERR_OSSL_EVP_UNSUPPORTED'
}

Node.js v18.16.0
ELIFECYCLE Command failed with exit code 1.
```

原因是nodejs版本过高

### 解决方案

在 package.json 中的 scripts 下的 dev 开头添加`set NODE_OPTIONS=--openssl-legacy-provider && `

```
"dev": "set NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service serve",
```

## org.apache.catalina.session.StandardManager doLoad

tomcat 的 work 目录下面的东西删一遍。

## org.hibernate.MappingException

hibernate 映射文件错误

## java.lang.IllegalArgumentException:

`Can not find a java.io.InputStream with the name [downloadFile] in the invocation stack. Check the <param name="inputName"> tag specified for this action.`

action 中缺少相应的方法
