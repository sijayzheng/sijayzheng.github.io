package sijay.zheng.experience.mybatis.pojo;

import lombok.*;

/**
 * @author Sijay
 * @date 2022/2/8 15:32
 */
@Data
@ToString
public class MStudent {
    private Integer id;
    private String name;
    private MTeacher mTeacher;
}
