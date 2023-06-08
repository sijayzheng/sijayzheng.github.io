package sijay.zheng.experience.mybatis.pojo;

import lombok.*;

import java.util.*;

/**
 * @author Sijay
 * @date 2022/2/8 15:32
 */
@Data
@ToString
public class MTeacher {
    private Integer id;
    private String name;
    private List<MStudent> students;
}
