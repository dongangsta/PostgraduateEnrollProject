package edu.dsm.test.function;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocketTest {

    @Test
    public void socket() throws IOException {
        Process p = Runtime.getRuntime().exec("ping " + "192.168.2.106");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
        }
        System.out.println("本次指令返回的消息是：");
        System.out.println(new String(sb.toString().getBytes("gbk")));
    }
}
