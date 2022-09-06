package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Message.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
