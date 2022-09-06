package edu.dsm.test.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1.创建套接字，指定服务端和端口号
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.等待客户端发来的信息
        //accept阻塞方法：等待接收客户端的数据，什么是接收到数据了，什么是时候程序继续向下执行
        Socket socket = serverSocket.accept();
        // accept 的返回值是Socket，接到Socket以后，客户端和服务端就可以正常通信了
        //3.感受到客户端
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        boolean flag = false;
        //4.读取客户端发来的数据
        class User{
            private String userName;
            private String password;
            public String getUsername() {return userName;}
            public void setUsername(String userName) {this.userName = userName;}
            public String getPassword() {return password;}
            public void setPassword(String password) {this.password = password;}
        }
        User user = (User) ois.readObject();
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")){
            flag = true;
        }

        //向客户端输出结果
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeBoolean(flag);


        //5.关闭流
        ois.close();
        is.close();
        socket.close();
        serverSocket.close();

    }
}



