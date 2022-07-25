package edu.dsm.service;

import edu.dsm.dao.CollegeDao;
import edu.dsm.entity.po.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    private CollegeDao collegeDao;
    public College selectByName(String collegeName){
        return collegeDao.selectByName(collegeName);
    }
    public List<College> getAll(){return collegeDao.selectAll();}
    public College selectById(Integer collegeId){return collegeDao.selectById(collegeId);}
    public int addCollege(College college){ return collegeDao.addCollege(college);}
    public int deleteBatchColleges(Integer [] collegeIds){return collegeDao.deleteBatchColleges(collegeIds);}
}
