package cn.sijay.suap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>NumberEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@AllArgsConstructor
@Getter
public enum NumberEnum {

    /**
     * ZERO
     */
    ZERO(0, 0, 0L, "0", "零", "零"),
    /**
     * ONE
     */
    ONE(1, 1, 1L, "1", "一", "壹"),
    /**
     * TWO
     */
    TWO(2, 2, 2L, "2", "二", "贰"),
    /**
     * THREE
     */
    THREE(3, 3, 3L, "3", "三", "叁"),
    /**
     * FOUR
     */
    FOUR(4, 4, 4L, "4", "四", "肆"),
    /**
     * FIVE
     */
    FIVE(5, 5, 5L, "5", "五", "伍"),
    /**
     * SIX
     */
    SIX(6, 6, 6L, "6", "六", "陆"),
    /**
     * SEVEN
     */
    SEVEN(7, 7, 7L, "7", "七", "柒"),
    /**
     * EIGHT
     */
    EIGHT(8, 8, 8L, "8", "八", "捌"),
    /**
     * NINE
     */
    NINE(9, 9, 9L, "9", "九", "玖"),
    /**
     * TEN
     */
    TEN(10, 10, 10L, "10", "十", "拾"),
    /**
     * ELEVEN
     */
    ELEVEN(11, 11, 11L, "11", "十一", "拾壹"),
    /**
     * TWELVE
     */
    TWELVE(12, 12, 12L, "12", "十二", "拾贰"),
    /**
     * THIRTEEN
     */
    THIRTEEN(13, 13, 13L, "13", "十三", "拾叁"),
    /**
     * FOURTEEN
     */
    FOURTEEN(14, 14, 14L, "14", "十四", "拾肆"),
    /**
     * FIFTEEN
     */
    FIFTEEN(15, 15, 15L, "15", "十五", "拾伍"),
    /**
     * SIXTEEN
     */
    SIXTEEN(16, 16, 16L, "16", "十六", "拾陆"),
    /**
     * SEVENTEEN
     */
    SEVENTEEN(17, 17, 17L, "17", "十七", "拾柒"),
    /**
     * EIGHTEEN
     */
    EIGHTEEN(18, 18, 18L, "18", "十八", "拾捌"),
    /**
     * NINETEEN
     */
    NINETEEN(19, 19, 19L, "19", "十九", "拾玖"),
    /**
     * TWENTY
     */
    TWENTY(20, 20, 20L, "20", "二十", "贰拾"),
    /**
     * THIRTY
     */
    THIRTY(30, 30, 30L, "30", "三十", "叁拾"),
    /**
     * FORTY
     */
    FORTY(40, 40, 40L, "40", "四十", "肆拾"),
    /**
     * FIFTY
     */
    FIFTY(50, 50, 50L, "50", "五十", "伍拾"),
    /**
     * SIXTY
     */
    SIXTY(60, 60, 60L, "60", "六十", "陆拾"),
    /**
     * SEVENTY
     */
    SEVENTY(70, 70, 70L, "70", "七十", "柒拾"),
    /**
     * EIGHTY
     */
    EIGHTY(80, 80, 80L, "80", "八十", "捌拾"),
    /**
     * NINETY
     */
    NINETY(90, 90, 90L, "90", "九十", "玖拾"),
    ;

    private final int intValue;
    private final Integer integerValue;
    private final Long longValue;
    private final String stringValue;
    private final String zhValue;
    private final String bigZhValue;
}
