package tcp;

/*
    服务器端
    1、固定服务器端的端口号
    2、创建并启动TCP服务器
    3、等待客户链接
    4、定义接、收发消息的对象
* */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int port = 9002;

    public static void main(String[] args) throws IOException {
        // 2、创建并启动TCP
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器已启动");
        // 3、等待客户链接
        Socket clientsocket = serverSocket.accept();
        //    连接成功
        System.out.println(String.format("已有客户端连接，IP:%s,端口号:%d",
                clientsocket.getInetAddress().getHostAddress(),
                clientsocket.getPort()));

        // 4、定义接、收发消息的对象
        try(
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(clientsocket.getOutputStream()));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(clientsocket.getInputStream()));
                ){
            while (true){
                // 接收客户消息
                String msg = reader.readLine();
                if(msg != null && !msg.equals("")){
                    System.out.println("接收客户端消息：" + msg);

                    // 给客户端反馈消息
                    String serMsg = "我收到了";
                    writer.write(serMsg + "\n");
                    writer.flush();
                }
            }
        }
    }
}
