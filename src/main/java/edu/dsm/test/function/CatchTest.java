package edu.dsm.test.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
     * 测试失败：结果：知乎的文章列表中的list暂时通过jsoup无法抓取,Htmlunit模拟未成功
     *
     * @throws IOException the io exception
     */
    @Test
    public void testZhiHuList() throws IOException{
        //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置
        WebClient browser = new WebClient();
        browser.getOptions().setCssEnabled(false);
        browser.getOptions().setJavaScriptEnabled(true);
        browser.getOptions().setThrowExceptionOnScriptError(false);
        //设置等待js的加载时间
        browser.waitForBackgroundJavaScript(20000);
        //获取页面
        HtmlPage htmlPage = browser.getPage("https://www.zhihu.com/search?q=%E4%B8%9C%E8%92%99%E7%94%B5%E5%AD%90%E4%BF%A1%E6%81%AF&type=content&utm_content=search_suggestion&vertical=article");
        //使用xml的方式解析获取到jsoup的document对象
        Document document = Jsoup.parse(htmlPage.asXml());
       //        Element data = document.getElementById("root");
//        System.out.println("data is: " +data);

        Elements elementsByClass = document.getElementById("root").getElementsByClass("SearchMain");
        System.out.println("elements are: " +elementsByClass);
        for (Element element:elementsByClass) {
            System.out.println("element" + element.id() + "" + element.text());
        }
    }


}
