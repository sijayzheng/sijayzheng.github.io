package cn.sijay.biu.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * DesensitizedUtil
 *
 * @author Sijay
 * @since 2025-03-14
 */
public class DesensitizedUtil {
    public DesensitizedUtil() {
    }

    public static String desensitized(CharSequence str, DesensitizedType desensitizedType) {
        if (StringUtil.isBlank(str)) {
            return "";
        } else {
            return switch (desensitizedType) {
                case USER_ID -> String.valueOf(userId());
                case CHINESE_NAME -> chineseName(String.valueOf(str));
                case ID_CARD -> idCardNum(String.valueOf(str), 1, 2);
                case FIXED_PHONE -> fixedPhone(String.valueOf(str));
                case MOBILE_PHONE -> mobilePhone(String.valueOf(str));
                case ADDRESS -> address(String.valueOf(str), 8);
                case EMAIL -> email(String.valueOf(str));
                case PASSWORD -> password(String.valueOf(str));
                case CAR_LICENSE -> carLicense(String.valueOf(str));
                case BANK_CARD -> bankCard(String.valueOf(str));
                case IPV4 -> ipv4(String.valueOf(str));
                case IPV6 -> ipv6(String.valueOf(str));
                case FIRST_MASK -> firstMask(String.valueOf(str));
                case CLEAR_TO_EMPTY -> clear();
                case CLEAR_TO_NULL -> clearToNull();
            };
        }
    }

    public static String clear() {
        return "";
    }

    public static String clearToNull() {
        return null;
    }

    public static Long userId() {
        return 0L;
    }

    public static String firstMask(String str) {
        return StringUtil.isBlank(str) ? "" : StringUtil.hide(str, 1, str.length());
    }

    public static String chineseName(String fullName) {
        return firstMask(fullName);
    }

    public static String idCardNum(String idCardNum, int front, int end) {
        if (StringUtil.isBlank(idCardNum)) {
            return "";
        } else if (front + end > idCardNum.length()) {
            return "";
        } else {
            return front >= 0 && end >= 0 ? StringUtil.hide(idCardNum, front, idCardNum.length() - end) : "";
        }
    }

    public static String fixedPhone(String num) {
        return StringUtil.isBlank(num) ? "" : StringUtil.hide(num, 4, num.length() - 2);
    }

    public static String mobilePhone(String num) {
        return StringUtil.isBlank(num) ? "" : StringUtil.hide(num, 3, num.length() - 4);
    }

    public static String address(String address, int sensitiveSize) {
        if (StringUtil.isBlank(address)) {
            return "";
        } else {
            int length = address.length();
            return StringUtil.hide(address, length - sensitiveSize, length);
        }
    }

    public static String email(String email) {
        if (StringUtil.isBlank(email)) {
            return "";
        } else {
            int index = StringUtils.indexOf(email, '@');
            return index <= 1 ? email : StringUtil.hide(email, 1, index);
        }
    }

    public static String password(String password) {
        return StringUtil.isBlank(password) ? "" : StringUtils.repeat('*', password.length());
    }

    public static String carLicense(String carLicense) {
        if (StringUtil.isBlank(carLicense)) {
            return "";
        } else {
            if (carLicense.length() == 7) {
                carLicense = StringUtil.hide(carLicense, 3, 6);
            } else if (carLicense.length() == 8) {
                carLicense = StringUtil.hide(carLicense, 3, 7);
            }

            return carLicense;
        }
    }

    public static String bankCard(String bankCardNo) {
        if (StringUtil.isBlank(bankCardNo)) {
            return bankCardNo;
        } else {
            bankCardNo = StringUtil.trim(bankCardNo);
            if (bankCardNo.length() < 9) {
                return bankCardNo;
            } else {
                int length = bankCardNo.length();
                int endLength = length % 4 == 0 ? 4 : length % 4;
                int midLength = length - 4 - endLength;
                StringBuilder buf = new StringBuilder();
                buf.append(bankCardNo, 0, 4);
                for (int i = 0; i < midLength; ++i) {
                    if (i % 4 == 0) {
                        buf.append(' ');
                    }
                    buf.append('*');
                }
                buf.append(' ').append(bankCardNo, length - endLength, length);
                return buf.toString();
            }
        }
    }

    public static String ipv4(String ipv4) {
        return StringUtil.subBefore(ipv4, '.', false) + ".*.*.*";
    }

    public static String ipv6(String ipv6) {
        return StringUtil.subBefore(ipv6, ':', false) + ":*:*:*:*:*:*:*";
    }

    public static enum DesensitizedType {
        USER_ID,
        CHINESE_NAME,
        ID_CARD,
        FIXED_PHONE,
        MOBILE_PHONE,
        ADDRESS,
        EMAIL,
        PASSWORD,
        CAR_LICENSE,
        BANK_CARD,
        IPV4,
        IPV6,
        FIRST_MASK,
        CLEAR_TO_NULL,
        CLEAR_TO_EMPTY;

        private DesensitizedType() {
        }
    }
}
