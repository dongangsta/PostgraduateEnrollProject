package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type User.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String email;
    private String phone;
    private String area;
    private Integer adminOrNot;
    private Integer predict;

    /**
     * Instantiates a new User.
     *
     * @param userId       the user id
     * @param userName     the user name
     * @param userPassword the user password
     * @param email        the email
     * @param phone        the phone
     * @param area         the area
     * @param adminOrNot   the admin or not
     */
    public User(Integer userId, String userName, String userPassword, String email, String phone, String area, Integer adminOrNot) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
        this.area = area;
        this.adminOrNot = adminOrNot;
    }

}
