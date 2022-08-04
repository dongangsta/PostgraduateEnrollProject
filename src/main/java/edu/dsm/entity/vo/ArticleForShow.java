package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Article for show.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleForShow {
        private Integer articleId;
        private String userName;
        private String title;
        private String text;
        private LocalDate articleDate;
        private String collegeName;

        public void setArticleDate(LocalDateTime articleDate) {
                this.articleDate = articleDate.toLocalDate();
        }
}
