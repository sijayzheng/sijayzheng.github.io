package sijay.zheng.javase.generators;

/**
 * @author Sijay
 */
public class CrcGenerator {
    /**
     * 计算CRC16校验码
     *
     * @param data 需要校验的字符串
     * @return 校验码
     */
    public static String getCRC16(String data) {
        data = data.replace(" ", "");
        int len = data.length();
        if (!(len % 2 == 0)) {
            return "0000";
        }
        int num = len / 2;
        byte[] para = new byte[num];
        for (int i = 0; i < num; i++) {
            int value = Integer.valueOf(data.substring(i * 2, 2 * (i + 1)), 16);
            para[i] = (byte) value;
        }
        return getCRC16(para);
    }

    /**
     * @param bytes 字节数组
     * @return {@link String} 校验码
     */
    public static String getCRC16(byte[] bytes) {
        //CRC寄存器全为1
        int CRC = 0x0000ffff;
        //多项式校验值
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        //结果转换为16进制
        String result = Integer.toHexString(CRC)
                .toUpperCase();
        if (result.length() != 4) {
            StringBuilder sb = new StringBuilder("0000");
            result = sb.replace(4 - result.length(), 4, result)
                    .toString();
        }
        //交换高低位
        return result.substring(2, 4) + result.substring(0, 2);
    }

}
