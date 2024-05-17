## 配置文件

可以自动加载配置，配置文件包括四种格式：properties,yml,json,xml，配置文件优先级依次为 `log4j2.configurationFile > log4j2-test.properties > log4j2-test.yaml或log4j2-test.yml > log4j2-test.json或log4j2-test.jsn > log4j2-test.xml > log4j2.properties > log4j2.yaml或log4j2.yml > log4j2.json或log4j2.jsn > log4j2.xml > DefaultConfiguration`
，配置文件需要放在 classpath 路径下

yml和json的配置文件需要额外引入相应的解析类库

### log4j2.properties

```properties
# 名称
name=PropertiesConfig
# 全局日志级别
status=INFO

# 过滤器
# 过滤器类型
filter.threshold.type = ThresholdFilter
# 过滤级别
filter.threshold.level = debug

# console
# 指定输出源的类型与名称
appender.console.type=Console
appender.console.name=Console
appender.console.layout.type=PatternLayout
# 输出模板
appender.console.layout.pattern=%d%p[%c]-%m%n

# rolling file
appender.rolling.type=RollingFile
appender.rolling.name=RollingFile
appender.rolling.fileName=logs/properties.log
# 指定当发生Rolling时，文件的转移和重命名规则
appender.rolling.filePattern=logs/properties.%d{YYYYMMdd}.log
appender.rolling.layout.type=PatternLayout
appender.rolling.layout.pattern=%d%p[%c]-%m%n
# 指定记录文件的封存策略，该策略主要是完成周期性的日志文件封存工作
appender.rolling.policies.type=Policies
# 基于时间的触发策略
appender.rolling.policies.time.type=TimeBasedTriggeringPolicy
# 当前记录周期为每天生成一个文件
appender.rolling.policies.time.interval=1
# 设置是否按小时来计算封存周期，否则以文件名格式
appender.rolling.policies.time.modulate = false

# 含有包含关系的类包，被子包的需要放在父包前面
loggers=org.hibernate.util.JDBCExceptionReporter,org.hibernate

logger.org.hibernate.util.JDBCExceptionReporter.name=org.hibernate.util.JDBCExceptionReporter
logger.org.hibernate.util.JDBCExceptionReporter.level=OFF
logger.org.hibernate.util.JDBCExceptionReporter.appenderRef.stdout.ref=Console
logger.org.hibernate.util.JDBCExceptionReporter.appenderRef.rolling.ref=RollingFile
logger.org.hibernate.util.JDBCExceptionReporter.additivity=false

logger.org.hibernate.name=org.hibernate
logger.org.hibernate.level=FATAL
logger.org.hibernate.appenderRef.stdout.ref=Console
logger.org.hibernate.appenderRef.rolling.ref=RollingFile
logger.org.hibernate.additivity=false

# rootLogger, 根记录器，所有记录器的父辈
# 指定根日志的级别
rootLogger.level=info
# 指定输出的appender引用
rootLogger.appenderRef.stdout.ref=Console
rootLogger.appenderRef.rolling.ref=RollingFile

```

### log4j2.yml

```yaml
Configuration:
  status: warn
  name: YAMLConfigTest
  properties:
    property:
      name: filename
      value: target/test-yaml.log
  thresholdFilter:
    level: debug
  appenders:
    Console:
      name: STDOUT
      target: SYSTEM_OUT
      PatternLayout:
        Pattern: '%m%n'
    File:
      name: File
      fileName: ${filename}
      PatternLayout:
        Pattern: '%d %p %C{1.} [%t] %m%n'
      Filters:
        ThresholdFilter:
          level: error
  Loggers:
    logger:
      - name: org.apache.logging.log4j.test1
        level: debug
        additivity: false
        ThreadContextMapFilter:
          KeyValuePair:
            key: test
            value: 123
        AppenderRef:
          ref: STDOUT
      - name: org.apache.logging.log4j.test2
        level: debug
        additivity: false
        AppenderRef:
          ref: File
    Root:
      level: error
      AppenderRef:
        ref: STDOUT
```

### log4j2.json

