package cn.sijay.biu.core.helper;

import cn.sijay.biu.core.annotation.Xss;
import cn.sijay.biu.core.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * 自定义xss校验注解实现
 *
 * @author Sijay
 */
public class XssValidator implements ConstraintValidator<Xss, String> {
    private static final Pattern PATTERN = Pattern.compile("(<[^<]*?>)|(<\\s*?/[^<]*?>)|(<[^<]*?/\\s*?>)");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtil.isNotBlank(value) && PATTERN.matcher(value).find();
    }

}
