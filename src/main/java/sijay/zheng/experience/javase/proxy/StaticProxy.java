package sijay.zheng.experience.javase.proxy;

/**
 * @author Sijay
 */
public class StaticProxy {
    public static void main(String[] args) {
        new wedingCorp(new You()).MarryHappy();
        new Thread(() -> System.out.println("233")).start();
    }
}

interface Marry {
    void MarryHappy();
}

class You implements Marry {

    @Override
    public void MarryHappy() {
        System.out.println("will marry");
    }
}

class wedingCorp implements Marry {
    private Marry target;

    public wedingCorp(Marry target) {
        this.target = target;
    }

    @Override
    public void MarryHappy() {
        before();
        this.target.MarryHappy();
        after();
    }

    private void before() {
        System.out.println("make stage");
    }

    private void after() {
        System.out.println("get money");
    }
}