package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.MyLike;
import edu.dsm.entity.po.User;
import edu.dsm.service.CollegeService;
import edu.dsm.service.MyLikeService;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type My like controller.
 */
@Controller
public class MyLikeController {
    @Resource
    private MyLikeService myLikeService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private UserService userService;

    /**
     * Like college string.
     *
     * @param model       the model
     * @param collegeName the college name
     * @param request     the request
     * @return the string
     */
    @GetMapping("likeCollege")        // 添加意向院校
    public String likeCollege(Model model, String collegeName, HttpServletRequest request) {
        College college = collegeService.selectByName(collegeName);
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        System.out.println(user.getUserName()+"的userId="+user.getUserId());
        int cnt = myLikeService.likeCollege(user.getUserId(),college.getCollegeId());
        if (cnt == 1) {
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null, "院校已添加至意向！", "系统提醒", JOptionPane.PLAIN_MESSAGE);
        }else if(cnt == 0){
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null, "您已添加过该意向院校！", "系统提醒", JOptionPane.PLAIN_MESSAGE);
        }
        model.addAttribute("college",college);
        return "user_show_college";}

    /**
     * 查询我的意向院校
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("myLikeList")        //
    public String myLikeList(Model model,HttpServletRequest request) {
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        List<College> collegeList =new ArrayList<>();
        List<MyLike> myLikeList = myLikeService.selectMyLike(user.getUserId());
        for (MyLike myLike:myLikeList){
            College college = collegeService.selectById(myLike.getCollegeId());
            collegeList.add(college);
        }
        model.addAttribute("collegeList",collegeList);
        return "user_show_likelist";}

    /**
     * Delete batch users string.
     *
     * @param model     the model
     * @param collegeId the college id
     * @param request   the request
     * @return the string
     */
    @GetMapping(value = "deleteMyLike")    //  删除我的意向
    public String deleteBatchUsers(Model model,Integer collegeId,HttpServletRequest request){
        int cnt  = myLikeService.deleteMyLike(collegeId);
        List<User> userList= userService.getAll();
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        List<College> collegeList =new ArrayList<>();
        List<MyLike> myLikeList = myLikeService.selectMyLike(user.getUserId());
        for (MyLike myLike:myLikeList){
            College college = collegeService.selectById(myLike.getCollegeId());
            collegeList.add(college);
        }
        model.addAttribute("collegeList",collegeList);
        return "user_show_likelist";
    }
}
