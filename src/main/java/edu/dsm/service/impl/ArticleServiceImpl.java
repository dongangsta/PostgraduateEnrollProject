package edu.dsm.service.impl;

import edu.dsm.dao.ArticleMapper;
import edu.dsm.entity.po.Article;
import edu.dsm.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Article service.
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper,Article> implements ArticleService {

        /**
         * Get all list.
         *
         * @return the list
         */
        @Override
        public List<Article> getAll(){return this.getBaseMapper().selectAll();}

        /**
         * Select by article id article.
         *
         * @param articleId the article id
         * @return the article
         */
        @Override
        public Article selectByArticleId(Integer articleId){return this.getBaseMapper().selectByPrimaryKey(articleId);}

        /**
         * Delete batch articles int.
         *
         * @param ids the ids
         * @return the int
         */
        @Override
        public int deleteBatchArticles(Integer [] ids){return this.getBaseMapper().deleteBatch(ids);}

        /**
         * 通过大学名称查找文章列表
         *
         * @param collegeName 大学名称
         * @return the list
         */
        @Override
        public List<Article> selectByCollegeName(String collegeName){return this.getBaseMapper().selectByCollegeName(collegeName);}

        /**
         * 通过作者userId查找文章列表
         *
         * @param userId 作者userId
         * @return the list
         */
        @Override
        public List<Article> selectByUserId(Integer userId){return this.getBaseMapper().selectByUserId(userId);}

        /**
         * 添加一篇文章
         *
         * @param userId      the user id
         * @param collegeName the college name
         * @param title       the title
         * @param text        the text
         * @return the int
         */
        @Override
        public int addArticle(Integer userId,String collegeName,String title,String text)
        {return this.getBaseMapper().addArticle(userId,title,collegeName,text);}
}
