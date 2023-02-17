package sijay.zheng.experience.common.util;

import org.springframework.stereotype.*;
import sijay.zheng.experience.common.annotation.*;
import sijay.zheng.experience.common.enums.*;

import javax.annotation.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author sijay
 * @date 2022/4/26 21:25
 */
@Component
@Resource
public class NumberUtils {
    private static final List<String> NUMBER = List.of("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");
    private static final List<String> UNIT_LOW = List.of("", "十", "百", "千");
    private static final List<String> UNIT_HIGH = List.of("", "万", "亿", "兆", "京");

    /**
     * @param number num
     * @return ChineseNum
     */
    @LogDog
    public static String numberToChinese(String number) {
        List<Integer> list = Stream.of(number.split("")).map(Integer::parseInt).collect(Collectors.toList());
        Collections.reverse(list);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            String res = "";
            if (0 != integer) {
                res = NUMBER.get(integer) + UNIT_LOW.get(i % 4);
            } else {
                if (i % 4 != 0) {
                    res = NUMBER.get(integer);
                }
            }
            if (i % 4 == 0) {
                res += UNIT_HIGH.get(i / 4);
            }
            result.add(res);
        }
        for (int i = 0; i < result.size(); i++) {
            if (UNIT_HIGH.contains(result.get(i)) && NumberEnum.ZERO.getZhValue().equals(result.get(i + 1))) {
                result.set(i, "");
            }
        }
        Collections.reverse(result);
        return String.join("", result).replaceAll("零+", NumberEnum.ZERO.getZhValue());
    }

    @LogDog
    public static String numberToChinese(int number) {
        return numberToChinese(String.valueOf(number));
    }

    @LogDog
    public static String numberToChinese(long number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(float number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(double number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(Integer number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(Long number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(Float number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(Double number) {
        return numberToChinese(new BigDecimal(number));
    }

    @LogDog
    public static String numberToChinese(BigDecimal number) {
        return numberToChinese(number.toString());
    }

}
