package sijay.zheng.javase.thread;

/**
 * @author Sijay
 */
public class TestYield {
    public static void main(String[] args) {
        Yield yield = new Yield();
        new Thread(yield, "thread A ").start();
        new Thread(yield, "thread B ").start();
    }
}

class Yield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread()
                .getName() + "start running");
        Thread.yield();
        System.out.println(Thread.currentThread()
                .getName() + "end running");
    }
}
