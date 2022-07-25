package edu.dsm.dao;

import edu.dsm.entity.po.College;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeDao {
    public College selectByName(String collegeName);
    public List<College> selectAll();
    public College selectById(Integer collegeId);
    public int addCollege(College college);
    public int deleteBatchColleges(Integer [] ids);
}
