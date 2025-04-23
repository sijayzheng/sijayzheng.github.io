package cn.sijay.demos.algo.utils;

/**
 * <strong>PrintUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
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
