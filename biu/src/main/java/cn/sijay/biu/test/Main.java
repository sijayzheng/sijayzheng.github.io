package cn.sijay.biu.test;

import cn.sijay.biu.core.util.SecureUtil;

/**
 * Main
 *
 * @author Sijay
 * @since 2025-03-19
 */
public class Main {
    public static void main(String[] args) {
        String passwd = "123456";
        System.out.println(SecureUtil.base64Encrypt(SecureUtil.sha256(passwd)));
        System.out.println(SecureUtil.base64Encrypt(SecureUtil.sm3(passwd)));
    }
}
