package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The type Article.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article implements Serializable {
    private Integer articleId;
    private Integer authorId;
    private String title;
    private String text;
    private Timestamp articleDate;
    private String collegeName;
}
