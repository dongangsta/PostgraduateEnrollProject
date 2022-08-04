package edu.dsm.converter;

import edu.dsm.entity.po.Article;
import edu.dsm.entity.vo.ArticleForShow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConverter {
    ArticleConverter INSTANCT = Mappers.getMapper(ArticleConverter.class);

    ArticleForShow conver(Article article);
}
