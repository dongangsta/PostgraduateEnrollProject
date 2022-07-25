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
     * Select by name college.
     *
     * @param collegeName the college name
     * @return the college
     */
    public College selectByName(String collegeName){
        return collegeDao.selectByName(collegeName);
    }

    /**
     * Get all list.
     *
     * @return the list
     */
    public List<College> getAll(){return collegeDao.selectAll();}

    /**
     * Select by id college.
     *
     * @param collegeId the college id
     * @return the college
     */
    public College selectById(Integer collegeId){return collegeDao.selectById(collegeId);}

    /**
     * Add college int.
     *
     * @param college the college
     * @return the int
     */
    public int addCollege(College college){ return collegeDao.addCollege(college);}

    /**
     * Delete batch colleges int.
     *
     * @param collegeIds the college ids
     * @return the int
     */
    public int deleteBatchColleges(Integer [] collegeIds){return collegeDao.deleteBatchColleges(collegeIds);}
}
