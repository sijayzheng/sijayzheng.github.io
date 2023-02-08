## 并发

## 锁

## 线程池

## AQS

## CAS

## ThreadLocal

## 注意事项

函数尽可能短，太长的函数拆分。

控制块尽可能短，太长的控制块抽出函数。

条件判断时，短分支写在长分支前面。条件判断时，能不写 else 分支就不写，else if 除外变量声明和变量使用的间隔尽可能地短。

变量的作用域尽可能的短。

得到返回值尽可能早的 return，清理操作放 finally。

发现异常尽可能早的 throw，哪怕 catch 再 rethrow。

## String

String… strings 为可变长度参数列表，表示 0 或多个 String 类型的对象或是 String[]，具体类型为一个数组

String.split()中“.” “\” "|" "\*" "+"需要转义

## Map

|               | 有序 | 顺序         | null 值            |
| ------------- | ---- | ------------ | ------------------ |
| HashMap       | 无   |              | 允许               |
| LinkedHashMap | 有序 | 放入顺序     | 允许               |
| TreeMap       | 有序 | key 值排序   | 不允许 key 为 null |
| HashTable     | 有序 | 放入顺序倒序 | 不允许             |

## List

- 合并两个相同类型的 List 并去重

```
list1.removeAll(list2);
list1.addAll(list2);
```

- 排序

```
Comparator.comparingInt(Integer::intValue).reversed() ``// 倒序
Comparator.comparingInt(Integer::intValue) ``// 正序
```

## 问题

1. org.apache.catalina.session.StandardManager doLoad

tomcat 的 work 目录下面的东西删一遍。

2. java.lang.IllegalStateException
3. org.hibernate.MappingException

hibernate 映射文件错误

4. java.lang.RuntimeException:

运行时异常，所有 jvm 异常的父类

5. java.lang.IllegalArgumentException:
   `Can not find a java.io.InputStream with the name [downloadFile] in the invocation stack. Check the <param name="inputName"> tag specified for this action.`

action 中缺少相应的方法
