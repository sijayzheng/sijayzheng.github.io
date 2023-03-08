package sijay.zheng.experience;

public class Main {
    public static void main(String[] args) {

    }
/*

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                result.add(List.of(1));
            } else if (i == 1) {
                result.add(Arrays.asList(1, 1));
            } else {
                List<Integer> row = new ArrayList<>();
                row.add(1);
                List<Integer> lastRow = result.get(i - 1);
                for (int j = 1; j < i; j++) {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
                row.add(1);
                result.add(row);
            }
        }
        return result;
    }
*/

}