```json
{
  "configuration": {
    "status": "debug",
    "name": "RoutingTest",
    "packages": "org.apache.logging.log4j.test",
    "properties": {
      "property": { "name": "filename", "value": "target/rolling1/rollingtest-$${sd:type}.log" }
    },
    "ThresholdFilter": { "level": "debug" },
    "appenders": {
      "appender": [
        {
          "type": "Console",
          "name": "STDOUT",
          "PatternLayout": { "pattern": "%m%n" },
          "ThresholdFilter": { "level": "debug" }
        },
        {
          "type": "Routing",
          "name": "Routing",
          "Routes": {
            "pattern": "$${sd:type}",
            "Route": [
              {
                "RollingFile": {
                  "name": "Rolling-${sd:type}",
                  "fileName": "${filename}",
                  "filePattern": "target/rolling1/test1-${sd:type}.%i.log.gz",
                  "PatternLayout": { "pattern": "%d %p %c{1.} [%t] %m%n" },
                  "SizeBasedTriggeringPolicy": { "size": "500" }
                }
              },
              { "AppenderRef": "STDOUT", "key": "Audit" }
            ]
          }
        }
      ]
    },
    "loggers": {
      "logger": [
        { "name": "EventLogger", "level": "info", "additivity": "false", "AppenderRef": { "ref": "Routing" } },
        { "name": "com.foo.bar", "level": "error", "additivity": "false", "AppenderRef": { "ref": "STDOUT" } }
      ],
      "root": { "level": "error", "AppenderRef": { "ref": "STDOUT" } }
    }
  }
}
```

### log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
<!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数-->
<configuration status="WARN" monitorInterval="30">
  <!--先定义所有的appender-->
  <appenders>
    <!--这个输出控制台的配置-->
    <console name="Console" target="SYSTEM_OUT">
      <!--输出日志的格式-->
      <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
    </console>
    <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，这个也挺有用的，适合临时测试用-->
    <File name="log" fileName="log/test.log" append="false">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n" />
    </File>
    <!-- 这个会打印出所有的info及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
    <RollingFile name="RollingFileInfo" fileName="${sys:user.home}/logs/info.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
      <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
      <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY" />
      <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
      <Policies>
        <TimeBasedTriggeringPolicy />
        <SizeBasedTriggeringPolicy size="100 MB" />
      </Policies>
    </RollingFile>
    <RollingFile name="RollingFileWarn" fileName="${sys:user.home}/logs/warn.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log">
      <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY" />
      <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
      <Policies>
        <TimeBasedTriggeringPolicy />
        <SizeBasedTriggeringPolicy size="100 MB" />
      </Policies>
      <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件，这里设置了20 -->
      <DefaultRolloverStrategy max="20" />
    </RollingFile>
    <RollingFile name="RollingFileError" fileName="${sys:user.home}/logs/error.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
      <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY" />
      <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
      <Policies>
        <TimeBasedTriggeringPolicy />
        <SizeBasedTriggeringPolicy size="100 MB" />
      </Policies>
    </RollingFile>
  </appenders>
  <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
  <loggers>
    <!--过滤-->
    <logger name="org.springframework" level="INFO"></logger>
    <logger name="org.mybatis" level="INFO"></logger>
    <root level="all">
      <appender-ref ref="Console" />
      <appender-ref ref="RollingFileInfo" />
      <appender-ref ref="RollingFileWarn" />
      <appender-ref ref="RollingFileError" />
    </root>
  </loggers>
