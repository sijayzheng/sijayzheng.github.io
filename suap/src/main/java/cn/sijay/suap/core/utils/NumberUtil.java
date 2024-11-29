package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.enums.HexEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <strong>NumberUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil extends NumberUtils {
    private static final List<String> NUMBER = List.of("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");
    private static final List<String> UNIT_LOW = List.of("", "十", "百", "千");
    private static final List<String> UNIT_HIGH = List.of("", "万", "亿", "兆", "京");

    /**
     * @param number num
     * @return ChineseNum
     */
    public static String numberToChinese(String number) {
        List<Integer> list = Stream.of(number.split("")).map(Integer::parseInt).collect(Collectors.toList());
        Collections.reverse(list);
        List<String> result = toString(list);
        for (int i = 0; i < result.size(); i++) {
            if (UNIT_HIGH.contains(result.get(i)) && "零".equals(result.get(i + 1))) {
                result.set(i, "");
            }
        }
        Collections.reverse(result);
        return String.join("", result).replaceAll("零+", "零");
    }

    private static List<String> toString(List<Integer> list) {
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
        return result;
    }

    public static String numToHex(Integer num) {
        if (num == 0) {
            return "0";
        }
        LinkedList<Character> list = new LinkedList<>();
        while (num > 1) {
            list.add(HexEnum.getBase(num % 16));
            num = num / 16;
        }
        if (num > 0) {
            list.add(HexEnum.getBase(num));
        }
        Collections.reverse(list);
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static double hexToNum(String hex) {
        return toNum(hex, 16);
    }

    public static double toNum(String val, int base) {
        assert base > 0 && base <= 16;
        int j = 0;
        double n = 0D;
        for (int i = val.length() - 1; i >= 0; i--) {
            double pow = Math.pow(base, j);
            n += HexEnum.getVal(val.charAt(i)) * pow;
            j++;
        }
        return n;
    }

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

    public static double binToNum(String bin) {
        return toNum(bin, 2);
    }

    public static int firstGreatThanZero(int... nums) {
        for (int num : nums) {
            if (num > 0) {
                return num;
            }
        }
        return 0;
    }

}
