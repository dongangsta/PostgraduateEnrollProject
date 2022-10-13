package edu.dsm.controller;

import edu.dsm.converter.ArticleConverter;
import edu.dsm.entity.po.AddArticleWebPo;
import edu.dsm.entity.po.Article;
import edu.dsm.entity.po.User;
import edu.dsm.entity.vo.ArticleForShow;
import edu.dsm.service.ArticleService;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import edu.dsm.util.GreenTextUtils;
import edu.dsm.util.TxtUtil;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Article controller.
 */
@Api(tags = "文章管理") //  tags：你可以当作是这个组的名字。
@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;
    /**
     * The Green text utils.
     */
    @Resource
    GreenTextUtils greenTextUtils;

    /**
     * Show news string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "ArticleMaintain")
    public String showNews(Model model){
        List<Article> articleList = articleService.getAll();
        model.addAttribute("showList", turnArticleListToShowList(articleList));
        return "admin_maintain_article";
    }

    /**
     * 进入文章页
     *
     * @return the string
     */
    @GetMapping("toShowArticle")
    public String toShowArticle() {
        return "user_show_articles";
    }

    /**
     * 返回文章列表
     *
     * @return the list
     */
    @GetMapping("articlesData")
    @ResponseBody
    public List<ArticleForShow> articlesData() {
        List<Article> articleList = articleService.getAll();
        return turnArticleListToShowList(articleList);
    }

    /**
     * 进入相应的文章页
     *
     * @param articleId the article id
     * @param model     the model
     * @return the string
     */
    @GetMapping("article")
    public String new1(@RequestParam("articleId") Integer articleId, Model model){
        model.addAttribute("articleId",articleId);
        return "user_show_single_article";
    }

    /**
     * 在文章页加载相应Id的文章
     *
     * @param articleId the article id
     * @return the article for show
     */
    @GetMapping("articleData")
    @ResponseBody
    public ArticleForShow articleData(Integer articleId){
        Article article = articleService.selectByArticleId(articleId);
        User user = userService.getOneById(article.getAuthorId());
        ArticleForShow articleForShow = ArticleConverter.INSTANCT.conver(article);
        articleForShow.setUserName(user.getUserName());
        return articleForShow;
    }

    /**
     *  在院校对应的文章页加载数据
     *
     * @param collegeName the college name
     * @return the list
     */
    @GetMapping("articlesDataOfCollege")
    @ResponseBody
    public List<ArticleForShow> articleData(String collegeName){
        List<Article> articleList = articleService.selectByCollegeName(collegeName);
        return turnArticleListToShowList(articleList);
    }


    /**
     * 进入添加文章页
     *
     * @param model       the model
     * @param collegeName the college name
     * @return the string
     */
    @GetMapping("toAddArticle")
    public String toAddArticle(Model model,String collegeName) {
        model.addAttribute("collegeName",collegeName);
        return "user_add_article";
    }

    /**
     * 添加文章
     *
     * @param text        the text
     * @param title       the title
     * @param collegeName the college name
     * @param request     the request
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "addArticle",method = RequestMethod.GET)
    public String addArticle(String text, String title, String collegeName, HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        System.out.println("text="+text);
        //  对文章进行审核
        if (text!=null) {
            String conclusion = greenTextUtils.greenText(text);
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null,"文章已提交审核!","文章已提交审核",JOptionPane.PLAIN_MESSAGE);
            if ("error".equals(conclusion)){
                JOptionPane.showMessageDialog(null,"审核平台错误！","审核平台错误",JOptionPane.PLAIN_MESSAGE);
            }
            else {
                if ("合规".equals(conclusion)) {
                    int cnt = articleService.addArticle(user.getUserId(), collegeName, title, text);
                    JOptionPane.showMessageDialog(null,"审核通过，文章已添加!","文章已添加",JOptionPane.PLAIN_MESSAGE);
                } else if ("不合规".equals(conclusion)) {
                    JOptionPane.showMessageDialog(null,"审核不通过，文章不合规!","文章不合规",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        return null;
    }

    /**
     * 批量删除文章
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchArticles")
    public String deleteBatchArticles(Model model,Integer [] ids ){
        int cnt  = articleService.deleteBatchArticles(ids);
        List<Article> articleList= articleService.getAll();
        model.addAttribute("showList", turnArticleListToShowList(articleList));
        return "admin_maintain_article";
    }

    /**
     * 进入文章页
     *
     * @return the string
     */
    @GetMapping("toMyArticle")
    public String toMyArticle() {
        return "user_show_myarticle";
    }

    /**
     * 在文章页加载我的文章数据
     *
     * @param request the request
     * @return the list
     */
    @GetMapping("articlesDataOfMe")
    @ResponseBody
    public List<ArticleForShow> articleData(HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        List<Article> articleList= articleService.selectByUserId(user.getUserId());
        return turnArticleListToShowList(articleList);
    }

    /**
     * 查看其他人发布的文章
     *
     * @param hisName the his name
     * @param model   the model
     * @return the string
     */
    @GetMapping("toHisArticle")
    public String toHisCenter(String hisName,Model model) {
        model.addAttribute("hisName",hisName);
        return "user_show_hisarticle";}

    /**
     * 在文章页加载被查看人的文章数据
     *
     * @param request the request
     * @return the list
     */
    @GetMapping("articlesDataOfHim")
    @ResponseBody
    public List<ArticleForShow> articlesDataOfHim(HttpServletRequest request){
        String hisName = CookieUtil.getCookieHisName(request);
        User him = userService.getByUserName(hisName);
        List<Article> articleList= articleService.selectByUserId(him.getUserId());
        return turnArticleListToShowList(articleList);
    }
    public List<ArticleForShow> turnArticleListToShowList(List<Article> articleList){
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            ArticleForShow articleForShow = ArticleConverter.INSTANCT.conver(article);
            articleForShow.setUserName(userService.getOneById(article.getAuthorId()).getUserName());
            showList.add(articleForShow);
        }
        return showList;
    }

    @PostMapping("addZhiHuArticle")
    @ResponseBody
    public boolean insertArticleFromZhiHuArticleList(@RequestBody AddArticleWebPo addArticleWebPo)
            throws UnsupportedEncodingException {
        //  输入：在txt文件先键入大学名称 然后空格 再贴入相应的知乎文章搜索页动态网页代码
        String strHtml = TxtUtil.txt2String(new File(addArticleWebPo.getPathName()));
        String collegeName = URLDecoder.decode(strHtml.substring(0,strHtml.indexOf(" ")), "utf-8");
        Pattern pattern = Pattern.compile("//[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(strHtml);
        List<String> urlArr = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group().contains("zhuanlan")) {
                String matcherStr = "https:" + matcher.group();
                urlArr.add(matcherStr);
            }
        }
        return catchZhiHuArticle(urlArr,collegeName);
    }

    public boolean catchZhiHuArticle(List<String> paths,String collegeName){
        try {
            int num = 0;
            for (String path:paths) {
                Document document = Jsoup.connect(path).timeout(20000).get();
                String title = document.title();
                System.out.println("正在添加的title is" + title);
                Elements textInElements = document.getElementById("root").getElementsByClass("RichText ztext Post-RichText css-4em6pe");
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
                return true;
            }
            else{
                System.out.println("部分添加失败，失败个数" + (paths.size()-num));
                return false;
            }
        }
        catch (IOException e){
            System.out.println("IO读写异常");
            return false;
        }
    }
}
