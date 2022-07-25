package edu.dsm.service;

import edu.dsm.dao.CollegeDao;
import edu.dsm.entity.po.College;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type College service.
 */
@Service
public class CollegeService {
    @Resource
    private CollegeDao collegeDao;

    /**
     * 通过大学名称查找大学相关信息
     *
     * @param collegeName the college name
     * @return the college
     */
    public College selectByName(String collegeName){
        return collegeDao.selectByName(collegeName);
    }

    /**
     * 获取所有大学列表
     *
     * @return the list
     */
    public List<College> getAll(){return collegeDao.selectAll();}

    /**
     * 通过大学Id查找大学
     *
     * @param collegeId the college id
     * @return the college
     */
    public College selectById(Integer collegeId){return collegeDao.selectById(collegeId);}

    /**
     * 添加一条大学信息
     *
     * @param college the college
     * @return the int
     */
    public int addCollege(College college){ return collegeDao.addCollege(college);}

    /**
     * 批量删除大学信息
     *
     * @param collegeIds the college ids
     * @return the int
     */
    public int deleteBatchColleges(Integer [] collegeIds){return collegeDao.deleteBatchColleges(collegeIds);}
}
