/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sijay.zheng.common.constant.CommConstant;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author sijay
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpClientUtil {

    /**
     * 同步GET方法
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public static HttpResponse<String> syncGet(String url, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest.Builder builder = getBuilder(url, headers);
        return HttpClient.newHttpClient().send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    /**
     * 异步GET方法
     *
     * @return
     * @throws URISyntaxException
     */
    public static CompletableFuture<HttpResponse<String>> asyncGet(String url, Map<String, String> headers) throws URISyntaxException {
        HttpRequest.Builder builder = getBuilder(url, headers);
        return HttpClient.newHttpClient().sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    private static HttpRequest.Builder getBuilder(String url, Map<String, String> headers) throws URISyntaxException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .header(CommConstant.CONTENT_TYPE, CommConstant.APPLICATION_JSON)
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofMillis(60000))
                .uri(new URI(url));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    public static HttpResponse<String> syncPost(String url, Map<String, String> headers, String body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = postBuilder(url, headers, body);
        return HttpClient.newHttpClient().send(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    public static CompletableFuture<HttpResponse<String>> asyncPost(String url, Map<String, String> headers, String body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = postBuilder(url, headers, body);
        return HttpClient.newHttpClient().sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    private static HttpRequest.Builder postBuilder(String url, Map<String, String> headers, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(60000))
                .POST(HttpRequest.BodyPublishers.ofString(body));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    public static HttpResponse<String> syncJsonPost(String url, Map<String, String> headers, String body) throws IOException, InterruptedException {
        headers.put(CommConstant.CONTENT_TYPE, CommConstant.APPLICATION_JSON);
        return syncPost(url, headers, body);
    }

    public static CompletableFuture<HttpResponse<String>> asyncJsonPost(String url, Map<String, String> headers, String body) throws IOException, InterruptedException {
        headers.put(CommConstant.CONTENT_TYPE, CommConstant.APPLICATION_JSON);
        return asyncPost(url, headers, body);
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
