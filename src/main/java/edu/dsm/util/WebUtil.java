package edu.dsm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络相关操作工具类
 */
public class WebUtil {
    /**
     * 通过域名获得ip地址（返回String）
     *
     * @param hostName the host name
     * @return the ip by name
     * @throws UnknownHostException the unknown host exception
     */
    public static String getIpByName(String hostName) throws UnknownHostException {
        // InetAddress inetAddress = new InetAddress(); 不能直接创建对象，因为 InetAddress被default修饰了
        // InetAddress 封装了IP
        return InetAddress.getByName(hostName).getHostAddress();
    }

    /**
     * 讲ip地址转换成int
     *
     * @param strIp the str ip
     * @return the int
     */
    public static int Ip2Int(String strIp){
        String[] s = strIp.split("\\.");
        if(s.length != 4){
            return 0;
        }
        byte[] bytes = new byte[s.length];
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = (byte)Integer.parseInt(s[i]);
        }
        return byte2Int(bytes);

    }

    /**
     * 把byte数组转换回int
     *
     * @param bytes the bytes
     * @return the int
     */
    public static int byte2Int(byte[] bytes){
        int n = bytes[0]& 0xFF;
        n |= ((bytes[1] << 8) & 0xFF00);
        n |= ((bytes[2] << 16) & 0xFF0000);
        n |= ((bytes[3] << 24) & 0xFF000000);
        return n;
    }

    /**
     * 把int转换回ip
     *
     * @param intIp the int ip
     * @return the string
     */
    public static String int2Ip(int intIp){
        byte[] bytes = int2byte(intIp);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            sb.append(bytes[i] & 0xFF);
            if(i < 3){
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /**
     * 把int转换成byte数组
     *
     * @param i the
     * @return the byte [ ]
     */
    public static byte[] int2byte(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(0xff & i);
        bytes[1] = (byte)((0xff00 & i) >> 8);
        bytes[2] = (byte)((0xff0000 & i) >> 16);
        bytes[3] = (byte)((0xff000000 & i) >> 24);
        return bytes;
    }

}
