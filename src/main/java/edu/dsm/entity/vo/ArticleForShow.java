package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
        private Timestamp articleDate;
        private String collegeName;
}
