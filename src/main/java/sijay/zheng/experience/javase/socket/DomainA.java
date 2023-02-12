package sijay.zheng.experience.javase.socket;

import java.io.*;
import java.net.*;
import java.time.*;

/**
 * @author Sijay
 */
public class DomainA {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 9999)) {
            DataOutputStream os;
            DataInputStream is;
            socket.getKeepAlive();
            OutputStream outputStream = socket.getOutputStream();
            os = new DataOutputStream(outputStream);
            is = new DataInputStream(socket.getInputStream());
            while (true) {
                String msg = LocalDateTime.now() + "发送的信息";
                os.writeUTF(msg);
                os.flush();
                System.out.println("收到的消息为：" + is.readUTF());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}