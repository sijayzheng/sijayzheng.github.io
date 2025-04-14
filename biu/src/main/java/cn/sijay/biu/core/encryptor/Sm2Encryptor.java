package cn.sijay.biu.core.encryptor;

import cn.sijay.biu.core.entity.EncryptContext;
import cn.sijay.biu.core.enums.AlgorithmType;
import cn.sijay.biu.core.enums.EncodeType;
import org.apache.commons.lang3.StringUtils;

/**
 * sm2算法实现
 *
 * @author Sijay
 * @version 4.6.0
 */
public class Sm2Encryptor extends AbstractEncryptor {

    private final EncryptContext context;

    public Sm2Encryptor(EncryptContext context) {
        super(context);
        String privateKey = context.getPrivateKey();
        String publicKey = context.getPublicKey();
        if (StringUtils.isAnyEmpty(privateKey, publicKey)) {
            throw new IllegalArgumentException("SM2公私钥均需要提供，公钥加密，私钥解密。");
        }
        this.context = context;
    }

    /**
     * 获得当前算法
     */
    @Override
    public AlgorithmType algorithm() {
        return AlgorithmType.SM2;
    }

    /**
     * 加密
     *
     * @param value      待加密字符串
     * @param encodeType 加密后的编码格式
     */
    @Override
    public String encrypt(String value, EncodeType encodeType) {
        return null;
//        if (encodeType == EncodeType.HEX) {
//            return EncryptUtils.encryptBySm2Hex(value, context.getPublicKey());
//        } else {
//            return EncryptUtils.encryptBySm2(value, context.getPublicKey());
//        }
    }

    /**
     * 解密
     *
     * @param value 待加密字符串
     */
    @Override
    public String decrypt(String value) {
        return null;
//        return EncryptUtils.decryptBySm2(value, context.getPrivateKey());
    }
}