</configuration>
```

## 配置参数

### appender 组件列表

| append                           | 描述                                                                                                                                                                           |
|----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| AsyncAppender                    | 用于接受其他类型 appender 的引用，并使用单独线程异步写入 log                                                                                                                                        |
| CassandraAppender                | 将日志写入 Cassandra 数据库中，需要提前建立 keyspace 和 table                                                                                                                                 |
| ConsoleAppender                  | 其日志写入 System.out 或 System.err，默认为 System.out                                                                                                                                 |
| FailoverAppender                 | 故障转移 appender，可以指定主 appender，并且包含一组 appender 集合，当主 appender 写入失败，则会依次使用其他 append 写入，直至写入成功或者全部 appender 写入失败                                                                 |
| FileAppender                     | 将日志写入文件，使用 FileManager 执行 io                                                                                                                                                 |
| FlumeAppender                    | 将日志序列化后发送给 Flume 代理。 可选组件，由单独的 jar 提供。                                                                                                                                       |
| JDBCAppender                     | 使用标准 JDBC 将日志写入关系数据库表，必须使用连接池                                                                                                                                                |
| JMS Appender                     | 将日志发送至 JMS 中                                                                                                                                                                 |
| JPAAppender                      | 通过 JPA 将日志写入关系型数据库表，需要有单独的 persistence.xml 配置文件                                                                                                                              |
| HttpAppender                     | 通过 http 请求发送日志，使用 HttpURLConnection 实现，响应 2XX 状态码为成功，否则抛出异常                                                                                                                  |
| KafkaAppender                    | 将日志事件发送到 Kafka 的 topic 中                                                                                                                                                     |
| MemoryMappedFileAppender         | 2.1 新增功能，将指定日志文件的一部分映射至内存，并将新日志事件写入此内存，达到阈值时将此内存刷新至存储设备                                                                                                                      |
| NoSQLAppender                    | 使用内部轻量级 Provider 接口将日志事件写入 NoSQL 数据库。目前只有 MongoDB 和 Apache CouchDB 的 Provider 实现                                                                                             |
| NoSQLAppender for MongoDB        | 2.0.11 开始，提供两个 MongoDB 模块：log4j-mongodb2、log4j-mongodb3                                                                                                                      |
| NoSQLAppender for MongoDB 2      | 使用 MongoDB 驱动程序版本 2 将日志写入 MongoDB 中                                                                                                                                          |
| NoSQLAppender for MongoDB 3      | 使用 MongoDB 驱动程序版本 3 将日志写入 MongoDB 中                                                                                                                                          |
| NoSQLAppender for Apache CouchDB | 使用内部轻量级 Provider 将日志写入 CouchDB 中                                                                                                                                             |
| OutputStreamAppender             | OutputStreamAppender 不能直接配置，只是作为基础组件提供给其他 Appender 使用，如可以将日志事件写入输出流的 File 和 Socket                                                                                           |
| RandomAccessFileAppender         | 与 FileAppender 相比，使用的 I/O 实现类不同，FileAppender 使用 FileOutputStream，RandomAccessFileAppender 使用 RandomAccessFile。bufferedIO=true(默认是 true)时，性能提高 20-200％ 。                      |
| RewriteAppender                  | 用于在日志被其他 Appender 写入文件之前，通过 RewritePolicy 修改日志事件                                                                                                                             |
| RollingFileAppender              | 将日志写入文件，并根据 TriggeringPolicy 和 RolloverPolicy 规则将文件归档、清理                                                                                                                     |
| RollingRandomAccessFileAppender  | 与 RollingFileAppender 相比，使用的 I/O 实现类不同，RollingFileAppender 使用 FileOutputStream，RollingRandomAccessFileAppender 使用 RandomAccessFile。bufferedIO=true(默认是 true)时，性能提高 20-200％ 。 |
| RoutingAppender                  | 配置不同的规则，将日志路由到不同的 Appender 进行输出                                                                                                                                              |
| SMTPAppender                     | 发生指定日志事件时，发送电子邮件                                                                                                                                                             |
| ScriptAppenderSelector           | 根据 Script 脚本的执行结果来选择 AppenderSet 中配置的 Appender，并将结果输出至 ScriptAppenderSelector 的 name 中                                                                                       |
| SocketAppender                   | 通过 tcp 或者 udp，将日志写入远程目标中                                                                                                                                                     |
| SyslogAppender                   | SyslogAppender 是一个 SocketAppender，它将其输出以符合 BSD Syslog 或 RFC 5424 格式的日志写入到远程目标                                                                                                |
| ZeroMQ/JeroMQ Appender           | ZeroMQ Appender 使用 JeroMQ 库将日志事件发送到一个或多个 ZeroMQ 端点                                                                                                                           |

### ConsoleAppender

ConsoleAppender 比较简单，就是把日志写入 System.out 或者 System.err
中，基本配置如：`<Console name="STDOUT" target="SYSTEM_ERR"> <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} %t %5p [%c:%L] - %m%n" /></Console>`

Console 一般使用基本配置就可以，唯一要注意的就是输出格式 pattern，pattern 的配置释义如下：

