package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.School;
import edu.dsm.service.CollegeService;
import edu.dsm.service.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

/**
 * The type College controller.
 */
@Controller
public class CollegeController {
    @Resource
    private CollegeService collegeService;
    @Resource
    private SchoolService schoolService;

    /**
     * College maintain string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "CollegeMaintain")     //  进入用户权限管理页面
    public String collegeMaintain(Model model){
        List<College> collegeList = collegeService.getAll();
        model.addAttribute("collegeList",collegeList);
        return "admin_maintain_college";
    }

    /**
     * To show college string.
     *
     * @param model       the model
     * @param collegeName the college name
     * @return the string
     */
    @GetMapping("toShowCollege")        // 进入院校主页
    public String toShowCollege(Model model, String collegeName) {
        College college = collegeService.selectByName(collegeName);
        List<School> schoolList = schoolService.selectBySchoolName(collegeName);
        model.addAttribute("college",college);
        model.addAttribute("schoolList",schoolList);
        return "user_show_college";
    }

    /**
     * 根据学校名称返回相应的院系json
     *
     * @param collegeName 学校名称
     * @return the schools
     */
    @GetMapping("getSchools")
    @ResponseBody
    public List<School> getSchools(String collegeName) {
        return schoolService.selectBySchoolName(collegeName);
    }

    /**
     * Show add college string.
     *
     * @return the string
     */
    @GetMapping("showAddCollege")        // 进入添加院校页面
    public String showAddCollege() {
        return "admin_add_college";
    }

    /**
     * Add college string.
     *
     * @param collegeName  the college name
     * @param collegeArea  the college area
     * @param collegeIntro the college intro
     * @param collegeNet   the college net
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "addCollege",method = RequestMethod.GET)   //  添加院校
    public String addCollege(String collegeName,String collegeArea,String collegeIntro,String collegeNet){
        College college = new College(1,collegeName,collegeArea,collegeIntro,collegeNet);
        if (!ObjectUtils.isEmpty(college.getCollegeName())){
            int cnt  = collegeService.addCollege(college);
            System.out.println(cnt);
        }else {
            JOptionPane.showMessageDialog(null,"院校名为空!","添加失败",JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }

    /**
     * Show college upadate string.
     *
     * @param collegeId the college id
     * @param model     the model
     * @return the string
     */
    @GetMapping(value = "showCollegeMod")
    public String showCollegeUpadate(Integer collegeId,Model model){
        College college = collegeService.selectById(collegeId);
        model.addAttribute("college",college);
        return "admin_mod_college";
    }

    /**
     * Delete batch colleges string.
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchColleges")    //  删除院系招生信息
    public String deleteBatchColleges(Model model,Integer [] ids ){
        int cnt  = collegeService.deleteBatchColleges(ids);
        List<College> collegeList = collegeService.getAll();
        model.addAttribute("collegeList",collegeList);
        return "admin_maintain_college";
    }
}
