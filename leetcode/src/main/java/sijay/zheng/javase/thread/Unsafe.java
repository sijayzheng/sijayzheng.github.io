package sijay.zheng.javase.thread;

/**
 * @author Sijay
 */
public class Unsafe {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "tom").start();
        new Thread(buyTicket, "jan").start();
        new Thread(buyTicket, "alen").start();
    }
}

class BuyTicket implements Runnable {
    boolean flag = true;
    private int nums = 10;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() {
        if (nums < 1) {
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread()
                .getName() + " take the " + nums--);
    }
}
