package edu.dsm.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private Integer messageId;
    private Integer userId;
    private Integer readerId;
    private String message;
    private LocalDateTime messageDate;

    /**
     * Instantiates a new Message.
     *
     * @param userId   the user id
     * @param readerId the reader id
     * @param message  the message
     */
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

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public Message() {
    }

    public Message(Integer messageId, Integer userId, Integer readerId, String message, LocalDateTime messageDate) {
        this.messageId = messageId;
        this.userId = userId;
        this.readerId = readerId;
        this.message = message;
        this.messageDate = messageDate;
    }
}
