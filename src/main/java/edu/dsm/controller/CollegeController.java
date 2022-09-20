package edu.dsm.controller;

import edu.dsm.entity.po.College;
import edu.dsm.entity.po.School;
import edu.dsm.service.CollegeService;
import edu.dsm.service.SchoolService;
import edu.dsm.util.JOptionPaneUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type College controller.
 */
@Api(tags = "学校管理")
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
        model.addAttribute("collegeList",collegeService.getAll());
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
        model.addAttribute("college",collegeService.selectByName(collegeName));
        model.addAttribute("schoolList",schoolService.selectBySchoolName(collegeName));
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
        College college = new College(collegeName,collegeArea,collegeIntro,collegeNet);
        if (!ObjectUtils.isEmpty(college.getCollegeName())){
            collegeService.addCollege(college);
        }else {
            JOptionPaneUtil.Popup("添加失败","院校名为空!");
        }
        return null;
    }

    /**
     * 进入对应的院校信息修改页面
     *
     * @param collegeId 院校Id
     * @param model     model
     * @return the string
     */
    @GetMapping(value = "showCollegeMod")
    public String showCollegeUpdate(Integer collegeId,Model model){
        model.addAttribute("college",collegeService.selectById(collegeId));
        return "admin_mod_college";
    }

    /**
     * 删除院系招生信息
     *
     * @param model the model
     * @param ids   the ids
     * @return the string
     */
    @PostMapping(value = "deleteBatchColleges")
    public String deleteBatchColleges(Model model,Integer [] ids ){
        collegeService.deleteBatchColleges(ids);
        model.addAttribute("collegeList",collegeService.getAll());
        return "admin_maintain_college";
    }
}
