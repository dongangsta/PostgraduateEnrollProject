package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The type Message for show.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageForShow {

    private String user;
    private String reader;
    private String message;
    private LocalDateTime messageDate;

}
