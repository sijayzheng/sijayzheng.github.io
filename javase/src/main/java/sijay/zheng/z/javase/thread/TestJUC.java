package sijay.zheng.z.javase.thread;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sijay
 */
public class TestJUC {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> list.add(Thread.currentThread()
                    .getName())).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
    }
}
