package sijay.zheng.experience.javase.socket;

import java.io.*;
import java.net.*;
import java.nio.charset.*;

/**
 * @author Sijay
 */
public class DomainB {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("waiting,,,,,");
            Thread.sleep(5000);
            String msg = "hello A";
            outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
            System.out.println(new String(socket.getInputStream()
                    .readAllBytes()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}