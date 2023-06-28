package sijay.zheng.z.javase.thread;

/**
 * @author Sijay
 * @date 2022/3/13 16:59
 */
public class TestPc2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Activator(tv).start();
        new Watcher(tv).start();
    }
}

class Activator extends Thread {
    TV tv;

    public Activator(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("xxxx节目" + i);
            } else {
                this.tv.play("aaaaa节目" + i);
            }
        }
    }
}

class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

class TV {
    String name;
    boolean flag = true;

    public synchronized void play(String name) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("playing" + name);
        this.notifyAll();
        this.name = name;
        this.flag = !this.flag;
    }

    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("watching " + this.name);
        this.notifyAll();
        this.flag = !this.flag;
    }
}
