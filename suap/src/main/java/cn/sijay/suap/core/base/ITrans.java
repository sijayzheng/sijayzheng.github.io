package cn.sijay.suap.core.base;

import java.util.HashMap;
import java.util.Map;

/**
 * <strong>ITrans</strong>
 * <p>
 * 继承该接口以进行数据翻译
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface ITrans {

    Map<String, String> TRANS_MAP = new HashMap<>();

    default Map<String, String> getTransMap() {
        return TRANS_MAP;
    }
}
