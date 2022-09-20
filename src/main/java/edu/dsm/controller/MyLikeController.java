package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.MyLike;
import edu.dsm.entity.po.User;
import edu.dsm.service.CollegeService;
import edu.dsm.service.impl.MyLikeServiceImpl;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import edu.dsm.util.JOptionPaneUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The type My like controller.
 */
@Api(tags = "意向院校管理")
@Controller
public class MyLikeController {
    @Resource
    private MyLikeServiceImpl myLikeService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private UserService userService;

    /**
     * 添加意向院校
     *
     * @param model       the model
     * @param collegeName the college name
     * @param request     the request
     * @return the string
     */
    @GetMapping("likeCollege")
    public String likeCollege(Model model, String collegeName, HttpServletRequest request) {
        College college = collegeService.selectByName(collegeName);
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        int cnt = myLikeService.likeCollege(user.getUserId(),college.getCollegeId());
        if (cnt == 1) {
            JOptionPaneUtil.Popup("系统提醒","院校已添加至意向！");
        }else if(cnt == 0){
            JOptionPaneUtil.Popup("系统提醒","您已添加过该意向院校！");
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
    @GetMapping("myLikeList")
    public String myLikeList(Model model,HttpServletRequest request) {
        model.addAttribute("collegeList",extractCollege(request));
        return "user_show_likelist";}

    /**
     * 删除我的意向
     *
     * @param model     the model
     * @param collegeId the college id
     * @param request   the request
     * @return the string
     */
    @GetMapping(value = "deleteMyLike")
    public String deleteMyLike(Model model,Integer collegeId,HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        myLikeService.deleteMyLike(user.getUserId(),collegeId);
        model.addAttribute("collegeList",extractCollege(request));
        return "user_show_likelist";
    }

    /**
     * 抽取通过喜欢列表获取院校列表方法
     *
     * @param request the request
     * @return the list
     */
    public List<College> extractCollege(HttpServletRequest request){
        String userName = CookieUtil.getCookieUserName(request);
        User user = userService.getByUserName(userName);
        List<College> collegeList =new ArrayList<>();
        List<MyLike> myLikeList = myLikeService.selectMyLike(user.getUserId());
        for (MyLike myLike:myLikeList){
            College college = collegeService.selectById(myLike.getCollegeId());
            collegeList.add(college);
        }
        return collegeList;
    }
}
