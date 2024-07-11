package cn.sijay.utils;

/**
 * <strong>PrintUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-11
 */
public class Trunk {
    Trunk prev;
    String str;

    Trunk(Trunk prev, String str) {
        this.prev = prev;
        this.str = str;
    }
}
