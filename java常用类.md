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
