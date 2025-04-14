package cn.sijay.biu.core.util;

import jakarta.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * ResponseUtil
 *
 * @author Sijay
 * @since 2025-04-01
 */
public class ResponseUtil {
    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     */
    public static void setResponseHeader(HttpServletResponse response, String realFileName) {
        String percentEncodedFileName = URLEncoder.encode(realFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        String contentDispositionValue = "attachment; filename=%s;filename*=utf-8''%s".formatted(percentEncodedFileName, percentEncodedFileName);
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-disposition", contentDispositionValue);
        response.setHeader("download-filename", percentEncodedFileName);
    }
}
