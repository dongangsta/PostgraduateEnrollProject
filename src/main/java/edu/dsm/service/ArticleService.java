package edu.dsm.service;

import edu.dsm.entity.po.Article;

import java.util.List;

public interface ArticleService extends BaseService<Article> {
    List<Article> getAll();
    Article selectByArticleId(Integer articleId);
    int deleteBatchArticles(Integer [] ids);
    List<Article> selectByCollegeName(String collegeName);
    List<Article> selectByUserId(Integer userId);
    int addArticle(Integer userId,String collegeName,String title,String text);
}
