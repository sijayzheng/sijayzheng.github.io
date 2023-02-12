package sijay.zheng.experience.javase.thread;

import java.util.*;

/**
 * @author Sijay
 */
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread()
                            .getName());
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
    }

}