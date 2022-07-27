package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Article.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Integer articleId;
    private Integer authorId;
    private String title;
    private String text;
    private LocalDateTime articleDate;
    private String collegeName;
}
