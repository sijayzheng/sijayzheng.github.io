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
 * LogLogin
 * 登录日志实体类
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
@Comment("登录日志")
@Entity
@Table(name = "log_login")
public class LogLogin extends IdEntity {

    /**
     * 用户id
     */
    @ExcelProperty("用户id")
    @NotNull
    @Comment("用户id")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    /**
     * 登录ip
     */
    @ExcelProperty("登录ip")
    @Size(max = 128, message = "登录ip最大长度为{max}")
    @Comment("登录ip")
    @Column(name = "ip", unique = true, length = 128)
    private String ip;

    /**
     * 登录地点
     */
    @ExcelProperty("登录地点")
    @Size(max = 255, message = "登录地点最大长度为{max}")
    @Comment("登录地点")
    @Column(name = "location", unique = true, length = 255)
    private String location;

    /**
     * 浏览器类型
     */
    @ExcelProperty("浏览器类型")
    @Size(max = 100, message = "浏览器类型最大长度为{max}")
    @Comment("浏览器类型")
    @Column(name = "browser", unique = true, length = 100)
    private String browser;

    /**
     * 操作系统
     */
    @ExcelProperty("操作系统")
    @Size(max = 50, message = "操作系统最大长度为{max}")
    @Comment("操作系统")
    @Column(name = "os", unique = true, length = 50)
    private String os;

    /**
     * 登录状态
     */
    @ExcelProperty("登录状态")
    @Comment("登录状态")
    @Column(name = "successful", unique = true)
    private Boolean successful;

    /**
     * 提示消息
     */
    @ExcelProperty("提示消息")
    @Size(max = 255, message = "提示消息最大长度为{max}")
    @Comment("提示消息")
    @Column(name = "msg", unique = true, length = 255)
    private String msg;

    /**
     * 时间
     */
    @ExcelProperty("时间")
    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Comment("时间")
    @Column(name = "time", unique = true)
    private LocalDateTime time;

    /**
     * 时间开始
     */
    @Transient
    private transient LocalDateTime timeStart;

    /**
     * 时间结束
     */
    @Transient
    private transient LocalDateTime timeEnd;

}
