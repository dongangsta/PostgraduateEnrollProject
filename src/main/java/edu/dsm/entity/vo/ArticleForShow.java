package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
        @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
        private LocalDateTime articleDate;
        private String collegeName;

//        public void setArticleDate(LocalDateTime articleDate) {
//                this.articleDate = articleDate.toLocalDate();
//        }
}
