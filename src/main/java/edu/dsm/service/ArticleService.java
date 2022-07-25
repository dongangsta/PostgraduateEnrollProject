package edu.dsm.service;

import edu.dsm.dao.ArticleDao;
import edu.dsm.dao.SchoolDao;
import edu.dsm.entity.Article;
import edu.dsm.entity.School;
import edu.dsm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService{
        @Autowired
        private ArticleDao articleDao;
        public List<Article> getAll(){return articleDao.selectAll();}
        public Article selectByArticleId(Integer articleId){return articleDao.selectByArticleId(articleId);}
        public int deleteBatchArticles(Integer [] ids){return articleDao.deleteBatchArticles(ids);}
        public List<Article> selectByCollegeName(String collegeName){return articleDao.selectByCollegeName(collegeName);}
        public List<Article> selectByUserId(Integer userId){return articleDao.selectByUserId(userId);}
        public int addArticle(Integer userId,String collegeName,String title,String text)
        {return articleDao.addArticle(userId,title,collegeName,text);}
}
