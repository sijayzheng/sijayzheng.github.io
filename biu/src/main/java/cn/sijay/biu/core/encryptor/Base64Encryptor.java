package cn.sijay.biu.core.encryptor;

import cn.sijay.biu.core.entity.EncryptContext;
import cn.sijay.biu.core.enums.AlgorithmType;
import cn.sijay.biu.core.enums.EncodeType;
import cn.sijay.biu.core.util.SecureUtil;

/**
 * Base64算法实现
 *
 * @author Sijay
 * @version 4.6.0
 */
public class Base64Encryptor extends AbstractEncryptor {

    public Base64Encryptor(EncryptContext context) {
        super(context);
    }

    /**
     * 获得当前算法
     */
    @Override
    public AlgorithmType algorithm() {
        return AlgorithmType.BASE64;
    }

    /**
     * 加密
     *
     * @param value      待加密字符串
     * @param encodeType 加密后的编码格式
     */
    @Override
    public String encrypt(String value, EncodeType encodeType) {
        return SecureUtil.base64Encrypt(value);
    }

    /**
     * 解密
     *
     * @param value 待加密字符串
     */
    @Override
    public String decrypt(String value) {
        return SecureUtil.base64Decrypt(value);
    }
}
