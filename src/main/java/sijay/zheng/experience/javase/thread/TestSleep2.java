package sijay.zheng.experience.javase.thread;

import java.time.*;

/**
 * @author Sijay
 */
public class TestSleep2 {
    public static void tenDown() throws InterruptedException {
        int n = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(n--);
            if (n < 0) {
                break;
            }
        }
    }

    public static void printTime() throws InterruptedException {
        while (true) {
            System.out.println(LocalTime.now()
                    .toString());
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        try {
            TestSleep2.printTime();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}