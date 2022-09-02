package edu.dsm.test;

import java.io.*;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) throws IOException {
        //1.创建套接字,指定服务器的IP和端口号
        Socket s = new Socket("localhost",8888);
        //2.利用输入流先服务端发送数据
        OutputStream os = s.getOutputStream();
        //OutputStream 没有发送字符串的方法，所以套了个DataOutputStream来处理流
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("你好");
        //接收服务器端的回话
        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        System.out.println("服务器端对我说："+dis.readUTF());
        //3.关闭流 + 关闭网络资源
        dos.close();
        os.close();
        s.close();
    }
}
