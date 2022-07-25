package edu.dsm.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ArticleForShow {
        private Integer articleId;
        private String userName;
        private String title;
        private String text;
        private Timestamp articleDate;
        private String collegeName;

    public ArticleForShow(Integer articleId, String userName, String title, String text, Timestamp articleDate, String collegeName) {
        this.articleId = articleId;
        this.userName = userName;
        this.title = title;
        this.text = text;
        this.articleDate = articleDate;
        this.collegeName = collegeName;
    }

    public Integer getArticleId() {return articleId;}

    public void setArticleId(Integer articleId) {this.articleId = articleId;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public Timestamp getArticleDate() {return articleDate;}

    public void setArticleDate(Timestamp articleDate) {this.articleDate = articleDate;}

    public String getCollegeName() {return collegeName;}

    public void setCollegeName(String collegeName) {this.collegeName = collegeName;}
}
