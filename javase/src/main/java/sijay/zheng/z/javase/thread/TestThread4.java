package sijay.zheng.z.javase.thread;

/**
 * @author Sijay
 */
public class TestThread4 implements Runnable {
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            System.out.println(Thread.currentThread()
                    .getName() + " get the " + ticketNums-- + " ticket");
        }
    }

    public static void main(String[] args) {
        TestThread4 t = new TestThread4();
        new Thread(t, "tom").start();
        new Thread(t, "tom1").start();
        new Thread(t, "tom2").start();
        new Thread(t, "tom3").start();
        new Thread(t, "tom4").start();
        new Thread(t, "tom5").start();
        new Thread(t, "tom6").start();
    }
}
