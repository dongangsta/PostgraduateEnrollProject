package edu.dsm.entity;

import java.sql.Timestamp;

public class Message {
    private Integer messageId;
    private Integer userId;
    private Integer readerId;
    private String message;
    private Timestamp messageDate;

    public Message(Integer messageId, Integer userId, Integer readerId, String message, Timestamp messageDate) {
        this.messageId = messageId;
        this.userId = userId;
        this.readerId = readerId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public Message(Integer userId, Integer readerId, String message) {
        this.userId = userId;
        this.readerId = readerId;
        this.message = message;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }
}