| 参数                            | 描述                                                 |
|-------------------------------|----------------------------------------------------|
| %c 或%logger                   | 输出 logName                                         |
| %C 或%class                    | 输出为所在类的全路径名                                        |
| d{pattern}或 date{pattern}     | 输出时间，其中 pattern 可以是保留字，也可以是 SimpleDateFormat 中的字符。 |
| %F 或%file                     | 输出所在类名.java                                        |
| %l                            | 输出错误的完整位置，全路径类名.方法名(类名.java:行号)，                   |
| %L                            | 输出行号                                               |
| %m 或%msg 或%message            | 输出 log.error(text)中的 text 内容                       |
| %M 或%method                   | 输出方法名                                              |
| %n                            | 换行符                                                |
| %t 或%thread                   | 输出线程名                                              |
| %u{“RANDOM”\| “TIME”}或 uuid   | 输出 uuid                                            |
| %sn 或%sequenceNumber          | 输出自增序列                                             |
| %r 或%relative                 | 输出从 JVM 启动到当前时刻的毫秒数                                |
| %T 或%tid 或%threadId           | 输出线程 id                                            |
| %t 或%tn 或%thread 或%threadName | 输出线程 id                                            |
| %tp 或%threadPriority          | 输出线程优先级                                            |

### RollingFileAppender

RollingFileAppender 是一个 OutputStreamAppender，可以根据 TriggeringPolicy 和 RolloverPolicy 将文件切割归档，通过 RollingFileManager（扩展了
OutputStreamManager）来实际执行文件 I / O 并执行归档。参数如下：

| 参数               | 类型               | 描述                                                                                                                                                   |
|------------------|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| append           | boolean          | 默认为 true。如果为 true，记录将附加到文件末尾。设置为 false 时，将在写入新记录之前清除文件                                                                                               |
| bufferedIO       | boolean          | 默认为 true。如果为 true，数据先写入缓冲区，如果缓冲区满或者 immediateFlush 为 true 时，数据才被写入磁盘，如果为 false 直接写入磁盘。文件锁定不能与 bufferedIO 一起使用                                        |
| bufferSize       | int              | 缓冲区大小。bufferedIO 为 true 时，此参数有效，默认为 8192 bytes                                                                                                       |
| createOnDemand   | boolean          | 默认为 false。按需创建文件。仅当日志事件通过所有 Filter 并且路由到该 append 时，append 才创建文件                                                                                      |
| filter           | Filter           | 确定事件是否应由此 Appender 处理，通过 CompositeFilter（对应标签为）可以使用多个过滤器                                                                                             |
| fileName         | String           | 要写入的文件名，如果不存在或者父目录不存在，则创建对应的文件或目录                                                                                                                    |
| filePattern      | String           | 归档文件的模式，取决于所使用的 RolloverPolicy                                                                                                                       |
| immediateFlush   | boolean          | 默认为 true。如果为 true，每次写操作后都会将数据刷新入磁盘，可能会影响性能。 每次写入后刷新仅在使用同步 appender 时才有用。即使设置为 false，异步 appender 也将在一批事件结束后自动刷新，这也可以确保效率更高的将数据写入磁盘。                   |
| layout           | Layout           | 格式化日志输出格式。如果未设置，则默认为’%m%n’                                                                                                                           |
| name             | String           | append 名称                                                                                                                                            |
| policy           | TriggeringPolicy | 用于确定归档的触发条件                                                                                                                                          |
| strategy         | RolloverStrategy | 用于确定归档的文件名称、路径及归档方式                                                                                                                                  |
| ignoreExceptions | boolean          | 默认为 true。设置为 true 时，如果记录日志发生异常，此条日志和异常将被忽略。设置为 false 时，异常将被抛出到调用方。如果此 append 用在 FailoverAppender 中，则必须设置为 false。                                     |
| filePermissions  | String           | 创建文件时指定文件的 rwx 权限，前提是文件系统应支持 POSIX 文件属性视图                                                                                                            |
| fileOwner        | String           | 文件所有者。出于安全原因，更改文件的所有者可能受到限制，并且不允许操作时会抛出 IOException。 如果\_POSIX_CHOWN_RESTRICTED 对路径有效，则只有有效用户 ID 等于文件用户 ID 或具有适当特权的进程才可以更改文件的所有权，前提是文件系统应支持文件所有者属性视图 |
| fileGroup        | String           | 文件组。，前提是文件系统应支持 POSIX 文件属性视图                                                                                                                         |

