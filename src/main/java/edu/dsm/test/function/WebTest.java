package edu.dsm.test.function;

import edu.dsm.util.WebUtil;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class WebTest {
    @Test
    public void webTest() throws UnknownHostException {

        String ip1 = WebUtil.getIpByName("www.baidu.com");
        // from百度：实现 String 类型的 ip 与整数之间的相互转化。
        // 例如：将 “192.168.0.1” 转换为整数，然后给定这个整数仍然可以得到这个 String 类型的 ip。
        int intIp = WebUtil.Ip2Int(ip1);
        System.out.printf("ip转换为整数的结果：%s\n", intIp);
        System.out.printf("整数转换为ip后的结果：%s\n", WebUtil.int2Ip(intIp));

        //  InetScoketAddress 封装了IP ，端口号
        //封装一个ip和端口号
        InetSocketAddress isa = new InetSocketAddress("192.169.199.222",8080);
        //得到域名
        System.out.println(isa.getHostName());
        //得到端口号
        System.out.println(isa.getPort());
        //getAddress 封装成一个 InetAddress对象
        InetAddress ia = isa.getAddress();
    }
    @Test
    public void localDateTest(){
        LocalDateTime l_da1 = LocalDateTime.parse("2007-04-04");
        System.out.println(l_da1.toString());
    }
}
