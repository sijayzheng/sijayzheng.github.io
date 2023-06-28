package sijay.zheng.z.javase.thread;

/**
 * @author Sijay
 */
public class TestThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("doing---" + i);
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
    }
}
