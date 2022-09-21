package edu.dsm.test.function;

import edu.dsm.util.TxtUtil;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 测试成功：Jsoup抓取知乎文章页成功
     *
     * @throws IOException the io exception
     */
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
        String newText = text.replaceAll("</div?[^>]+>", ""); //剔出</div>的标签
        newText = newText.replaceAll("<div?[^>]+>", ""); //剔出<div>的标签
        System.out.println(newText);
//        System.out.println("text is: " + elementsByClass.text());
        //System.out.println(document);
    }

    /**
     * 测试失败：结果：知乎的文章列表中的list暂时通过jsoup无法抓取
     *
     * @throws IOException the io exception
     */
    @Test
    public void testZhiHuList() throws IOException{
        Document document =
                Jsoup.connect("https://www.zhihu.com/search?q=%E4%B8%9C%E8%92%99%E7%94%B5%E5%AD%90%E4%BF%A1%E6%81%AF&type=content&utm_content=search_suggestion&vertical=article").timeout(20000).get();
//        Element data = document.getElementById("root");
//        System.out.println("data is: " +data);
        Elements elementsByClass = document.getElementById("root").getAllElements();
        System.out.println("elements are: " +elementsByClass);
        for (Element element:elementsByClass) {
            System.out.println("element" + element.id() + "" + element.text());
        }
    }

    @Test
    public void getUrl(){
        String strHtml = TxtUtil.txt2String(new File("D:/url.txt"));
        Pattern pattern = Pattern.compile("//[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(strHtml);
        List<String> urlArr = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group().contains("zhuanlan")) {
                String matcherStr = "https:" + matcher.group();
                urlArr.add(matcherStr);
            }
        }
        System.out.println(urlArr);
    }

}
