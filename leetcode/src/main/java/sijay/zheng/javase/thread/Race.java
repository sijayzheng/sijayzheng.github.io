package sijay.zheng.javase.thread;

/**
 * @author Sijay
 */
public class Race implements Runnable {
    private static String winner;

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "rabbit").start();
        new Thread(race, "tortoise").start();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            String name = Thread.currentThread()
                    .getName();
            if ("rabbit".equals(name)) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (over(i)) {
                break;
            }
            System.out.println(name + " run " + i + " step");
        }
    }

    private boolean over(int step) {
        if (winner != null) {
            return true;
        }
        if (step >= 100) {
            winner = Thread.currentThread()
                    .getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }
}
