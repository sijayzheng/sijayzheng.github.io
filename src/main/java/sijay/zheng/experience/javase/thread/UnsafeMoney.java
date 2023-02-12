package sijay.zheng.experience.javase.thread;

/**
 * @author Sijay
 */
public class UnsafeMoney {
    public static void main(String[] args) {
        Account aaa = new Account(1000, "aaa");
        Drawing tom = new Drawing(aaa, 50, "tom");
        Drawing jan = new Drawing(aaa, 100, "jan");
        tom.start();
        jan.start();
    }
}

class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    Account account;
    int drawingMoney;
    int now;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println("not enough money");
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money = account.money - drawingMoney;
            now += drawingMoney;
            System.out.println(super.getName() + " got " + now + " money and " + account.name + " last " + account.money);
        }
    }
}