package cn.sijay.biu.log.entity;

import cn.sijay.biu.core.base.IdEntity;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * LogOperate
 * 操作日志实体类
 *
 * @author Sijay
 * @since 2025-04-14
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("操作日志")
@Entity
@Table(name = "log_operate")
public class LogOperate extends IdEntity {

    /**
     * 用户id
     */
    @ExcelProperty("用户id")
    @NotNull
    @Comment("用户id")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    /**
     * 模块标题
     */
    @ExcelProperty("模块标题")
    @Size(max = 50, message = "模块标题最大长度为{max}")
    @Comment("模块标题")
    @Column(name = "title", unique = true, length = 50)
    private String title;

    /**
     * 方法名称
     */
    @ExcelProperty("方法名称")
    @Size(max = 100, message = "方法名称最大长度为{max}")
    @Comment("方法名称")
    @Column(name = "method", unique = true, length = 100)
    private String method;

    /**
     * 请求方式
     */
    @ExcelProperty("请求方式")
    @Size(max = 10, message = "请求方式最大长度为{max}")
    @Comment("请求方式")
    @Column(name = "request_method", unique = true, length = 10)
    private String requestMethod;

    /**
     * 请求URL
     */
    @ExcelProperty("请求URL")
    @Size(max = 255, message = "请求URL最大长度为{max}")
    @Comment("请求URL")
    @Column(name = "url", unique = true, length = 255)
    private String url;

    /**
     * 主机地址
     */
    @ExcelProperty("主机地址")
    @Size(max = 128, message = "主机地址最大长度为{max}")
    @Comment("主机地址")
    @Column(name = "ip", unique = true, length = 128)
    private String ip;

    /**
     * 操作地点
     */
    @ExcelProperty("操作地点")
    @Size(max = 255, message = "操作地点最大长度为{max}")
    @Comment("操作地点")
    @Column(name = "location", unique = true, length = 255)
    private String location;

    /**
     * 请求参数
     */
    @ExcelProperty("请求参数")
    @Size(max = 2000, message = "请求参数最大长度为{max}")
    @Comment("请求参数")
    @Column(name = "param", unique = true, length = 2000)
    private String param;

    /**
     * 返回参数
     */
    @ExcelProperty("返回参数")
    @Size(max = 2000, message = "返回参数最大长度为{max}")
    @Comment("返回参数")
    @Column(name = "result", unique = true, length = 2000)
    private String result;

    /**
     * 操作状态
     */
    @ExcelProperty("操作状态")
    @Comment("操作状态")
    @Column(name = "status", unique = true)
    private Integer status;

    /**
     * 错误消息
     */
    @ExcelProperty("错误消息")
    @Size(max = 2000, message = "错误消息最大长度为{max}")
    @Comment("错误消息")
    @Column(name = "error_msg", unique = true, length = 2000)
    private String errorMsg;

    /**
     * 操作时间
     */
    @ExcelProperty("操作时间")
    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Comment("操作时间")
    @Column(name = "time", unique = true)
    private LocalDateTime time;

    /**
     * 消耗时间
     */
    @ExcelProperty("消耗时间")
    @ColumnDefault("'0'")
    @Comment("消耗时间")
    @Column(name = "cost_time", unique = true)
    private Long costTime;

    /**
     * 操作时间开始
     */
    @Transient
    private transient LocalDateTime timeStart;

    /**
     * 操作时间结束
     */
    @Transient
    private transient LocalDateTime timeEnd;

}
