package sijay.zheng.experience.common.util;

import sijay.zheng.experience.common.annotation.LogDog;
import sijay.zheng.experience.common.constant.CommConstant;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author sijay
 */
public class HttpClientUtil {

    /**
     * 同步GET方法
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @LogDog
    public static void syncGet(String url, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .header(CommConstant.CONTENT_TYPE, CommConstant.APPLICATION_JSON)
                .version(HttpClient.Version.HTTP_1_1)
                .uri(new URI(url));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    /**
     * 异步GET方法
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @LogDog
    public static void asyncGet(String url, Map<String, String> headers) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .header(CommConstant.CONTENT_TYPE, CommConstant.APPLICATION_JSON)
                .version(HttpClient.Version.HTTP_1_1)
                .uri(new URI(url));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        client.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

    /**
     * 并发执行
     *
     * @param threadNums 线程数
     */
    public long concurrent(int threadNums, Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread(() -> {
                try {
                    // 使线程在此等待，当开始门打开时，一起涌入门中
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        // 将结束门减1，减到0时，就可以开启结束门了
                        endGate.countDown();
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            });
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }
}
