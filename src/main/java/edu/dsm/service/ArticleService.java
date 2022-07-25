package edu.dsm.service;

import edu.dsm.dao.ArticleDao;
import edu.dsm.entity.po.Article;
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
         * 通过大学名称查找文章列表
         *
         * @param collegeName 大学名称
         * @return the list
         */
        public List<Article> selectByCollegeName(String collegeName){return articleDao.selectByCollegeName(collegeName);}

        /**
         * 通过作者userId查找文章列表
         *
         * @param userId 作者userId
         * @return the list
         */
        public List<Article> selectByUserId(Integer userId){return articleDao.selectByUserId(userId);}

        /**
         * 添加一篇文章
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
