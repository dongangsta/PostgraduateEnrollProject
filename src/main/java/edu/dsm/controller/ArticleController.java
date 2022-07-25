package edu.dsm.controller;

import edu.dsm.entity.po.Article;
import edu.dsm.entity.po.User;
import edu.dsm.entity.vo.ArticleForShow;
import edu.dsm.service.ArticleService;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import edu.dsm.util.GreenTextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;
    @Resource
    GreenTextUtils greenTextUtils;

    @GetMapping(value = "ArticleMaintain")
    public String showNews(Model model){
        List<Article> articleList= articleService.getAll();
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            User author = userService.getOneById(article.getAuthorId());
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),author.getUserName(),article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        model.addAttribute("showList", showList);
        return "admin_maintain_article";
    }

    @GetMapping("toShowArticle")        // 进入文章页
    public String toShowArticle(Model model) {
        return "user_show_articles";
    }

    @GetMapping("articlesData")
    @ResponseBody
    public List<ArticleForShow> artilcesData(Model model) {
        List<Article> articleList = articleService.getAll();
        List<ArticleForShow> showList = new ArrayList<>();

        for (Article article:articleList){
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),userService.getOneById(article.getAuthorId()).getUserName(),article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        model.addAttribute("showList", showList);
        return showList;
    }

    @GetMapping("article")  //  进入相应的文章页
    public String new1(@RequestParam("articleId") Integer articleId, Model model){
        model.addAttribute("articleId",articleId);
        return "user_show_single_article";
    }

    @GetMapping("articleData")  //  在文章页加载数据
    @ResponseBody
    public ArticleForShow articleData(Integer articleId){
        Article article = articleService.selectByArticleId(articleId);
        User user = userService.getOneById(article.getAuthorId());
        ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),user.getUserName(),article.getTitle(),
                article.getText(),article.getArticleDate(),article.getCollegeName());
        return articleForShow;
    }

    @GetMapping("articlesDataOfCollege")  //  在文章页加载数据
    @ResponseBody
    public List<ArticleForShow> articleData(Model model,String collegeName){
        List<Article> articleList = articleService.selectByCollegeName(collegeName);
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),"dong1",article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        model.addAttribute("showList", showList);
        return showList;
    }


    @GetMapping("toAddArticle")        // 进入文章页
    public String toAddArticle(Model model,String collegeName) {
        model.addAttribute("collegeName",collegeName);
        return "user_add_article";
    }

    @ResponseBody
    @RequestMapping(value = "addArticle",method = RequestMethod.GET)    //  添加文章
    public String addArticle(String text, String title, String collegeName, HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        System.out.println("text="+text);

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

    @PostMapping(value = "deleteBatchArticles")    //  删除用户
    public String deleteBatchArticles(Model model,Integer [] ids ){
        int cnt  = articleService.deleteBatchArticles(ids);
        List<Article> articleList= articleService.getAll();
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),"dong1",article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        model.addAttribute("showList", showList);
        return "admin_maintain_article";
    }

    @GetMapping("toMyArticle")        // 进入文章页
    public String toMyArticle() {
        return "user_show_myarticle";
    }

    @GetMapping("articlesDataOfMe")  //  在文章页加载数据
    @ResponseBody
    public List<ArticleForShow> articleData(HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        List<Article> articleList= articleService.selectByUserId(user.getUserId());
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),user.getUserName(),article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        return showList;
    }

    @GetMapping("toHisArticle")        // 查看其他人发布的文章
    public String toHisCenter(String hisName,Model model) {
        model.addAttribute("hisName",hisName);
        return "user_show_hisarticle";}

    @GetMapping("articlesDataOfHim")  //  在文章页加载数据
    @ResponseBody
    public List<ArticleForShow> articlesDataOfHim(HttpServletRequest request){
        String hisName = CookieUtil.getCookieHisName(request);
        User him = userService.getByUserName(hisName);
        List<Article> articleList= articleService.selectByUserId(him.getUserId());
        List<ArticleForShow> showList = new ArrayList<>();
        for (Article article:articleList){
            ArticleForShow articleForShow = new ArticleForShow(article.getArticleId(),him.getUserName(),article.getTitle(),article.getText(),article.getArticleDate(),article.getCollegeName());
            showList.add(articleForShow);
        }
        return showList;
    }
}
