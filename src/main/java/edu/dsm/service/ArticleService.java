package edu.dsm.service;

import edu.dsm.dao.ArticleDao;
import edu.dsm.entity.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Article service.
 */
@Service
public class ArticleService{
        @Resource
        private ArticleDao articleDao;

        /**
         * Get all list.
         *
         * @return the list
         */
        public List<Article> getAll(){return articleDao.selectAll();}

        /**
         * Select by article id article.
         *
         * @param articleId the article id
         * @return the article
         */
        public Article selectByArticleId(Integer articleId){return articleDao.selectByArticleId(articleId);}

        /**
         * Delete batch articles int.
         *
         * @param ids the ids
         * @return the int
         */
        public int deleteBatchArticles(Integer [] ids){return articleDao.deleteBatchArticles(ids);}

        /**
         * Select by college name list.
         *
         * @param collegeName the college name
         * @return the list
         */
        public List<Article> selectByCollegeName(String collegeName){return articleDao.selectByCollegeName(collegeName);}

        /**
         * Select by user id list.
         *
         * @param userId the user id
         * @return the list
         */
        public List<Article> selectByUserId(Integer userId){return articleDao.selectByUserId(userId);}

        /**
         * Add article int.
         *
         * @param userId      the user id
         * @param collegeName the college name
         * @param title       the title
         * @param text        the text
         * @return the int
         */
        public int addArticle(Integer userId,String collegeName,String title,String text)
        {return articleDao.addArticle(userId,title,collegeName,text);}
}
