package sijay.zheng.javase.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sijay
 * @date 2022/3/13 16:00
 */
public class TestLock {
    public static void main(String[] args) {
        TestTickets testTickets = new TestTickets();
        new Thread(testTickets).start();
        new Thread(testTickets).start();
        new Thread(testTickets).start();
        new Thread(testTickets).start();
    }
}

class TestTickets implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket-- + "张票");
                    Thread.sleep(1000);
                } else {
                    System.out.println(Thread.currentThread().getName() + "没有票了");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
