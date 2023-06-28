package sijay.zheng.z.javase.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @author Sijay
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("等待接收消息");
            Socket accept = serverSocket.accept();
            accept.setKeepAlive(true);
            System.out.println("客户端【" + accept.getInetAddress() + "】已连接");
            DataInputStream is = new DataInputStream(accept.getInputStream());
            DataOutputStream os = new DataOutputStream(accept.getOutputStream());
            while (true) {
                System.out.println(is.readUTF());
                os.writeUTF("我在" + LocalDateTime.now() + "接收到了你的消息");
                os.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
