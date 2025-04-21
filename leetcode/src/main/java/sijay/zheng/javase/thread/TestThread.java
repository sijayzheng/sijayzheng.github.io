package sijay.zheng.javase.thread;

/**
 * @author Sijay
 */
public class TestThread extends Thread {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("doing---" + i);
        }
    }
}
