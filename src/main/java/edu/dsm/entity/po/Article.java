package edu.dsm.entity.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Article.
 */

public class Article implements Serializable {
    private Integer articleId;
    private Integer authorId;
    private String title;
    private String text;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH-mm-ss"
    )
    private LocalDateTime articleDate;
    private String collegeName;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(LocalDateTime articleDate) {
        this.articleDate = articleDate;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Article(Integer articleId, Integer authorId, String title, String text, LocalDateTime articleDate,
            String collegeName) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.articleDate = articleDate;
        this.collegeName = collegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Article article = (Article) o;
        return Objects.equals(articleId, article.articleId) && Objects.equals(authorId, article.authorId)
                && Objects.equals(title, article.title) && Objects.equals(text, article.text) && Objects.equals(
                articleDate, article.articleDate) && Objects.equals(collegeName, article.collegeName);
    }

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", authorId=" + authorId + ", title='" + title + '\''
                + ", text='" + text + '\'' + ", articleDate=" + articleDate + ", collegeName='" + collegeName + '\''
                + '}';
    }

    public Article() {
    }
}
