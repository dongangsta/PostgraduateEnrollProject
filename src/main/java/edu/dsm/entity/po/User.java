package edu.dsm.entity.po;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


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
     * @param userName     the user name
     * @param userPassword the user password
     * @param email        the email
     * @param phone        the phone
     * @param area         the area
     * @param adminOrNot   the admin or not
     */
    public User(String userName, String userPassword, String email, String phone, String area, Integer adminOrNot) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
        this.area = area;
        this.adminOrNot = adminOrNot;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAdminOrNot() {
        return adminOrNot;
    }

    public void setAdminOrNot(Integer adminOrNot) {
        this.adminOrNot = adminOrNot;
    }

    public Integer getPredict() {
        return predict;
    }

    public void setPredict(Integer predict) {
        this.predict = predict;
    }

    public User(Integer userId, String userName, String userPassword, String email, String phone, String area,
            Integer adminOrNot, Integer predict) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
        this.area = area;
        this.adminOrNot = adminOrNot;
        this.predict = predict;
    }

    public User() {
    }
}
