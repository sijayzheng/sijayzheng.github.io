package sijay.zheng.experience.javase.sort;

import java.util.*;

/**
 * @author Sijay
 * 冒泡排序
 */
public class BubbleSort {

    public int[] sort(int[] arr) {
        int[] result = Arrays.copyOf(arr, arr.length);
        int temp;
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1; j++) {
                if (result[j] > result[j + 1]) {
                    temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

}