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
@Table(name = "province")
public class Province {
    @Id
    private Integer id;
    //行政区划代码
    @Column(name = "code")
    private String code;
    //名称
    @Column(name = "name")
    private String name;
    //省/直辖市
    @Column(name = "province")
    private String province;
    //市
    @Column(name = "city")
    private String city;
    //区
    @Column(name = "area")
    private String area;
    //城镇地区
    @Column(name = "town")
    private String town;
}
