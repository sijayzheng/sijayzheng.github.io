package sijay.zheng.experience.javase.thread;

import java.util.concurrent.*;

/**
 * @author Sijay
 */
public class TestCallable implements Callable {
    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        TestCallable t1 = new TestCallable("http://127.0.0.1/resource/pic/rocket.png", "rocket1.png");
        TestCallable t2 = new TestCallable("http://127.0.0.1/resource/pic/rocket.png", "rocket2.png");
        TestCallable t3 = new TestCallable("http://127.0.0.1/resource/pic/rocket.png", "rocket3.png");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> r1 = executorService.submit(t1);
        Future<Boolean> r2 = executorService.submit(t2);
        Future<Boolean> r3 = executorService.submit(t3);
        try {
            System.out.println(r1.get());
            System.out.println(r2.get());
            System.out.println(r3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println(name + " download finsh");
        return true;
    }
}