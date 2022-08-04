package edu.dsm.converter;

import edu.dsm.entity.po.Article;
import edu.dsm.entity.vo.ArticleForShow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Article converter.
 */
@Mapper
public interface ArticleConverter {
    /**
     * ArticleConverter实例，供其他类调用使用
     */
    ArticleConverter INSTANCT = Mappers.getMapper(ArticleConverter.class);

    /**
     * 将article转换成前台显示的样式
     *
     * @param article 转换前的
     * @return the article for show
     */
    ArticleForShow conver(Article article);
}
