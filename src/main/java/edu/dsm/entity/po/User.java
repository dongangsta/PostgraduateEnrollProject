package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * The type User.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Integer userId;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private String userPassword;
    @Email(message = "邮箱有误！")
    private String email;
    private String phone;
    @NotBlank(message = "地址不能为空！")
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
