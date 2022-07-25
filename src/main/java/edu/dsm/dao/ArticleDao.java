package edu.dsm.dao;

import edu.dsm.entity.po.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleDao {
    List<Article> selectAll();
    Article selectByArticleId(Integer articleId);
    int deleteBatchArticles(Integer [] ids);
    List<Article> selectByCollegeName(String collegeName);
    List<Article> selectByUserId(Integer userId);
    int addArticle(Integer userId,String title,String collegeName,String text);
}