### TriggeringPolicy

TriggeringPolicy 是控制日志文件归档的触发条件。总共有四种类型的 TriggeringPolicy，可以组合（CompositeTriggeringPolicy）多种触发策略来控制归档，标签为，如果配置了多种策略，则只要有一种策略返回
true，就返回 true。

`<Policies>` `<!-- <CronTriggeringPolicy schedule="0 0 * * * ?"/> -->` `<OnStartupTriggeringPolicy minSize="2" />` `<SizeBasedTriggeringPolicy size="20 MB" />` `<TimeBasedTriggeringPolicy /></Policies>`

四种类型如下：

- OnStartupTriggeringPolicy
  如果日志文件的时间比 JVM 的启动时间早，或者达到 minSize 的值，则会触发归档。
  minSize：触发文件归档的最小值，默认为 1。
- SizeBasedTriggeringPolicy
  当文件达到指定大小后，触发归档。大小可以通过 size 指定，单位为 KB、MB、GB。与 TimeBasedTriggeringPolicy 配合使用时，filePattern
  中必须包含%i，否则文件每次归档时都会覆盖当前文件，因为 TimeBasedTriggeringPolicy 不会让文件名中的时间戳改变。如果不使用 TimeBasedTriggeringPolicy，则
  SizeBasedTriggeringPolicy 会让时间戳改变。
- TimeBasedTriggeringPolicy
  当前时间与当前日志文件时间不匹配时，TimeBasedTriggeringPolicy 会触发归档。参数如下：

| 参数             | 描述                                                                                                                                            |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| interval       | 基于 filePattern 中配置的最小时间单位进行来控制归档频率，默认值为 1。如：filePattern 中最小时间单位为小时，如果 interval=1，则 1 小时归档一次；如果 interval=2，则 2 小时归档一次。                         |
| modulate       | 默认为 false。指明是否对 interval 进行调节，若 modulate 为 true，会以 0 为开始对 interval 进行偏移计算。例如，最小时间粒度为小时，当前为 3:00，interval 为 4，则后面归档时间依次为 4:00，8:00，12:00，16:00 |
| maxRandomDelay | 指示随机延迟过渡的最大秒数。默认情况下，该值为 0，表示没有延迟。此设置在配置了多个应用程序以同时滚动日志文件的服务器上很有用，并且可以在整个时间上分散这样做的负担。                                                           |

- CronTriggeringPolicy
  基于 cron 表达式触发归档。此策略由计时器控制，并且与处理日志事件异步，因此上一个或下一个时间段的日志事件可能会出现在当前日志文件的开头或结尾。filePattern
  属性应包含一个时间戳，否则目标文件将在每次归档时被覆盖。参数如下：

| 参数                  | 描述                                                                   |
|---------------------|----------------------------------------------------------------------|
| schedule            | cron 表达式，该表达式与 Quartz 调度程序中允许的表达式相同。详见 CronExpression                |
| EvaluationOnStartup | 启动时，将根据文件的最后修改时间戳评估 cron 表达式。如果 cron 表达式指示应该在该时间和当前时间之间归档，则文件将立即被归档。 |

### RolloverPolicy

用来控制文件归档方式，目前有两种类型：DefaultRolloverStrategy 和 DirectWriteRolloverStrategy

#### DefaultRolloverStrategy

通过接收 filePattern 属性中日期/时间模式（%d）和整数（%i）来控制归档方式。如果存在日期/时间模式，则将在归档时使用当前时间替换 filePattern
中配置的日期/时间部分，如果模式包含整数，则它将在每次归档时递增。如果归档时在模式中同时包含日期/时间和整数，则整数将递增，直到日期/时间部分也将被替换。如果文件模式以“
.gz”，“.zip”，“.bz2”，“.deflate”，“.pack200”或“ .xz”结尾，则将使用与后缀匹配的压缩方案来压缩文件。 bzip2, Deflate, Pack200 and XZ 格式要求有 Apache Commons
Compress 组件，另外 xz 格式还要求有 XZ for Java 组件。

