## 配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!--
scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="true">
    <property name="logPath" value="logs"/>
    <property name="logPattern" value="%5p[%d{HH:mm:ss.SSS}] %.30logger{10} %msg%n"/>
    <!--    日志记录-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%5p[%d{HH:mm:ss.SSS}]→%.50logger{50}:%n - %m%n</pattern>
        </encoder>
    </appender>

    <appender name="infoLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${logPath}/infoLog.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${logPath}/infoLog.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${logPattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <appender name="errorLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${logPath}/errorLog.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${logPath}/errorLog.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${logPattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <appender name="userLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${logPath}/userLog.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${logPath}/userLog.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${logPattern}</pattern>
        </encoder>
    </appender>

    <!-- 日志级别控制  -->
    <logger name="org.springframework" level="warn"/>
    <logger name="sijay.zheng" level="trace"/>

    <root level="info">
        <appender-ref ref="console"/>
    </root>
    <root level="info">
        <appender-ref ref="infoLog"/>
        <appender-ref ref="errorLog"/>
    </root>
    <logger name="userLog" level="info">
        <appender-ref ref="userLog"/>
    </logger>
</configuration>

```

## 使用示例

引入类库

```xml
<!--        logback-->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>${logback.version}</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>${logback.version}</version>
</dependency>
```

示例代码

```java
public class LogbackTest {
    static Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        // OFF >  ERROR > WARN > INFO > DEBUG > TRACE > ALL
    }
}
```
