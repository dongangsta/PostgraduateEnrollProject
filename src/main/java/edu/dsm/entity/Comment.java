package edu.dsm.entity;

public class Comment {
    private Integer commentId;
    private Integer authorId;
    private Integer userId;
    private String message;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment(Integer commentId, Integer authorId, Integer userId, String message) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.userId = userId;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", authorId=" + authorId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
