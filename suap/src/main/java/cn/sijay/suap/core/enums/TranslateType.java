package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>TransType</strong>
 * <p>
 * 翻译类型
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@AllArgsConstructor
@Getter
public enum TranslateType {

    /**
     * 字典
     */
    DICTIONARY,

    /**
     * 数据库
     */
    DATABASE,
}
