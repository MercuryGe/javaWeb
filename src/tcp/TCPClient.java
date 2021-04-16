package tcp;

/*
    客户端
    1、与客户端沟通的服务器端ip与端口号
    2、创建并启动客户端，连接服务器端
    3、定义接、收发消息的对象
        构建消息并发送
        接受服务器返回响应
* */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    // 1、与客户端沟通的服务器端ip与端口号
    private static final String ip = "1.116.140.95";
    private static final int port = 9002;

    public static void main(String[] args) throws IOException {
        // 2、创建并启动客户端，连接服务器端
        Socket socket = new Socket(ip,port);

        // 定义接、收发消息的对象
        try(
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in);
                ){
            while(true){
                System.out.print("->");
                // 构建消息并发送
                String msg = scanner.nextLine();
                writer.write(msg + "\n");
                writer.flush();
                // 接受服务器返回响应
                String serMsg = reader.readLine();
                if(serMsg != null && !serMsg.equals("")){
                    System.out.println("服务器返回：" + serMsg);
                }
            }
        }
    }
}
