package edu.dsm.util;

import org.junit.Test;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class Md5Util {
    /**
     * 将string转换成MD5加密
     *
     * @param inStr input
     * @return the string
     */
    public static String string2MD5(String inStr){
            MessageDigest md5;
            try{
                md5 = MessageDigest.getInstance("MD5");
            }catch (Exception e){
                e.printStackTrace();
                return "";
            }
            char[] charArray = inStr.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
            return hexValue.toString();
        }

    @Test
    public void md5Test() {
        System.out.println(Md5Util.string2MD5("11111111"));
        //System.out.println();
    }
}