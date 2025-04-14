package cn.sijay.biu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * genEnum
 *
 * @author Sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum GenEnum {
    ZIP("压缩包下载"),
    DIRECTORY("指定目录"),
    ;
    private final String value;
}
