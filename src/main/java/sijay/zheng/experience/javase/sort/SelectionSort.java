package sijay.zheng.experience.javase.sort;

import java.util.*;

/**
 * @author Sijay
 * 选择排序
 */
public class SelectionSort {

    public int[] sort(int[] arr) {
        int[] result = Arrays.copyOf(arr, arr.length);
        int temp, index;
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] > result[j]) {
                    temp = result[j];
                    index = j;
                }
            }
        }
        return arr;
    }
}