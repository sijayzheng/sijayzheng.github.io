package cn.sijay.suap.core.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * <p>
 * <em>NumberUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/10 11:13
 */
@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class NumberUtil extends NumberUtils {
    public static int firstGreatThanZero(int... nums) {
        for (int num : nums) {
            if (num > 0) {
                return num;
            }
        }
        return 0;
    }
}
