/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app.h2;

import lombok.Data;
import sijay.zheng.z.app.common.enums.GenderEnum;

import java.beans.Transient;
import java.time.LocalDate;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/7/6 11:07
 */
@Data
@Entity
@Table(name = "STUDENT")
public class Demo {
    //主键
    @Id
    //生成方式
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "STUDENT_NAME", length = 50, nullable = false)
    private String name;
    //非持久化字段
    @Transient
    private Integer age;
    //时间类型数据
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    // 枚举类型
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
}
