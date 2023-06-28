package sijay.zheng.z.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/13 16:42
 */
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        producer.start();
        consumer.start();
    }
}

class Producer extends Thread {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产者生产" + i + "鸡");
        }
    }
}

class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费者消费" + container.pop().getId() + "鸡");
        }
    }
}

class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class SynContainer {
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    public synchronized void push(Chicken chicken) {
        if (count == chickens.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count++] = chicken;
        this.notifyAll();
    }

    public synchronized Chicken pop() {
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        this.notifyAll();
        return chickens[count];
    }
}
