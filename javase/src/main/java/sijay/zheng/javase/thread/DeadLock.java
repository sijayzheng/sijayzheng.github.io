package sijay.zheng.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/13 15:43
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup girl0 = new Makeup(0, "girl0");
        Makeup girl1 = new Makeup(1, "girl1");
        girl0.start();
        girl1.start();
    }
}

class Lipstick {

}

class Mirror {
}

class Makeup extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    int choice;
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            deadMakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deadMakeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + " lipstick lock");
                Thread.sleep(1000);
                synchronized (mirror) {
                    System.out.println(this.girlName + " mirror lock");
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + " mirror lock");
                Thread.sleep(2000);
                synchronized (lipstick) {
                    System.out.println(this.girlName + " lipstick lock");
                }
            }
        }
    }
}
