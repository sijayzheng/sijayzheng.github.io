## 基本知识

### DB2 命令：

1. 启动数据库 DB2START；关闭数据库 DB2STOP;强制关闭数据库 DB2STOP FORCE
2. 连接数据库时使用 `DB2 CONNECT TO 数据库名 USER 用户名 USING 用户密码`
3. 导出 `DB2 EXPORT TO 文件名 OF DEL SELECT \* FROM 表名；`
4. 导入`DB2 IMPORT FROM 文件名 OF DEL INSERT INTO 表名`


2. 视图
   即为一虚拟表，其数据来自一个或多个表或视图；使用视图一为安全，二为是复杂查询易于理解，即为权限控制，屏蔽细节，保护表数据，简化查询。
3. 索引
   可理解为目录，优点：加快数据检索速度；创建唯一性索引，保证数据库表中每一行数据的唯一性；加速表和表之间的连接；在使用分组和排序子句进行数据检索时，可以显著减少查询中分组和排序的时间。缺点：索引需要占物理空间；当对表中的数据进行增加、删除和修改的时候，索引也要动态的维护，降低了数据的维护速度。
4. REORG
   修改表结构后需要执行 REORG 操作，数据表有数据损坏时也可进行 REORG 操作,即：DB2 REORG TABLE 数据表名。
5. INSERT INTO 表 1(V1,V2,V3) SELECT T2.F2,T3.F3,S1 FROM 表 2 T2 LEFT JOIN 表 3 T3 ON 表连接条件 WHERE 约束
6. DB2 的分页查询

SELECT \* FROM ( SELECT 字段 1,字段 2,ROWNUMBER() OVER(ORDER BY 字段 1 DESC)AS RNUM FROM 表 1) WHERE RNUM BETWEEN x AND y

从表一中查询字段 1 和字段 2，并以字段 1 倒序排列取出第 x 行到 y 行的数据

7. DB2 的字段自增

GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1 )

当该字段的数据类型为 INT 时，可用该语句自动对其赋值

8. 日期时间格式化

TO_CHAR(时间,'时间格式')

时间要求为标准时间，时间格式：YYYY 年，MM 月，DD 日，HH 时(HH24 24 小时制的时)，MI 分，SS 秒

9. 删除本地数据库目录或系统数据库目录中已经存在数据库别名（SQL1005N）

db2 list database directory

db2 list database directory on d：

db2 catalog database DB on d:

db2 drop database DB

10. DB2 主键自增长设置（纯数字 id 自增）

CREATE TABLE

USER

(

ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1 ),

NAME VARCHAR(150),

PRIMARY KEY (ID)

)

其中 GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1 )就是主键自增。

start with 1 ：就是从 1 开始

increment by 1 ：每次自增 1

11. 备份数据库

db2 terminate

db2 deactivate db 数据库名

db2 backup database 数据库名 to 存放目录

12. 还原数据库

db2 RESTORE DB 目标数据库别名 [FROM 存放目录] [TAKEN AT 时间戳]

## DB2 常用函数

