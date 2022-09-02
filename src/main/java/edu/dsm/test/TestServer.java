package edu.dsm.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) throws IOException {
        //1.创建套接字，指定服务端和端口号
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.等待客户端发来的信息
        //accept阻塞方法：等待接收客户端的数据，什么是接收到数据了，什么是时候程序继续向下执行
        Socket socket = serverSocket.accept();
        // accept 的返回值是Socket，接到Socket以后，客户端和服务端就可以正常通信了
        //3.感受到客户端
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        //4.读取客户端发来的数据，打印到控制台
        System.out.println("客户端发来的数据为："+dis.readUTF());
        //向客户端发送一条数据 输出流
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("你好，我是服务器端，我收到了你的信息");
        //5.关闭流
        dis.close();
        is.close();
        socket.close();
        serverSocket.close();

    }
}

