package cn.sijay.suap.core.util;

import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.enums.GenFileType;
import cn.sijay.suap.core.exception.BaseException;
import cn.sijay.suap.gen.entity.GenTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * <em>VelocityUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/11 17:36
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityUtil {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new BaseException("Velocity引擎初始化失败");
        }
    }

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("packageName", genTable.getPackageName());
        velocityContext.put("module", genTable.getModule());
        velocityContext.put("className", genTable.getClassName());
        velocityContext.put("lowerName", genTable.getLowerName());
        velocityContext.put("comment", genTable.getComment());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("author", genTable.getAuthor());
        velocityContext.put("createTime", "yyyy/MM/dd HH:mm");//DateTimeUtil.now("yyyy/MM/dd HH:mm")
        velocityContext.put("imports", genTable.getImports());
        velocityContext.put("fields", genTable.getFields());
        String superClass = genTable.getSuperClass();
        velocityContext.put("superClass", superClass);
        velocityContext.put("superClassName", superClass.substring(superClass.lastIndexOf(".") + 1));
//        setMenuVelocityContext(velocityContext, genTable);
//        if (GenConstants.TPL_TREE.equals(tplCategory)) {
//            setTreeVelocityContext(velocityContext, genTable);
//        }
        return velocityContext;
    }

    public static String getGenPath(GenTable genTable, String template, GenFileType genFileType, String path) {
        return switch (genFileType) {
            case JAVA -> FileUtil.concatPath(path, genFileType.getGenPath(), genTable.getPackageName().replace(".", File.separator),
                    genTable.getModule(), template.replace("_", File.separator), getFileName(genTable, template, genFileType));
            case XML ->
                    FileUtil.concatPath(path, genFileType.getGenPath(), template, genTable.getModule(), getFileName(genTable, template, genFileType));
            case VUE, JS -> FileUtil.concatPath(path, genFileType.getGenPath(), genTable.getModule(), getFileName(genTable, template, genFileType));
            case SQL -> "";
        };
    }

    public static String getFileName(GenTable genTable, String template, GenFileType genFileType) {
        if (Objects.requireNonNull(genFileType) == GenFileType.JAVA) {
            if ("entity".equals(template)) {
                return genTable.getClassName() + Constants.DOT + genFileType.getType();
            } else if ("service".equals(template)) {
                return "I" + genTable.getClassName() + StringUtil.toUpperCamelCase(template) + Constants.DOT + genFileType.getType();
            } else {
                return genTable.getClassName() + StringUtil.toUpperCamelCase(template) + Constants.DOT + genFileType.getType();
            }
        } else if (genFileType == GenFileType.XML) {
            return genTable.getClassName() + "Mapper" + Constants.DOT + genFileType.getType();
        } else if (genFileType == GenFileType.VUE || genFileType == GenFileType.JS) {
            return genTable.getLowerName() + Constants.DOT + genFileType.getType();
        }
        throw new IllegalArgumentException();
    }

    public static String render(String template, VelocityContext context) {
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate("/templates/vm/" + template, Constants.UTF8);
        tpl.merge(context, sw);
        return sw.toString();
    }
}
