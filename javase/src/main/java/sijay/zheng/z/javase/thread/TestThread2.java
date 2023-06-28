package sijay.zheng.z.javase.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sijay
 */
public class TestThread2 extends Thread {
    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println(name + " download finsh");
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("http://127.0.0.1/resource/pic/rocket.png", "rocket1.png");
        TestThread2 t2 = new TestThread2("http://127.0.0.1/resource/pic/rocket.png", "rocket2.png");
        TestThread2 t3 = new TestThread2("http://127.0.0.1/resource/pic/rocket.png", "rocket3.png");
        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownloader {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("下载出现异常");
        }
    }
}
