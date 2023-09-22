package sijay.zheng.datastructure;

/**
 * @author sijay
 * @date 2023/2/7 14:17
 */
public class Stack<T> {
    private final int maxSize;
    private T[] arr;
    private int index = -1;

    public Stack(int size) {
        this.maxSize = size;
    }

    public Stack(T[] arr, int size) {
        this.arr = arr;
        this.maxSize = size;
    }

    /**
     * Add an element to the top of a stack
     *
     * @return
     */
    public Stack<T> push(T t) {
        if (this.index == -1) {
            this.arr = (T[]) new Object[this.maxSize];
        }
        this.index = this.index + 1;
        if (this.index < maxSize) {
            this.arr[this.index] = t;
        } else {
            throw new RuntimeException("Stack overflow");
        }
        return this;
    }

    /**
     * Remove an element from the top of a stack
     *
     * @return
     */
    public T pop() {
        T t = this.arr[this.index];
        this.index = this.index - 1;
        return t;
    }

    /**
     * Check if the stack is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return index == -1;
    }

    /**
     * Check if the stack is full
     *
     * @return
     */
    public boolean isFull() {
        return this.index >= this.maxSize;
    }

    /**
     * Get the value of the top element without removing it
     *
     * @return
     */
    public T peek() {
        if (this.index == -1) {
            throw new RuntimeException("Stack unoverflow");
        }
        return this.arr[this.index];
    }

}
