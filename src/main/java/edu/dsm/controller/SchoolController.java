package edu.dsm.controller;

import edu.dsm.entity.po.School;
import edu.dsm.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping(value = "SchoolMaintain")
    public String SchoolsMaintain(Model model){
        List<School> schoolList = schoolService.getAll();
        model.addAttribute("schoolList",schoolList);
        return "admin_maintain_school";
    }

    @GetMapping("toUserShowSchool")        // 进入用户首页
    public String toUserShowSchool(Model model) {
        List<School> schoolList = schoolService.getAll();
        model.addAttribute("schoolList",schoolList);
        return "user_show_school";
    }

    @GetMapping("showAddSchool")        // 添加学校信息
    public String showAddSchool(Model model) {
        return "admin_add_school";
    }

    @ResponseBody
    @RequestMapping(value = "addSchool",method = RequestMethod.POST)   //  添加院系招生信息
    public String addUser(Model model,String province,String schoolName,String collegeName,String specialtyName,Integer averageScore,Integer score21,Integer score20,Integer score19,String mathSubject,String englishSubject,String majorSubject){
        School school = new School(10086,province,schoolName,collegeName,specialtyName,
                averageScore,score21,score20,score19,mathSubject,englishSubject,majorSubject);
        if(school!=null){
            int cnt  = schoolService.addSchool(school);
            System.out.println(cnt);
        }
        return null;
    }

    @PostMapping(value = "deleteBatchSchools")    //  删除院系招生信息
    public String deleteBatchSchools(Model model,Integer [] ids ){
        int cnt  = schoolService.deleteBatchSchools(ids);
        List<School> schoolList = schoolService.getAll();
        model.addAttribute("schoolList",schoolList);
        return "admin_maintain_school";
    }

    @GetMapping("toUserShowTwoSchool")        // 进入用户首页
    public String toUserShowTwoSchool(Model model) {
        List<School> schoolList = schoolService.getAll();
        model.addAttribute("schoolList",schoolList);
        return "user_show_two_school";
    }

    @PostMapping (value ="compareTwoSchools")        // 对比查询
    public String compareTwoSchools(Model model,String school1,String school2) {
        List<School> schoolList = new ArrayList<>();
        List<School> schoolList1 = schoolService.selectBySchoolName(school1);
        List<School> schoolList2 = schoolService.selectBySchoolName(school2);
        for (School school:schoolList1){
            schoolList.add(school);
        }
        for (School school:schoolList2){
            schoolList.add(school);
        }
        model.addAttribute("schoolList",schoolList);
        return "user_show_two_school";
    }

    @PostMapping (value ="selectSchools")        // 详细查询
    public String selectSchools(Model model,String province,String specialtyName,String schoolName,Integer lowestScore,
                                Integer highestScore,String mathSubject, String englishSubject) {
        List<School> schoolList = schoolService.selectSchools(province,specialtyName,schoolName,lowestScore,
                highestScore,mathSubject,englishSubject);
        model.addAttribute("schoolList",schoolList);
        return "user_show_school";
    }

    @GetMapping(value = "showSchoolMod")
    public String showSchoolUpdate(Integer schoolId,Model model){
        School school = schoolService.getOneById(schoolId);
        model.addAttribute("school",school);
        return "admin_mod_school";
    }

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
