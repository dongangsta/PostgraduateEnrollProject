package edu.dsm.controller;

import edu.dsm.entity.User;
import edu.dsm.service.UserService;
import edu.dsm.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("admin")        // 进入后台管理登陆页
    public String toAdminIndex() {
        return "admin_login";
    }

    @PostMapping("adminLogin")       // 验证管理员身份
    public String adminLogin(String userName, String password) {
        User user = userService.getAdminByUserName(userName);
        Md5 md5 = new Md5();
        String adminPassword = md5.string2MD5(password);
        if (user==null||user.getUserPassword().equals(adminPassword) != true) {
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null,"您输入的用户名不存在或密码错误!","访问数据库失败",JOptionPane.PLAIN_MESSAGE);
            return "admin_login";    //再次返回到登录页面
        }
        else {
            return "admin_index";
        }
    }

    @GetMapping("adminMain")        // 进入后台管理首页
    public String toAdminMain() {
        return "admin_main";
    }

    @GetMapping(value = "UserPowerMaintain")     //  进入用户权限管理页面
    public String UserPowerMaintain(Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("userList",userList);
        return "admin_user_power_maintain";
    }

    @GetMapping("turnToAdmin")        // 设置成管理员
    public String turnToAdmin(int userId) {
        User user = userService.getOneById(userId);
        if (user != null){
            if (user.getAdminOrNot()!=1) {int cnt = userService.turnToAdmin(userId);}
            else  {int cnt = userService.turnToUser(userId);}
        }
        return "redirect:UserPowerMaintain";
    }


}
