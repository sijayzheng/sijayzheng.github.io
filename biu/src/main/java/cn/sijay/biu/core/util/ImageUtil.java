package cn.sijay.biu.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Random;

/**
 * ImageUtil
 *
 * @author Sijay
 * @since 2025-02-17
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtil {

    public static Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256) - 1;
        int g = random.nextInt(256) - 1;
        int b = random.nextInt(256) - 1;
        return new Color(r, g, b);
    }
}
