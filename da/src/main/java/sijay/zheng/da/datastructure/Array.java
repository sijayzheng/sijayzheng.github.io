package sijay.zheng.da.datastructure;

/**
 * @author sijay
 * @desc Array
 * @date 2023/11/28 9:20
 */
public class Array {
    private int[] array;
    private int size = 0;
    private final int DEFAULT_SIZE = 8;

    public Array() {
        this.array = new int[8];
    }

    public Array(int size) {
        this.array = new int[size];
    }

    public void add(int value) {
        if (size < array.length) {
            array[size] = value;
        }
        size++;
    }

    public void remove(int index) {
        if (index > -1 && index < array.length) {

        }
        size--;
    }

    public int get(int index) {
        return array[index];
    }

    public void update(int index, int value) {

    }
}
