package cn.sijay.demos.algo.sort;

import cn.sijay.demos.algo.utils.PrintUtil;

import java.util.*;

/**
 * <strong>SortUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-11
 */
public class SortUtil {
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    static void quickSort(int[] nums, int left, int right) {

        while (left < right) {

            int pivot = partition(nums, left, right);

            if (pivot - left < right - pivot) {
                quickSort(nums, left, pivot - 1);
                left = pivot + 1;
            } else {
                quickSort(nums, pivot + 1, right);
                right = pivot - 1;
            }
        }
    }

    static int partition(int[] nums, int left, int right) {

        int med = medianThree(nums, left, (left + right) / 2, right);

        swap(nums, left, med);

        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, left);
        return i;
    }

    static int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) {
            return mid;
        }
        if ((m <= l && l <= r) || (r <= l && l <= m)) {
            return left;
        }
        return right;
    }

    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    static void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        System.arraycopy(tmp, 0, nums, left, tmp.length);
    }

    public static void heapSort(int[] nums) {
        Queue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            heap.add(num);
        }
        int i = nums.length - 1;
        while (!heap.isEmpty() && i >= 0) {
            Integer poll = heap.poll();
            System.out.print(poll + " ");
            PrintUtil.printHeap(heap);
            nums[i--] = poll;
        }
    }

    public static void bucketSort(float[] nums) {

        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }

        for (float num : nums) {

            int i = (int) (num * k);

            buckets.get(i).add(num);
        }

        for (List<Float> bucket : buckets) {

            Collections.sort(bucket);
        }

        int i = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[i++] = num;
            }
        }
    }

    static void countingSort(int[] nums) {

        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }

        int[] counter = new int[m + 1];
        for (int num : nums) {
            counter[num]++;
        }

        int i = 0;
        for (int num = 0; num < m + 1; num++) {
            for (int j = 0; j < counter[num]; j++, i++) {
                nums[i] = num;
            }
        }
    }

    public static void radixSort(int[] nums) {

        int m = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > m) {
                m = num;
            }
        }

        for (int exp = 1; exp <= m; exp *= 10) {

            countingSortDigit(nums, exp);
        }
    }

    static void countingSortDigit(int[] nums, int exp) {

        int[] counter = new int[10];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int d = digit(nums[i], exp);
            counter[d]++;
        }

        for (int i = 1; i < 10; i++) {
            counter[i] += counter[i - 1];
        }

        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int d = digit(nums[i], exp);
            int j = counter[d] - 1;
            res[j] = nums[i];
            counter[d]--;
        }

        System.arraycopy(res, 0, nums, 0, n);
    }

    static int digit(int num, int exp) {

        return (num / exp) % 10;
    }

}
