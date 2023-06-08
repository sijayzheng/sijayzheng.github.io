package sijay.zheng.experience.mybatis.pojo;

import lombok.*;
import lombok.experimental.*;

/**
 * @author Sijay
 */
@Data
@ToString
@Accessors(chain = true)
public class MUserPO {
    private int id;
    private String name;
    private String pwd;
}