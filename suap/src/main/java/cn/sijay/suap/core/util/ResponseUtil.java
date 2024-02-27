package cn.sijay.suap.core.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * <em>ResponseUtil</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/31 14:26
 */
@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ResponseUtil {

    public static void setDownloadFileName(HttpServletResponse response, String fileName) {
        String name = URLEncoder.encode(new String((fileName).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,filename");
        response.setHeader("Content-Disposition", "attachment; filename=%s;filename*=utf-8''%s".formatted(name, name));
        response.setHeader("filename", name);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
