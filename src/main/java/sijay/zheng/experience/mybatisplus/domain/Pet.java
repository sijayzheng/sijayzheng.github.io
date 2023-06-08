package sijay.zheng.experience.mybatisplus.domain;

import lombok.*;

import java.time.*;

/**
 * @author Sijay
 */
@Data
@ToString
public class Pet {
    private Integer id;
    private String name;
    private String type;
    private Integer age;
    private LocalDateTime birthday;
}