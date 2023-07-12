package edu.dsm.controller;

import edu.dsm.entity.po.User;
import edu.dsm.service.UserService;
import edu.dsm.util.Md5Util;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * 管理员相关操作
 */
@Api(tags = "管理员功能")
@Controller
public class AdminController {
    @Resource
    private UserService userService;

    /**
     * 进入后台管理登陆页
     *
     * @return the string
     */
    @GetMapping("admin")
    public String toAdminIndex() {return "admin_login";}

    /**
     * 管理员登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return the string
     */
    @PostMapping("adminLogin")
    public String adminLogin(String userName, String password) {
        User user = userService.getAdminByUserName(userName);
        String adminPassword = Md5Util.string2MD5(password);
        if (user == null || !user.getUserPassword().equals(adminPassword)) {
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null,"您输入的用户名不存在或密码错误!","访问数据库失败",JOptionPane.PLAIN_MESSAGE);
            return "admin_login";    //再次返回到登录页面
        }
        else {
            return "admin_index";
        }
    }

    /**
     * 进入后台管理首页
     *
     * @return the string
     */
    @GetMapping("adminMain")
    public String toAdminMain() {return "admin_main";}

    /**
     * 进入用户权限管理页面
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "UserPowerMaintain")
    public String userPowerMaintain(Model model){
        model.addAttribute("userList",userService.getAll());
        return "admin_user_power_maintain";
    }

    /**
     * 根据用户Id将用户设置成管理员/普通用户
     *
     * @param userId the user id
     * @return the string
     */
    @GetMapping("turnToAdmin")        //
    public String turnToAdmin(int userId) {
        User user = userService.getOneById(userId);
        if (!ObjectUtils.isEmpty(user)){
                userService.turnRole(user);
        }
        return "redirect:UserPowerMaintain";
    }

}
