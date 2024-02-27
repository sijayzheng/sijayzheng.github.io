package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.base.BaseDataEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>DictType 数据来源</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/11 16:34
 */
@Getter
@AllArgsConstructor
public enum DataSource implements BaseDataEnum {
    DICT("D", "字典"),
    ENUM("E", "枚举"),
    SELF("S", "自身"),
    TABLE("T", "表数据"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

}
