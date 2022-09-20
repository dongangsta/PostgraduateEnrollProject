package edu.dsm.test.function;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CatchTest {
    @Test
    public void catchTest() {
        //1.创建httpclient（相当于打开一个浏览器）
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.创建get请求（相当于浏览器输入网址）
        HttpGet request = new HttpGet("http://suolongkaoyan.info/school_detail/%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6");

        CloseableHttpResponse response = null;
        try {
            //3.执行get请求（相当于输入网址后敲回车键）
            response = httpClient.execute(request);

            //4.判断响应状态是否为200
            if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {

                //5.获取响应内容即页面内容
                org.apache.http.HttpEntity httpEntity = response.getEntity();
                String html = org.apache.http.util.EntityUtils.toString(httpEntity, "utf-8");

                //打印出来
                System.out.println(html);
            } else {
                //如果返回状态不是200，比如404（页面不存在）等
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    @Test
    public void jsoupTest() throws IOException {
        Document document = Jsoup.connect("https://zhuanlan.zhihu.com/p/492385833").timeout(20000).get();
        String title = document.title();
        System.out.println("title is: " + title);
        Element data = document.getElementById("root");
        System.out.println("data is: " +data);
        Elements elementsByClass = document.getElementById("root").getElementsByClass("RichText ztext Post-RichText css-yvdm7v");
        System.out.println("elements are: " +elementsByClass);
        String text = elementsByClass.toString();
        System.out.println(text);
//        System.out.println("text is: " + elementsByClass.text());
        //System.out.println(document);
    }
}
