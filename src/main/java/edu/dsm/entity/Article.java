package edu.dsm.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Article implements Serializable {
    private Integer articleId;
    private Integer authorId;
    private String title;
    private String text;
    private Timestamp articleDate;
    private String collegeName;

    public Article(Integer articleId, Integer authorId, String title, String text, Timestamp articleDate, String collegeName) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.articleDate = articleDate;
        this.collegeName = collegeName;
    }

    public String getCollegeName() {return collegeName;}

    public void setCollegeName(String collegeName) {this.collegeName = collegeName;}

    public Timestamp getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Timestamp articleDate) {
        this.articleDate = articleDate;
    }

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

}
