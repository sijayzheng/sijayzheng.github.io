package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenFileType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Getter
@AllArgsConstructor
public enum GenFileType {
    JAVA("java", FileUtil.concatPath("src", "main", "java")),
    XML("xml", FileUtil.concatPath("src", "main", "resources")),
    VUE("vue", FileUtil.concatPath("ui", "src", "views")),
    JS("js", FileUtil.concatPath("ui", "src", "api")),
    SQL("sql", FileUtil.concatPath("sql")),
    ;
    private final String type;
    private final String genPath;
}
