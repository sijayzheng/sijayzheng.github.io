package cn.sijay.suap.core.enums;

import cn.sijay.suap.core.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <em>GenFileType 生成文件路径</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 15:56
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
