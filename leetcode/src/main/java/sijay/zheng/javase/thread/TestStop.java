package sijay.zheng.javase.thread;

/**
 * @author Sijay
 */
public class TestStop implements Runnable {
    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        Thread.sleep(1);
        for (int i = 0; i < 1000; i++) {
            System.out.println("main running");
            if (i == 900) {
                testStop.stop();
                System.out.println("stop running........");
            }
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("running......" + i++);
        }
    }

    public void stop() {
        this.flag = false;
    }
}
