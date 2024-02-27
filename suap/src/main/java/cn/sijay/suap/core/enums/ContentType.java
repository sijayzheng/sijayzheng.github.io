package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>ContentType</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/31 15:03
 */
@Getter
@AllArgsConstructor
public enum ContentType {
    HTML("text/html"),
    TEXT("text/plain"),
    XML("text/xml"),
    GIF("image/gif"),
    JPG("image/jpeg"),
    PNG("image/png"),
    XHTML("application/xhtml+xml"),
    XML_DATA("application/xml"),
    JSON("application/json"),
    PDF("application/pdf"),
    WORD("application/msword"),
    STREAM("application/octet-stream"),
    FORM("application/x-www-form-urlencoded"),
    ;
    private final String type;
}
