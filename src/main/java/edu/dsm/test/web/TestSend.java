package edu.dsm.test.web;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

//发送方
public class TestSend {
    public static void main(String[] args)  {
        System.out.println("学生上线...");
        DatagramSocket ds = null;
        try {
            // 1.准备套接字，指定发送方的套接字
            ds= new DatagramSocket(8888);
            while (true){
                //2.准备数据包，向老师发送信息
                Scanner sc = new Scanner(System.in);
                System.out.println("学生(bye，结束标识)：");
                String str = sc.next();
                byte[] bytes = str.getBytes();
        /*
            需要准备四个参数
            1.所要发送的字符串数组
            2.所要发送的字符串数组的长度
            3.接收方的ip
            4.接收方的端口号
         */
                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 9999);

                //发送
                ds.send(dp);
                //判断学生的结束标识
                if (str.equals("bye")){
                    break;
                }
                //创建一空数组，用来接收老师发送的数据
                byte[] b = new byte[2048];
                DatagramPacket dp2 = new DatagramPacket(b,b.length);
                //取出老师发送的数据
                ds.receive(dp2);
                //接收老师信息
                byte[] data = dp2.getData();
                String s = new String(data,0,dp2.getLength());//dp.getLength() 有效数据
                System.out.println("老师说："+s);
            }
        }catch (SocketException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            ds.close();
        }
    }
}

