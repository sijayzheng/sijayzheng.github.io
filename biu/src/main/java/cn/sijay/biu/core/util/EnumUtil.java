package cn.sijay.biu.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * EnumUtil
 *
 * @author Sijay
 * @since 2025-03-07
 */
public class EnumUtil {
    public static List<Object> getFieldValues(Class<? extends Enum<?>> clazz, String fieldName) {
        List<Object> list = new ArrayList<>();
        try {
            Enum<?>[] enums = clazz.getEnumConstants();
            for (Enum<?> e : enums) {
                list.add(e.getClass().getField(fieldName).get(e));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
