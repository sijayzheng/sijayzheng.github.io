package cn.sijay.suap.core.utils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <strong>ResponseUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    public static void setDownloadFileName(HttpServletResponse response, String fileName) {
        String name = URLEncoder.encode(new String((fileName).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,filename");
        response.setHeader("Content-Disposition", "attachment; filename=%s;filename*=utf-8''%s".formatted(name, name));
        response.setHeader("filename", name);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
