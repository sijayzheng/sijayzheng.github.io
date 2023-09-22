package sijay.zheng.datastructure;

/**
 * @author Sijay
 * @date 2023/3/25 23:27
 */
public class Array {
    public static int[][] twoDimensional2Sparse(int[][] td, int def) {
        int count = 0;
        for (int[] ints : td) {
            for (int val : ints) {
                if (val != def) {
                    count++;
                }
            }
        }
        int[][] sparse = new int[count + 1][3];
        sparse[0][0] = td.length;
        sparse[0][1] = td[0].length;
        sparse[0][2] = count;
        int row = 1, col = 1;
        for (int[] ints : td) {
            for (int val : ints) {
                if (val != def) {
                    sparse[row][col] = val;
                    row++;
                    col++;
                }
            }
        }
        return sparse;
    }
}