| 函数名                          | 函数作用                                                             |
|------------------------------|------------------------------------------------------------------|
| AVG()                        | 返回一组数值的平均值                                                       |
| BIGINT()                     | 返回整型常量中的数字或字符串的 64 位正数表示                                         |
| CAST()                       | 用来转换类型使用                                                         |
| CEILING() OR CEIL()          | 返回比参数大或等于参数的最小的整数值                                               |
| CHAR()                       | 返回日期时间型，字符串，整数，十进制或双精度浮点数的字符串表示                                  |
| COALESCE(arg1,arg2….)        | 返回参数集中第一个非 null 参数                                               |
| CONCAT(arg1,arg2)            | 返回两个字符串的连接                                                       |
| CONCAT(arg1,arg2)            | 返回两个字符串的连接                                                       |
| COUNT()                      | 返回一组行或值的个数                                                       |
| DATE()                       | 返回一个日期、时间戳、日期字符串、时间戳字符串中的日期                                      |
| DAY（）                        | 返回一个日期、时间戳、日期字符串、时间戳字符串的日部分                                      |
| DAYNAME（）                    | 返回一个日期、时间戳、日期字符串、时间戳字符串是星期几                                      |
| DAYOFWEEK（）                  | 返回一个日期、时间戳、日期字符串、时间戳字符串在周内的天值(从周日开始 1 到 7)                       |
| DAYOFWEEK_ISO（）              | 返回一个日期、时间戳、日期字符串、时间戳字符串在周内的天值(从周一开始 1 到 7)                       |
| DAYOFYEAR（）                  | 返回一个日期、时间戳、日期字符串、时间戳字符串在年内的天值                                    |
| DAYS（）                       | 返回一个日期、时间戳、日期字符串、时间戳字符串的日期整数表示法                                  |
| DOUBLE()                     | 如果参数是一个数字表达式，返回与其对应的浮点数，如果参数是字符串表达式，则返回概述的字符串表达式                 |
| FLOAT()                      | 返回一个数的浮点表示                                                       |
| FLOOR()                      | 返回小于或等于参数的最大整数                                                   |
| HOUR()                       | 返回一个时间、时间戳、时间字符串、时间戳字符串的小时部分                                     |
| INSERT(arg1,pos,size,arg2)   | 返回一个，将 arg1 从 pos 处删除 size 个字符，将 arg2 插入该位置                      |
| LEFT(arg,length)             | 返回 arg 最左边的 length 个字符串,arg 可以是 char 或 binary string             |
| LENGTH()                     | 返回参数的长度                                                          |
| LOCATE(arg1,arg2,)           | 在 arg2 中查找 arg1 第一次出现的位置，指定 pos，则从 arg2 的 pos 处开始找 arg1 第一次出现的位置 |
| LOWER()                      | 返回定长、变长字符串的小写形式                                                  |
| LTRIM()                      | 从 CHAR, VARCHAR, GRAPHIC, or VARGRAPHIC 中去掉左侧的空格                 |
| MAX()                        | 返回一组数值中的最大值                                                      |
| MIDNIGHT_SECONDS（）           | 返回一个时间、时间戳、时间字符串、时间戳字符串的月份名                                      |
| MIN()                        | 返回一组数值中的最小值                                                      |
| MINUTE()                     | 返回一个时间、时间戳、时间字符串、时间戳字符串的分钟部分                                     |
| MOD(EXP1,EXP2)               | 返回 EXP1 除以 EXP2 的余数                                              |
| MONTH()                      | 返回一个日期、时间戳、日期字符串、时间戳字符串的月部分                                      |
| MONTHNAME（）                  | 返回一个日期、时间戳、日期字符串、时间戳字符串的月份名                                      |
| NULLIF(EXP1,EXP2)            | 如果 EXP1=EXP2，则为 NULL,否则为 EXP1                                    |
| POSSTR(EXP1,EXP2)            | 返回 EXP2 在 EXP1 中的位置                                              |
| QUARTER（）                    | 返回一个日期、时间戳、日期字符串、时间戳字符串的季度部分                                     |
| RAND()                       | 返回 0 和 1 之间的随机浮点数                                                |
| REPEAT(arg1 ,num_times)      | 返回 arg1 被重复 num_times 次的字符串                                      |
| REPLACE(EXP1,EXP2,EXP3)      | 用 EXP3 替代 EXP1 中所有的 EXP2                                         |
| RIGHT(arg,length)            | 返回 arg 最右边的 length 个字符串,arg 可以是 char 或 binary string             |
| ROUND(EXP1,EXP2)             | 返回 EXP1 小数点右边的第 EXP2 位置处开始的四舍五入值                                 |
| RTRIM()                      | 从 CHAR, VARCHAR, GRAPHIC, or VARGRAPHIC 中去掉右侧的空格                 |
| SECOND()                     | 返回一个时间、时间戳、时间字符串、时间戳字符串的秒部分                                      |
| SPACE()                      | 返回由参数指定的长度，包含空格在内的字符串                                            |
| SPACE(size)                  | 返回一个包含 size 个空格的字符串                                              |
| SUBSTR (arg1,pos,)           | 返回 arg1 中 pos 位置开始的 length 个字符，如果没指定 length，则返回剩余的字符             |
| SUBSTR(EXP1,EXP2)            | 返回 EXP1 串自 EXP2 处开始的字串                                           |
| SUM()                        | 返回一组数据的和                                                         |
| TIME()                       | 返回一个时间、时间戳或一个时间、时间戳字符串参数中的时间                                     |
| TIMESTAMP(expres1[,expres2]) | 返回一个或两个参数中的时间戳                                                   |
| TRIM()                       | 从 CHAR, VARCHAR, GRAPHIC, or VARGRAPHIC 中去掉左右侧的空格                |
| TRUNCATE() OR TRUNC()        | 从表达式小数点右边的位置开始截断并返回该数值                                           |
| UPPER()                      | 返回定长、变长字符串的大写形式                                                  |
| VARCHAR()                    | 返回字符型，日期型，图形串的可变长度字符串表示                                          |
| WEEK（）                       | 返回一个日期、时间戳、日期字符串、时间戳字符串的星期部分                                     |
| YEAR()                       | 返回一个日期、时间戳、日期字符串、时间戳字符串的年部分                                      |

## 常用操作

### 行转列

## 常见问题
