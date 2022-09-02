package edu.dsm.test;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

//接收方
public class TestReceive {
    public static void main(String[] args){
        System.out.println("老师上线了....");
        DatagramSocket ds = null;
        try{
            ds= new DatagramSocket(9999);
            while (true){
                //1.创建套接字，指定接收方端
                //2.创建一个空的数据包，然后放入我们dp数据包中填充
                byte[] bytes = new byte[2048];
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
                //3.接收方的数据包，然后放入我们的dp数据包中填充
                ds.receive(dp);
                //4.取出数据
                byte[] data = dp.getData();
                String s = new String(data,0,dp.getLength());//dp.getLength() 学生发的有效数据
                System.out.println("学生说："+s);
                //如果老师接收到学生的下线标志，就跳出循环
                if (s.equals("bye")){
                    System.out.println("学生下线了，老师也下线了");
                    break;
                }
                //老师进行回复
                Scanner sc = new Scanner(System.in);
                System.out.println("老师：");
                String str = sc.next();
                byte[] bytes1 = str.getBytes();
        /*
            需要准备四个参数
            1.所要发送的字符串数组
            2.所要发送的字符串数组的长度
            3.接收方的ip
            4.接收方的端口号
         */
                DatagramPacket dp2 = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("localhost"), 8888);

                //发送
                ds.send(dp2);
            }
        }catch (SocketException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            ds.close();
        }
    }
}

