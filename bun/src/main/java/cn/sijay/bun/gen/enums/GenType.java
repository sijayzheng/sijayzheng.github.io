package cn.sijay.bun.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>genType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-11-08
 */
@Getter
@AllArgsConstructor
public enum GenType {
    ZIP("压缩包下载"),
    DIRECTORY("指定目录"),
    ;
    private final String value;
}
