package edu.dsm.service;

import edu.dsm.entity.po.College;

import java.util.List;

public interface CollegeService extends BaseService<College>{
    College selectByName(String collegeName);
    List<College> getAll();
    College selectById(Integer collegeId);
    int addCollege(College college);
    int deleteBatchColleges(Integer [] collegeIds);
}
