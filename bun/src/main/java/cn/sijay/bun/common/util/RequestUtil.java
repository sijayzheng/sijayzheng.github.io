package cn.sijay.bun.common.util;

import cn.sijay.bun.common.constant.Constants;
import cn.sijay.bun.common.constant.ContentType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * <strong>RequestUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-13
 */
public class RequestUtil {

    public static HttpResponse<String> post(String url, Object body) throws IOException, InterruptedException {
        HttpRequest builder = HttpRequest.newBuilder()
                                         .version(HttpClient.Version.HTTP_1_1)
                                         .uri(URI.create(url))
                                         .timeout(Duration.ofMillis(60000))
                                         .header(Constants.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                                         .POST(HttpRequest.BodyPublishers.ofString(JSONUtil.toJSONString(body))).build();
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            return httpClient.send(builder, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        }
    }

}
