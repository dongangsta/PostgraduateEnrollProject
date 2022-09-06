package edu.dsm.test.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestClient2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        DataOutputStream dos = null;
        try{
            //1.创建套接字，指定服务端和端口号
            serverSocket= new ServerSocket(8888);

            //2.等待客户端发来的信息
            //accept阻塞方法：等待接收客户端的数据，什么是接收到数据了，什么是时候程序继续向下执行
            socket = serverSocket.accept();
            // accept 的返回值是Socket，接到Socket以后，客户端和服务端就可以正常通信了

            //3.感受到客户端
            is = socket.getInputStream();
            ois= new ObjectInputStream(is);

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
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeBoolean(flag);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //5.关闭流
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
