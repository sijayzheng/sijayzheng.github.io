package cn.sijay.biu.core.util;

import cn.sijay.biu.core.entity.Pair;
import cn.sijay.biu.core.exception.UtilException;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * SecureUtil
 *
 * @author Sijay
 * @since 2025-03-04
 */
public class SecureUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Base64加密
     *
     * @param data 待加密数据
     * @return 加密后字符串
     */
    public static String base64Encrypt(String data) {
        return base64Encrypt(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64加密
     *
     * @param data 待加密数据
     * @return 加密后字符串
     */
    public static String base64Encrypt(byte[] data) {
        return new String(Base64.getEncoder().encode(data), StandardCharsets.UTF_8);
    }

    /**
     * Base64解密
     *
     * @param data 待解密数据
     * @return 解密后字符串
     */
    public static String base64Decrypt(String data) {
        return new String(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    /**
     * SHA256散列
     */
    public static String sha256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException("SHA256加密错误");
        }
    }

    /**
     * 生成RSA密钥对
     */
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        // 获取 RSA 算法的 KeyPairGenerator 实例
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        // 生成密钥对
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * RSA加密
     */
    public static String rsaEncrypt(String plainText, PublicKey publicKey) throws Exception {
        // 获取 RSA 算法的 Cipher 实例
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化 Cipher 为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 执行加密操作
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        // 将加密后的字节数组进行 Base64 编码
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * RSA解密
     */
    public static String rsaDecrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        // 获取 RSA 算法的 Cipher 实例
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化 Cipher 为解密模式
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 对 Base64 编码的加密文本进行解码
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        // 执行解密操作
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * AES加密
     */
    public static String aesEncrypt(String plainText, SecretKey secretKey) throws Exception {
        // 获取 AES 算法的 Cipher 实例
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化 Cipher 为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 执行加密操作
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        // 将加密后的字节数组进行 Base64 编码
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES解密
     */
    public static String aesDecrypt(String encryptedText, SecretKey secretKey) throws Exception {
        // 获取 AES 算法的 Cipher 实例
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化 Cipher 为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 对 Base64 编码的加密文本进行解码
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        // 执行解密操作
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * SM2生成密钥对
     */
    public static Pair<ECPrivateKeyParameters, ECPublicKeyParameters> genSm2KeyPair() {
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(
                ecParameters.getCurve(),
                ecParameters.getG(),
                ecParameters.getN(),
                ecParameters.getH()
        );
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        keyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, new SecureRandom()));
        AsymmetricCipherKeyPair keyPair = keyPairGenerator.generateKeyPair();
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters publicKey = (ECPublicKeyParameters) keyPair.getPublic();
        return new Pair<>(privateKey, publicKey);
    }

    /**
     * SM2加密
     */
    public static String sm2Encrypt(ECPublicKeyParameters publicKey, String plainText) throws InvalidCipherTextException {
        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(
                ecParameters.getCurve(),
                ecParameters.getG(),
                ecParameters.getN(),
                ecParameters.getH()
        );
        ECPoint publicKeyPoint = ecParameters.getCurve().decodePoint(publicKeyBytes);
        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(publicKeyPoint, domainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(publicKeyParameters, new SecureRandom()));
        return new String(sm2Engine.processBlock(bytes, 0, bytes.length));
    }

    /**
     * SM2解密
     */
    public static String sm2Decrypt(ECPrivateKeyParameters privateKey, String cipherText) throws InvalidCipherTextException {
        byte[] privateKeyBytes = privateKey.getD().toByteArray();
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(
                ecParameters.getCurve(),
                ecParameters.getG(),
                ecParameters.getN(),
                ecParameters.getH()
        );
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(new java.math.BigInteger(1, privateKeyBytes), domainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, privateKeyParameters);
        byte[] bytes = cipherText.getBytes(StandardCharsets.UTF_8);
        return new String(sm2Engine.processBlock(bytes, 0, bytes.length));
    }

    /**
     * SM3
     */
    public static String sm3(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        SM3Digest digest = new SM3Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] output = new byte[digest.getDigestSize()];
        digest.doFinal(output, 0);
        return new String(output);
    }

    /**
     * SM4加密
     */
    public static String sm4Encrypt(byte[] key, String plainText) throws InvalidCipherTextException {
        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(new SM4Engine()));
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), new byte[16]);
        cipher.init(true, params);
        byte[] out = new byte[cipher.getOutputSize(bytes.length)];
        int length = cipher.processBytes(bytes, 0, bytes.length, out, 0);
        cipher.doFinal(out, length);
        return new String(out);
    }

    /**
     * SM4解密
     */
    public static String sm4Decrypt(byte[] key, String cipherText) throws InvalidCipherTextException {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(CBCBlockCipher.newInstance(new SM4Engine()));
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), new byte[16]);
        cipher.init(false, params);
        byte[] bytes = cipherText.getBytes(StandardCharsets.UTF_8);
        byte[] out = new byte[cipher.getOutputSize(bytes.length)];
        int length = cipher.processBytes(bytes, 0, bytes.length, out, 0);
        cipher.doFinal(out, length);
        return new String(out);
    }

    /**
     * 加密密码
     */
    public static String hashPasswd(String password) {
        return base64Encrypt(sha256(password));
    }

    public static boolean verifyPasswd(String password, String hashedPassword) {
        return hashedPassword.equals(hashPasswd(password));
    }
}
