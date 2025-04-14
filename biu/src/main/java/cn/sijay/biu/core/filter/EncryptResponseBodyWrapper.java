package cn.sijay.biu.core.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 加密响应参数包装类
 *
 * @author Sijay
 */
public class EncryptResponseBodyWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final ServletOutputStream servletOutputStream;
    private final PrintWriter printWriter;

    public EncryptResponseBodyWrapper(HttpServletResponse response) throws IOException {
        super(response);
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.servletOutputStream = this.getOutputStream();
        this.printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream));
    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return byteArrayOutputStream.toByteArray();
    }

    public String getContent() throws IOException {
        flushBuffer();
        return byteArrayOutputStream.toString();
    }

    /**
     * 获取加密内容
     *
     * @param servletResponse response
     * @param publicKey       RSA公钥 (用于加密 AES 秘钥)
     * @param headerFlag      请求头标志
     * @return 加密内容
     * @throws IOException
     */
    public String getEncryptContent(HttpServletResponse servletResponse, String publicKey, String headerFlag) throws IOException {
        // 生成秘钥
//        String aesPassword = RandomUtil.randomString(32);
//        // 秘钥使用 Base64 编码
//        String encryptAes = EncryptUtils.encryptByBase64(aesPassword);
//        // Rsa 公钥加密 Base64 编码
//        String encryptPassword = EncryptUtils.encryptByRsa(encryptAes, publicKey);
//
//        // 设置响应头
//        servletResponse.addHeader("Access-Control-Expose-Headers", headerFlag);
//        servletResponse.setHeader(headerFlag, encryptPassword);
//        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        servletResponse.setHeader("Access-Control-Allow-Methods", "*");
//        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//
//        // 获取原始内容
//        String originalBody = this.getContent();
//        // 对内容进行加密
//        return EncryptUtils.encryptByAes(originalBody, aesPassword);
        return "";
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                byteArrayOutputStream.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                byteArrayOutputStream.write(b, off, len);
            }
        };
    }

    @Override
    public PrintWriter getWriter() {
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (servletOutputStream != null) {
            servletOutputStream.flush();
        }
        if (printWriter != null) {
            printWriter.flush();
        }
    }

    @Override
    public void reset() {
        byteArrayOutputStream.reset();
    }

}
