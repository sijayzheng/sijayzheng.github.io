package sijay.zheng.experience.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/10 21:14
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println("main thread is " + Thread.currentThread().getPriority());
        Thread thread = new Thread(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，优先级：" + Thread.currentThread().getPriority()), "thread");
        Thread thread1 = new Thread(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，优先级：" + Thread.currentThread().getPriority()), "thread1");
        Thread thread2 = new Thread(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，优先级：" + Thread.currentThread().getPriority()), "thread2");
        Thread thread3 = new Thread(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，优先级：" + Thread.currentThread().getPriority()), "thread3");
        Thread thread4 = new Thread(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，优先级：" + Thread.currentThread().getPriority()), "thread4");

        thread.setPriority(Thread.MAX_PRIORITY);
        thread1.setPriority(8);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread3.setPriority(3);
        thread4.setPriority(Thread.NORM_PRIORITY);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
