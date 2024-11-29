package sijay.zheng.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/10 21:06
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i);
            }
        });
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        while (thread.getState() != Thread.State.TERMINATED) {
            Thread.sleep(500);
            System.out.println(thread.getState());
        }

        System.out.println(thread.getState());
    }
}
