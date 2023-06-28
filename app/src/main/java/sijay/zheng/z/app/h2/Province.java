package sijay.zheng.z.app.h2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author sijay
 * @date 2023/6/28 23:12
 */
@Data
@Entity
public class Province {
    @Id
    private Integer id;
    //行政区划代码
    private String code;
    //名称
    private String name;
    //省/直辖市
    private String province;
    //市
    private String city;
    //区
    private String area;
    //城镇地区
    private String town;
}
