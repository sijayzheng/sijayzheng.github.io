/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import sijay.zheng.z.common.annotation.LogDog;
import sijay.zheng.z.common.enums.NumberBaseEnum;
import sijay.zheng.z.common.enums.NumberEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sijay
 * @date 2022/4/26 21:25
 */
@Component
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class NumberUtil {
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

    @LogDog
    public static String numToHex(Integer num) {
        if (num == 0) {
            return "0";
        }
        LinkedList<Character> list = new LinkedList<>();
        while (num > 1) {
            list.add(NumberBaseEnum.getBase(num % 16));
            num = num / 16;
        }
        if (num > 0) {
            list.add(NumberBaseEnum.getBase(num));
        }
        Collections.reverse(list);
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    @LogDog
    public static double hexToNum(String hex) {
        return toNum(hex, 16);
    }

    @LogDog
    public static String numToBin(Integer num) {
        if (num == 0) {
            return "0";
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (num > 1) {
            list.add(num % 2);
            num = num / 2;
        }
        if (num > 0) {
            list.add(num);
        }
        Collections.reverse(list);
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    @LogDog
    public static double binToNum(String bin) {
        return toNum(bin, 2);
    }

    @LogDog
    public static double toNum(String val, int base) {
        assert base > 0 && base <= 16;
        int j = 0;
        double n = 0D;
        for (int i = val.length() - 1; i >= 0; i--) {
            double pow = Math.pow(base, j);
            n += NumberBaseEnum.getVal(val.charAt(i)) * pow;
            j++;
        }
        return n;
    }
}
