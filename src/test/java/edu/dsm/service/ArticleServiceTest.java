package edu.dsm.service;

import edu.dsm.util.TxtUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Resource
    ArticleService articleService;

    @Test
    public void insertArticleFromZhiHuArticleList() throws IOException {
        //  输入：在txt文件先键入大学名称 然后空格 再贴入相应的知乎文章搜索页动态网页代码
        String strHtml = TxtUtil.txt2String(new File("E:/url.txt"));
        System.out.println(strHtml);
        String collegeName = strHtml.substring(0,strHtml.indexOf(" "));
        Pattern pattern = Pattern.compile("//[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(strHtml);
        List<String> urlArr = new ArrayList<>();
        System.out.println(urlArr);
        while (matcher.find()) {
            if (matcher.group().contains("zhuanlan")) {
                String matcherStr = "https:" + matcher.group();
                urlArr.add(matcherStr);
            }
        }
        catchZhiHuArticle(urlArr,collegeName);
    }

    public void catchZhiHuArticle(List<String> paths,String collegeName){
        try {
            int num = 0;
            for (String path:paths) {
                Document document = Jsoup.connect(path).timeout(50000).get();
                String title = document.title();
                System.out.println("正在添加的title is" + title);
                Elements textInElements = document.getElementById("root").getElementsByClass("RichText ztext Post-RichText css-yvdm7v");
                String text = textInElements.toString();
                text = text.replaceAll("</div?[^>]+>", ""); //剔出</div>的标签
                text = text.replaceAll("<div?[^>]+>", ""); //剔出<div>的标签
                int cnt = articleService.addArticle(1,collegeName,title,text);
                if (cnt == 1){
                    System.out.println(title + "添加成功");
                    num++;
                }
            }
            if(num == paths.size()) {
                System.out.println("全部添加成功，成功个数" +paths.size());
            }
            else{
                System.out.println("部分添加失败，失败个数" + (paths.size()-num));
            }
        }
        catch (IOException e){
            System.out.println("IO读写异常");
        }
    }
}
