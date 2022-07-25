package edu.dsm.dao;

import edu.dsm.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleDao {
    public List<Article> selectAll();
    public Article selectByArticleId(Integer articleId);
    public int deleteBatchArticles(Integer [] ids);
    public List<Article> selectByCollegeName(String collegeName);
    public List<Article> selectByUserId(Integer userId);
    public int addArticle(Integer userId,String title,String collegeName,String text);
}
