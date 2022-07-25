package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.User;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import edu.dsm.util.Md5;
import edu.dsm.util.Recommend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * To index string.
     *
     * @return the string
     */
    @GetMapping("/")        // 进入首页
    public String toIndex() {
        return "user_login";
    }

    /**
     * Login string.
     *
     * @param model        the model
     * @param userName     the user name
     * @param userPassword the user password
     * @param response     the response
     * @return the string
     */
    @RequestMapping("loginJudge")   //  登录判断
    public String login(Model model, @RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, HttpServletResponse response) {
        User user = userService.getByUserName(userName);
        Md5 md5 = new Md5();
        userPassword = md5.string2MD5(userPassword);
        if (user==null|| !user.getUserPassword().equals(userPassword)){
            System.out.println("账号密码不正确");
            }
        Cookie cookie1 = new Cookie("userName", userName.trim());
        cookie1.setMaxAge(120 * 60);// 设置为120min
        cookie1.setPath("/");
        System.out.println("==============已添加===============");
        response.addCookie(cookie1);
        return "user_index";
    }

    /**
     * To user index string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("toUserIndex")        // 进入用户首页
    public String toUserIndex(Model model,HttpServletRequest request) {
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        model.addAttribute("user",user);
        return "user_direct";}


    /**
     * Users maintain string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "UserMaintain")     //  进入用户管理页面
    public String UsersMaintain(Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("userList",userList);
        return "admin_user_maintain";
    }

    /**
     * Register string.
     *
     * @return the string
     */
    @GetMapping("register")        // 进入注册页面
    public String register() {return "user_register";}


    /**
     * Re password string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @GetMapping("rePassword")        // 重置密码
    public String rePassword(Model model,Integer userId) {
        System.setProperty("java.awt.headless", "false");
        JOptionPane.showMessageDialog(null,"密码已重置为11111111!","密码已重置",JOptionPane.PLAIN_MESSAGE);
        int cnt = userService.rePassword(userId);
        List<User> userList = userService.getAll();
        model.addAttribute("userList",userList);
        return "admin_user_maintain";}


    /**
     * Show add user string.
     *
     * @return the string
     */
    @GetMapping("showAddUser")        // 进入添加用户页面
    public String showAddUser() {
        return "admin_add_user";
    }

    /**
     * Add user string.
     *
     * @param model        the model
     * @param userName     the user name
     * @param userPassword the user password
     * @param email        the email
     * @param phone        the phone
     * @param area         the area
     * @param adminOrNot   the admin or not
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "addUser",method = RequestMethod.GET)   //  添加用户
    public String addUser(Model model,String userName,String userPassword,String email,String phone,String area,Integer adminOrNot){
        User user = new User(10086,userName,userPassword,email,phone,area,adminOrNot);
        if(userName != null){
            int cnt  = userService.addUser(user);
            System.out.println(cnt);
        }
        return null;
    }

    /**
     * Delete batch users string.
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchUsers")    //  删除用户
    public String deleteBatchUsers(Model model,Integer [] ids ){
        int cnt  = userService.deleteBatchUsers(ids);
        List<User> userList= userService.getAll();
        model.addAttribute("userList",userList);
        return "admin_user_maintain";
    }

    /**
     * Show spot mod string.
     *
     * @param userId the user id
     * @param model  the model
     * @return the string
     */
    @GetMapping(value = "showUserUpadate")
    public String showSpotMod(Integer userId,Model model){
        User user = userService.getOneById(userId);
        model.addAttribute("user",user);
        return "admin_mod_user";
    }

    /**
     * Modify spot string.
     *
     * @param model the model
     * @param user  the user
     * @return the string
     */
    @PostMapping(value = "updateUser")
    public String modifySpot(Model model,User user){
        if(user!=null){
            int cnt  = userService.updateUser(user);
            System.out.println(cnt);
        }
        List<User> userList = userService.getAll();
        model.addAttribute("userList",userList);
        return "admin_user_maintain";
    }

    /**
     * Show user upadate user string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "showUserUpadateUser")
    public String showUserUpadateUser(Model model,HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        model.addAttribute("user",user);
        return "user_mod_user";
    }

    /**
     * User update user string.
     *
     * @param model the model
     * @param user  the user
     * @return the string
     */
    @PostMapping(value = "userUpdateUser")
    public String userUpdateUser(Model model,User user){
        if(user!=null){
            int cnt  = userService.updateUser(user);
            System.out.println(cnt);
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null,"信息修改成功!","信息修改成功",JOptionPane.PLAIN_MESSAGE);
        }
        User u = userService.getByUserName(user.getUserName());
        model.addAttribute("user",u);
        return "user_mod_user";
    }


    /**
     * Register string.
     *
     * @param userName     the user name
     * @param userPassword the user password
     * @param email        the email
     * @param phone        the phone
     * @param area         the area
     * @return the string
     */
    @PostMapping("register")    //  用户注册
    public String register(String userName,String userPassword,String email,String phone,String area) {
        Md5 md5 = new Md5();
        User user = new User(1,userName,md5.string2MD5(userPassword),email,phone,area,0);
        int resultCount = userService.addUser(user);
        return "user_login";
    }

    /**
     * User center string.
     *
     * @return the string
     */
    @GetMapping("userCenter")        // 进入个人中心
    public String userCenter() {return "user_center";}

    /**
     * To his center string.
     *
     * @param userName the user name
     * @param model    the model
     * @param response the response
     * @return the string
     */
    @GetMapping("toHisCenter")        // 进入其他人的个人中心
    public String toHisCenter(String userName,Model model,HttpServletResponse response) {
        Cookie cookie1 = new Cookie("hisName", userName.trim());
        cookie1.setMaxAge(120 * 60);// 设置为120min
        cookie1.setPath("/");
        System.out.println("==============已添加===============");
        response.addCookie(cookie1);
        model.addAttribute("hisName",userName);
        return "user_see_center";}

    /**
     * Test string.
     *
     * @param model   the model
     * @param request the request
     * @param predict the predict
     * @return the string
     */
    @GetMapping("showRecommend")        // 院校推荐
    public String Test(Model model,HttpServletRequest request,Integer predict) {
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
     * To recommend string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("toRecommend")        // 进入其他人的个人中心
    public String toRecommend(Model model) {
        return "user_show_recommend";}


}
