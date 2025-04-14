package cn.sijay.bun.test.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <strong>TestTable</strong>
 * <p>
 * 测试数据
 * </p>
 *
 * @author Sijay
 * @since 2024-11-11
 */
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Comment("测试数据")
@Entity
@Table(name = "test_table")
public class TestTable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 输入框
     */
    @ExcelProperty("输入框")
    @Size(max = 5)
    @Column(name = "c_input", length = 5)
    private String cInput;

    /**
     * 数字输入框
     */
    @ExcelProperty("数字输入框")
    @Column(name = "c_number_input")
    private Integer cNumberInput;

    /**
     * 单选
     */
    @ExcelProperty("单选")
    @Column(name = "c_radio")
    private Boolean cRadio;

    /**
     * 开关
     */
    @ExcelProperty("开关")
    @NotNull
    @Column(name = "c_switch", nullable = false)
    private Boolean cSwitch;

    /**
     * 选择器
     */
    @ExcelProperty("选择器")
    @NotNull
    @Column(name = "c_select", nullable = false)
    private Integer cSelect;

    /**
     * 树形选择器
     */
    @ExcelProperty("树形选择器")
    @Column(name = "c_tree_select")
    private Integer cTreeSelect;

    /**
     * 日期时间选择
     */
    @ExcelProperty("日期时间选择")
    @Column(name = "c_datetime_pick")
    private LocalDateTime cDatetimePick;

    /**
     * 日期选择
     */
    @ExcelProperty("日期选择")
    @Column(name = "c_date_pick")
    private LocalDate cDatePick;

    /**
     * 时间选择
     */
    @ExcelProperty("时间选择")
    @NotNull
    @Column(name = "c_time_pick", nullable = false)
    private LocalTime cTimePick;

    /**
     * 介于日期时间选择
     */
    @ExcelProperty("介于日期时间选择")
    @Column(name = "c_between_datetime_pick")
    private LocalDateTime cBetweenDatetimePick;

    /**
     * 介于日期选择
     */
    @ExcelProperty("介于日期选择")
    @Column(name = "c_between_date_pick")
    private LocalDate cBetweenDatePick;

    /**
     * 介于时间选择
     */
    @ExcelProperty("介于时间选择")
    @NotNull
    @Column(name = "c_between_time_pick", nullable = false)
    private LocalTime cBetweenTimePick;

    /**
     * 介于日期时间选择开始
     */
    private LocalDateTime cBetweenDatetimePickStrat;

    /**
     * 介于日期时间选择结束
     */
    private LocalDateTime cBetweenDatetimePickEnd;

    /**
     * 介于日期选择开始
     */
    private LocalDate cBetweenDatePickStrat;

    /**
     * 介于日期选择结束
     */
    private LocalDate cBetweenDatePickEnd;

    /**
     * 介于时间选择开始
     */
    private LocalTime cBetweenTimePickStrat;

    /**
     * 介于时间选择结束
     */
    private LocalTime cBetweenTimePickEnd;

}
