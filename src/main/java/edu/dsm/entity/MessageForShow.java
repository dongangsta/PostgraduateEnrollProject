package edu.dsm.entity;

import java.sql.Timestamp;

public class MessageForShow {
    public MessageForShow(String user, String reader, String message, Timestamp messageDate) {
        this.user = user;
        this.reader = reader;
        this.message = message;
        this.messageDate = messageDate;
    }

    private String user;
    private String reader;
    private String message;
    private Timestamp messageDate;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
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