DefaultRolloverStrategy 参数如下：

| 参数                        | 描述                                                     |
|---------------------------|--------------------------------------------------------|
| fileIndex                 | 默认值为 max。可选值为：min、max，2.8 之后新增 nomax。文件归档及新文件创建规则后面介绍。 |
| min                       | 计数器的最小值。预设值为 1。                                        |
| max                       | 计数器的最大值。一旦达到此值，较旧的归档文件将在以后的转换中被删除。预设值为 7。              |
| compressionLevel          | 将压缩级别设置为 0-9，其中 0 =无，1 =最佳速度，直到 9 =最佳压缩。仅针对 ZIP 文件实现   |
| tempCompressedFilePattern | 压缩期间归档日志文件的文件名的模式。                                     |

fileIndex 设值不同，则文件归档及新文件创建及计数器递增方法都不同，计数器递增有三种方式，如下：

- 方式一：fileIndex 值为 max
  假设将 DefaultRolloverStrategy 的 min 属性设置为 1，将 max 属性设置为 3，fileName 是“ foo.log”，filePattern 是“ foo-％i.log”,归档规则如下：

| 归档数 | 当前日志文件  | 归档文件                          | 描述                                                                                                                        |
|-----|---------|-------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| 0   | foo.log | -                             | 所有日志记录都将转到初始文件                                                                                                            |
| 1   | foo.log | foo-1.log                     | 第一次归档，foo.log 重命名为 foo-1.log。创建新的 foo.log 文件并继续写入                                                                         |
| 2   | foo.log | foo-2.log，foo-1.log           | 第二次归档，foo.log 重命名为 foo-2.log。创建新的 foo.log 文件并继续写入                                                                         |
| 3   | foo.log | foo-3.log，foo-2.log，foo-1.log | 第三次归档，foo.log 重命名为 foo-3.log。创建新的 foo.log 文件并继续写入                                                                         |
| 4   | foo.log | foo-3.log，foo-2.log，foo-1.log | 第四次和随后的归档，foo-1.log 被删除，foo-2.log 被重命名为 foo-1.log，foo-3.log 被重命名为 foo-2.log，foo.log 被重命名为 foo-3.log。创建新的 foo.log 文件并继续写入。 |

- 方式二：fileIndex 值为 min

假设将 DefaultRolloverStrategy 的 min 属性设置为 1，将 max 属性设置为 3，fileName 是“ foo.log”，filePattern 是“ foo-％i.log”,归档规则如下：

| 归档数 | 当前日志文件  | 归档文件                          | 描述                                                                                                                           |
|-----|---------|-------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| 0   | foo.log | -                             | 所有日志记录都将转到初始文件                                                                                                               |
| 1   | foo.log | foo-1.log                     | 第一次归档，foo.log 重命名为 foo-1.log。创建新的 foo.log 文件并继续写入                                                                            |
| 2   | foo.log | foo-1.log，foo-2.log           | 第二次归档，将 foo-1.log 重命名为 foo-2.log，并将 foo.log 重命名为 foo-1.log。创建新的 foo.log 文件并继续写入                                              |
| 3   | foo.log | foo-1.log，foo-2.log，foo-3.log | 第三次归档，将 foo-2.log 重命名为 foo-3.log，将 foo-1.log 重命名为 foo-2.log，将 foo.log 重命名为 foo-1.log。创建新的 foo.log 文件并继续写入                    |
| 4   | foo.log | foo-1.log，foo-2.log，foo-3.log | 第四次和随后的归档，删除 foo-3.log，将 foo-2.log 重命名为 foo-3.log，将 foo-1.log 重命名为 foo-2.log，将 foo.log 重命名为 foo -1.log。创建新的 foo.log 文件并继续写入。 |

- 方式三：fileIndex 值为 nomax
  nomax 为 2.8 新增属性，设置为 nomax 时，将忽略 DefaultRolloverStrategy 的最大值和最小值，每次归档生成的新文件相对于前一个文件编号加 1，没有最大文件数限制。

#### DirectWriteRolloverStrategy

将日志事件直接写入由 filePattern 表示的文件。使用此策略文件不会执行重命名。如果是基于大小的触发策略，将在指定的时间段内写入多个文件，它们从 1
开始编号，并不断递增直到发生基于时间归档。

