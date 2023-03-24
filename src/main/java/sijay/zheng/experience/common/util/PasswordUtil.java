package sijay.zheng.experience.common.util;

import sijay.zheng.experience.common.record.KVElement;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author sijay
 * @date 2022/11/24 17:34
 */
public class PasswordUtil {

    private static final String LOW = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL = "~!@#$%^&*()_+/-=[]{};:'<>?.";
    private static final String NUM = "0123456789";
    private PasswordUtil() {
    }

    // 随机获取字符串字符
    private static char getRandomChar(String str) {
        SecureRandom random = new SecureRandom();
        return str.charAt(random.nextInt(str.length()));
    }

    // 随机获取小写字符
    private static char getLowChar() {
        return getRandomChar(LOW);
    }

    // 随机获取大写字符
    private static char getUpperChar() {
        return Character.toUpperCase(getLowChar());
    }

    // 随机获取数字字符
    private static char getNumChar() {
        return getRandomChar(NUM);
    }

    // 随机获取特殊字符
    private static char getSpecialChar() {
        return getRandomChar(SPECIAL);
    }

    //指定调用字符函数
    private static char getRandomChar(int funNum) {
        switch (funNum) {
            case 0:
                return getLowChar();
            case 1:
                return getUpperChar();
            case 2:
                return getNumChar();
            default:
                return getSpecialChar();
        }
    }

    // 指定长度，随机生成复杂密码
    public static String getRandomPassword(int num) {
        List<Character> list = new ArrayList<>(num);
        list.add(getLowChar());
        list.add(getUpperChar());
        list.add(getNumChar());
        list.add(getSpecialChar());
        for (int i = 4; i < num; i++) {
            SecureRandom random = new SecureRandom();
            int funNum = random.nextInt(4);
            list.add(getRandomChar(funNum));
        }
        Collections.shuffle(list);   // 打乱排序
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static String getPasswordSalt() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String a = uuid.substring(0, 16);
        String b = uuid.substring(16, 32);
        char[] chars = new char[16];
        for (int i = 0; i < 16; i++) {
            chars[i] = (char) (a.charAt(i) | b.charAt(i));
        }
        return new String(chars);
    }

    public static KVElement encryptPassword(String password) {
        String salt = getPasswordSalt();
        password = EncryptUtil.aesEncrypt(password, salt);
        return new KVElement(password, salt);
    }

}
