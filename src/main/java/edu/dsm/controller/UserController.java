package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.User;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import edu.dsm.util.JOptionPaneUtil;
import edu.dsm.util.Md5Util;
import edu.dsm.util.Recommend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.List;

/**
 * The type User controller.
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 进入首页
     *
     * @return the string
     */
    @GetMapping("/")
    public String toIndex() {
        return "user_login";
    }

    /**
     * 登录判断
     *
     * @param userName     the user name
     * @param userPassword the user password
     * @param response     the response
     * @return the string
     */
    @RequestMapping("loginJudge")   //
    public String login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, HttpServletResponse response) {
        User user = userService.getByUserName(userName);
        userPassword = Md5Util.string2MD5(userPassword);
        if (user==null|| !user.getUserPassword().equals(userPassword)){
            System.out.println("账号密码不正确");
            }
        Cookie cookie1 = new Cookie("userName", userName.trim());
        cookie1.setMaxAge(120 * 60);    // 设置为120min
        cookie1.setPath("/");
        System.out.println("============cookie已添加=============");
        response.addCookie(cookie1);
        return "user_index";
    }

    /**
     * 进入用户首页
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("toUserIndex")
    public String toUserIndex(Model model,HttpServletRequest request) {
        String userName = CookieUtil.getCookieUserName(request);
        model.addAttribute("user",userService.getByUserName(userName));
        return "user_direct";}


    /**
     * 进入用户管理页面
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "UserMaintain")
    public String UsersMaintain(Model model){
        model.addAttribute("userList",userService.getAll());
        return "admin_user_maintain";
    }

    /**
     * 进入注册页面
     *
     * @return the string
     */
    @GetMapping("register")
    public String register() {return "user_register";}


    /**
     * 重置用户密码
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @GetMapping("rePassword")
    public String rePassword(Model model,Integer userId) {
        int cnt = userService.rePassword(userId);
        if (cnt == 1){
            JOptionPaneUtil.Popup("密码已重置","密码已重置为11111111!");
        }
        else {
            JOptionPaneUtil.Popup("密码重置失败","密码重置失败，请重试!");
        }
        model.addAttribute("userList",userService.getAll());
        return "admin_user_maintain";}


    /**
     * 进入添加用户页面
     *
     * @return the string
     */
    @GetMapping("showAddUser")
    public String showAddUser() {
        return "admin_add_user";
    }

    /**
     * 添加用户
     *
     * @param userName     用户名
     * @param userPassword 用户密码
     * @param email        用户邮箱
     * @param phone        用户手机号码
     * @param area         所在地区
     * @param adminOrNot   是否为管理员（1：是 0：不是）
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "addUser",method = RequestMethod.GET)
    public String addUser(String userName,String userPassword,String email,String phone,String area,Integer adminOrNot){
        User user = new User(userName,userPassword,email,phone,area,adminOrNot);
        if(userName != null){
            int cnt  = userService.addUser(user);
            System.out.println(cnt);
        }
        return null;
    }

    /**
     * 批量删除用户
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchUsers")
    public String deleteBatchUsers(Model model,Integer [] ids ){
        int cnt  = userService.deleteBatchUsers(ids);
        model.addAttribute("userList",userService.getAll());
        return "admin_user_maintain";
    }

    /**
     * 进入管理员更新用户信息页
     *
     * @param userId the user id
     * @param model  the model
     * @return the string
     */
    @GetMapping(value = "showUserUpadate")
    public String showSpotMod(Integer userId,Model model){
        model.addAttribute("user",userService.getOneById(userId));
        return "admin_mod_user";
    }

    /**
     * 修改用户信息
     *
     * @param model the model
     * @param user  the user
     * @return the string
     */
    @PostMapping(value = "updateUser")
    public String updateUser(Model model,@Validated User user){
        if(user!=null){
            int cnt  = userService.updateUser(user);
        }
        model.addAttribute("userList",userService.getAll());
        return "admin_user_maintain";
    }

    /**
     * 进入用户修改个人信息页面
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "showUserUpdateUser")
    public String showUserUpdateUser(Model model,HttpServletRequest request){
        User user = userService.getByUserName(CookieUtil.getCookieUserName(request));
        model.addAttribute("user",user);
        return "user_mod_user";
    }

    /**
     * 用户修改个人信息
     *
     * @param model the model
     * @param user  the user
     * @return the string
     */
    @PostMapping(value = "userUpdateUser")
    public String userUpdateUser(Model model,@Validated User user){
        System.setProperty("java.awt.headless", "false");
        if(user!=null){
            int cnt  = userService.updateUser(user);
            JOptionPane.showMessageDialog(null,"信息修改成功!","信息修改成功",JOptionPane.PLAIN_MESSAGE);
            User u = userService.getByUserName(user.getUserName());
            model.addAttribute("user",u);
        }
        else {
            JOptionPane.showMessageDialog(null,"请检查您输入的信息!","信息修改失败",JOptionPane.PLAIN_MESSAGE);
        }
        return "user_mod_user";
    }


    /**
     * 用户注册
     *
     * @param userName     the user name
     * @param userPassword the user password
     * @param email        the email
     * @param phone        the phone
     * @param area         the area
     * @return the string
     */
    @PostMapping("register")
    public String register(String userName,String userPassword,String email,String phone,String area) {
        User user = new User(userName, Md5Util.string2MD5(userPassword),email,phone,area,0);
        int cnt = userService.addUser(user);
        return "user_login";
    }

    /**
     * 用户进入个人中心
     *
     * @return the string
     */
    @GetMapping("userCenter")
    public String userCenter() {return "user_center";}

    /**
     * 进入其他人的个人中心
     *
     * @param userName the user name
     * @param model    the model
     * @param response the response
     * @return the string
     */
    @GetMapping("toHisCenter")
    public String toHisCenter(String userName,Model model,HttpServletResponse response) {
        Cookie cookie1 = new Cookie("hisName", userName.trim());
        cookie1.setMaxAge(120 * 60);// 设置为120min
        cookie1.setPath("/");
        System.out.println("============cookie已添加=============");
        response.addCookie(cookie1);
        model.addAttribute("hisName",userName);
        return "user_see_center";}

    /**
     * 院校推荐
     *
     * @param model   the model
     * @param request the request
     * @param predict the predict
     * @return the string
     */
    @GetMapping("showRecommend")
    public String showRecommend(Model model,HttpServletRequest request,Integer predict) {
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        int cnt = userService.updatePredict(predict,user.getUserId());
        Recommend recommend = new Recommend();
        List<College> recommendationColleges = recommend.recommend(userName);
        System.out.println("-----------------------");
        System.out.println("推荐结果如下：");
        for (College college : recommendationColleges) {
            System.out.println("学校："+ college.getCollegeName());
        }
        model.addAttribute("collegeList",recommendationColleges);
        return "user_show_recommend";
    }

    /**
     * 进入推荐院校页面
     *
     * @return the string
     */
    @GetMapping("toRecommend")
    public String toRecommend() {
        return "user_show_recommend";}


}
