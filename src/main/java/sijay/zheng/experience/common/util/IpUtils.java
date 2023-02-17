package sijay.zheng.experience.common.util;

import java.net.*;

/**
 * @author SijayZheng
 */
public class IpUtils {
    public static String getMacAddress() throws SocketException, UnknownHostException {
        byte[] hardwareAddress = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hardwareAddress.length; i++) {
            if (i != 0) {
                sb.append(":");
            }
            //字节转换为整数
            int temp = hardwareAddress[i] & 0xff;
            String str = Integer.toHexString(temp);
            sb.append(str.length() == 1 ? "0" + str : str);
        }
        return sb.toString();
    }
}
