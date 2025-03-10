## 异常

> HTTP Status 404-There is no Action mapped for namespace[/] and action name [ActionName] associated with context
> path [/status]

- 未定义 action;或拼写错误;或大小写不一致
- 多个 status 配置文件中的包名或命名空间冲突
- struts.xml 文件名错误。一定要注意拼写问题；
- struts.xml 文件放置路径错误。一定要将此文件放置在 src 目录下。编译成功后，要确认是否编译到 classes 目录中；
- struts.xml 文件内容错误；
- 删掉 struts.xml 的 namespace。

>
`Can not find a java.io.InputStream with the name [downloadFile] in the invocation stack. Check the <param name="inputName"> tag specified for this action.`

action 中缺少相应的方法
