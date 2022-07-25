package edu.dsm.dao;

import edu.dsm.entity.po.College;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeDao {
    College selectByName(String collegeName);
    List<College> selectAll();
    College selectById(Integer collegeId);
    int addCollege(College college);
    int deleteBatchColleges(Integer [] ids);
}
