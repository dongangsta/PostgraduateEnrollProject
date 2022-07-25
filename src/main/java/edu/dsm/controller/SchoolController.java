package edu.dsm.controller;

import edu.dsm.entity.po.School;
import edu.dsm.service.SchoolService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

/**
 * The type School controller.
 */
@Controller
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    /**
     * Schools maintain string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "SchoolMaintain")
    public String SchoolsMaintain(Model model){
        model.addAttribute("schoolList",schoolService.getAll());
        return "admin_maintain_school";
    }

    /**
     * To user show school string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("toUserShowSchool")        // 进入用户首页
    public String toUserShowSchool(Model model) {
        model.addAttribute("schoolList",schoolService.getAll());
        return "user_show_school";
    }

    /**
     * Show add school string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("showAddSchool")        // 添加学校信息
    public String showAddSchool(Model model) {
        return "admin_add_school";
    }

    /**
     * Add user string.
     *
     * @param province       the province
     * @param schoolName     the school name
     * @param collegeName    the college name
     * @param specialtyName  the specialty name
     * @param averageScore   the average score
     * @param score21        the score 21
     * @param score20        the score 20
     * @param score19        the score 19
     * @param mathSubject    the math subject
     * @param englishSubject the english subject
     * @param majorSubject   the major subject
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "addSchool",method = RequestMethod.POST)   //  添加院系招生信息
    public String addUser(String province,String schoolName,String collegeName,String specialtyName,Integer averageScore,Integer score21,Integer score20,Integer score19,String mathSubject,String englishSubject,String majorSubject){
        School school = new School(10086,province,schoolName,collegeName,specialtyName,
                averageScore,score21,score20,score19,mathSubject,englishSubject,majorSubject);
        if(!ObjectUtils.isEmpty(school.getSchoolName())){
            int cnt  = schoolService.addSchool(school);
            System.out.println(cnt);
        }else {
            System.setProperty("java.awt.headless", "false");
            JOptionPane.showMessageDialog(null,"院校名为空!","添加失败",JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }

    /**
     * Delete batch schools string.
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchSchools")    //  删除院系招生信息
    public String deleteBatchSchools(Model model,Integer [] ids ){
        int cnt  = schoolService.deleteBatchSchools(ids);
        model.addAttribute("schoolList",schoolService.getAll());
        return "admin_maintain_school";
    }

    /**
     * 进入院校对比查询页
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("toUserShowTwoSchool")
    public String toUserShowTwoSchool(Model model) {
        model.addAttribute("schoolList",schoolService.getAll());
        return "user_show_two_school";
    }

    /**
     * Compare two schools string.
     *
     * @param model   the model
     * @param school1 the school 1
     * @param school2 the school 2
     * @return the string
     */
    @PostMapping (value ="compareTwoSchools")        // 对比查询
    public String compareTwoSchools(Model model,String school1,String school2) {
        model.addAttribute("schoolList",schoolService.selectTwoSchools(school1,school2));
        return "user_show_two_school";
    }

    /**
     * Select schools string.
     *
     * @param model          the model
     * @param province       the province
     * @param specialtyName  the specialty name
     * @param schoolName     the school name
     * @param lowestScore    the lowest score
     * @param highestScore   the highest score
     * @param mathSubject    the math subject
     * @param englishSubject the english subject
     * @return the string
     */
    @PostMapping (value ="selectSchools")        // 详细查询
    public String selectSchools(Model model,String province,String specialtyName,String schoolName,Integer lowestScore,
                                Integer highestScore,String mathSubject, String englishSubject) {
        List<School> schoolList = schoolService.selectSchools(province,specialtyName,schoolName,lowestScore,
                highestScore,mathSubject,englishSubject);
        model.addAttribute("schoolList",schoolList);
        return "user_show_school";
    }

    /**
     * Show school update string.
     *
     * @param schoolId the school id
     * @param model    the model
     * @return the string
     */
    @GetMapping(value = "showSchoolMod")
    public String showSchoolUpdate(Integer schoolId,Model model){
        School school = schoolService.getOneById(schoolId);
        model.addAttribute("school",school);
        return "admin_mod_school";
    }

    /**
     * Modify spot string.
     *
     * @param model  the model
     * @param school the school
     * @return the string
     */
    @PostMapping(value = "updateSchool")
    public String modifySpot(Model model, School school){
        if(school!=null){
            int cnt  = schoolService.updateSchool(school);
            System.out.println(cnt);
        }
        List<School> schoolList = schoolService.getAll();
        model.addAttribute("schoolList", schoolList);
        return "admin_maintain_school";
    }
}