注意：如果 filePattern 的后缀表示应该进行压缩，则在关闭应用程序时不会压缩当前文件。此外，如果时间更改使得 filePattern 不再与当前文件匹配，则启动时也不会对其进行压缩。

DirectWriteRolloverStrategy 模式参数如下：

| 参数                        | 描述                                                                             |
|---------------------------|--------------------------------------------------------------------------------|
| maxFiles                  | 与文件格式匹配的时间段内允许的最大文件数。如果超出文件数量，则最早的文件将被删除。如果指定，则该值必须大于 1。如果该值小于零或省略，则文件数量将不受限制。 |
| compressionLevel          | 将压缩级别设置为 0-9，其中 0 =无，1 =最佳速度，直到 9 =最佳压缩。仅针对 ZIP 文件实现。                          |
| tempCompressedFilePattern | 压缩期间归档日志文件的文件名的模式。                                                             |

#### 归档保留策略

DefaultRolloverStrategy 模式下，Log4j-2.5 引入了 Delete（删除）操作（标签为），与 max 属性所提供的功能相比，Log4j-2.5
使用户可以更好地控制在归档时删除哪些文件。删除操作使用户可以配置一个或多个条件，以选择要相对于基本目录删除的文件。

注意：可以删除任何文件，而不仅仅是删除日志文件，因此请谨慎使用此操作！使用 testMode 参数，可以测试配置，而不会意外删除错误的文件。

delete 参数如下：

| 参数              | 描述                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| basePath        | 必需。从此处开始扫描要删除的文件的基本路径。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| maxDepth        | 要访问的目录的最大级别数。值为 0 表示仅访问起始文件，除非安全管理器拒绝。Integer.MAX_VALUE 的值指示应访问所有级别。默认值为 1，表示仅指定基本路径中的文件。                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| followLinks     | 是否遵循符号链接。默认为 false。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| testMode        | 如果为 true，则不会删除文件，而是在 INFO 级别打印一条消息到状态记录器。使用此功能可以测试配置是否按预期工作。默认为 false。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| pathSorter      | 一个实现 PathSorter 接口的插件， 用于在选择要删除的文件之前对文件进行排序。默认设置是首先对最近修改的文件进行排序。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| pathConditions  | 如果未指定 ScriptCondition，则为必需。可以指定一个或多个 PathCondition 元素。如果指定了多个 PathCondition 元素，则**需要所有的 PathCondition 结果都为 true**才会进行删除。PathCondition 也可以嵌套。如果进行嵌套，则是先判断外层的 PathCondition，然后进行内层的判断。如果没有嵌套，则是按顺序进行判断。也可以创建自定义条件或使用内置条件：IfFileName 如果文件名与此参数匹配则结果为 true，此参数为正则表达式或 glob 的文件。IfLastModified 最后修改时间早于或等于此参数则结果为 true，此参数为 duration。IfAccumulatedFileCount 文件数超过指定个数则结果为 true，此参数为整型。IfAccumulatedFileSize 所有文件总大小达到此参数则结果为 true，此参数为 KB、MB、GB。IfAll 如果此标签下的所有条件都配置成功（逻辑与），则结果为 true。IfAny 如果此标签下的任何一个条件匹配成功（逻辑或），则结果为 true。IfNot 如果此标签下的所有条件都不匹配（逻辑非），则结果为 true。 |
| scriptCondition | 如果未指定 PathConditions，则为必需。指定脚本的 ScriptCondition 元素。ScriptCondition 应该包含一个 Script，ScriptRef 或 ScriptFile 元素，该元素指定要执行的逻辑。（有关配置 ScriptFiles 和 ScriptRefs 的更多示例，另请参阅 ScriptFilter 文档。）该脚本传递了许多参数，包括在 basePath 下找到的路径列表（最大 maxDepth），并且必须返回包含要删除的路径的列表。                                                                                                                                                                                                                                                                                                            |

## 使用示例

引入类库

```xml
<!--        log4j-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>${log4j.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>${log4j.version}</version>
</dependency>
```

示例代码

```java
public class Log4jTest {
    static final Logger logger = LogManager.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        // OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
    }
}
```
