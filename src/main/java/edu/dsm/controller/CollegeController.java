package edu.dsm.controller;

import edu.dsm.entity.College;
import edu.dsm.entity.School;
import edu.dsm.service.ArticleService;
import edu.dsm.service.CollegeService;
import edu.dsm.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CollegeController {
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SchoolService schoolService;

    @GetMapping(value = "CollegeMaintain")     //  进入用户权限管理页面
    public String collegeMaintain(Model model){
        List<College> collegeList = collegeService.getAll();
        model.addAttribute("collegeList",collegeList);
        return "admin_maintain_college";
    }

    @GetMapping("toShowCollege")        // 进入院校主页
    public String toShowCollege(Model model, String collegeName) {
        College college = collegeService.selectByName(collegeName);
        List<School> schoolList = schoolService.selectBySchoolName(collegeName);
        model.addAttribute("college",college);
        model.addAttribute("schoolList",schoolList);
        return "user_show_college";
    }

    @GetMapping("getSchools")        // 取出schools
    @ResponseBody
    public List<School> getSchools(String collegeName) {
        List<School> schoolList = schoolService.selectBySchoolName(collegeName);
        return schoolList;
    }

    @GetMapping("showAddCollege")        // 进入添加院校页面
    public String showAddCollege() {
        return "admin_add_college";
    }

    @ResponseBody
    @RequestMapping(value = "addCollege",method = RequestMethod.GET)   //  添加院校
    public String addCollege(Model model,String collegeName,String collegeArea,String collegeIntro,String collegeNet){
        College college = new College(1,collegeName,collegeArea,collegeIntro,collegeNet);
        if(college!=null){
            int cnt  = collegeService.addCollege(college);
            System.out.println(cnt);
        }
        return null;
    }

    @GetMapping(value = "showCollegeMod")
    public String showCollegeUpadate(Integer collegeId,Model model){
        College college = collegeService.selectById(collegeId);
        model.addAttribute("college",college);
        return "admin_mod_college";
    }

    @PostMapping(value = "deleteBatchColleges")    //  删除院系招生信息
    public String deleteBatchColleges(Model model,Integer [] ids ){
        int cnt  = collegeService.deleteBatchColleges(ids);
        List<College> collegeList = collegeService.getAll();
        model.addAttribute("collegeList",collegeList);
        return "admin_maintain_college";
    }
}
