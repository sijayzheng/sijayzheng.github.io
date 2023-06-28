package sijay.zheng.z.javase.thread;

/**
 * @author Sijay
 */
public class TestSleep implements Runnable {
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()
                    .getName() + " get the " + ticketNums-- + " ticket");
        }
    }

    public static void main(String[] args) {
        TestSleep t = new TestSleep();
        new Thread(t, "tom0").start();
        new Thread(t, "tom1").start();
        new Thread(t, "tom2").start();
    }
}
