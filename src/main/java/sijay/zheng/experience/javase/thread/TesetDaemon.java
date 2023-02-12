package sijay.zheng.experience.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/10 21:32
 */
public class TesetDaemon {
    public static void main(String[] args) {
        God god = new God();
        People people = new People();
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();
        Thread thread1 = new Thread(people);
        thread1.start();
    }
}

class People implements Runnable {
    @Override
    public void run() {
        System.out.println("hello world");
        for (int i = 0; i < 36500; i++) {
            System.out.println("alive" + i);
        }
        System.out.println("goodbye world");
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I am alive");
        }
    }
}