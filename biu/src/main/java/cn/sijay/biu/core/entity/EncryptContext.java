package cn.sijay.biu.core.entity;

import cn.sijay.biu.core.enums.AlgorithmType;
import cn.sijay.biu.core.enums.EncodeType;
import lombok.Data;

/**
 * 加密上下文 用于encryptor传递必要的参数。
 *
 * @author Sijay
 * @version 4.6.0
 */
@Data
public class EncryptContext {

    /**
     * 默认算法
     */
    private AlgorithmType algorithm;

    /**
     * 安全秘钥
     */
    private String password;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 编码方式，base64/hex
     */
    private EncodeType encode;

}
