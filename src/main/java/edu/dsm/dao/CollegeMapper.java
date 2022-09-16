package edu.dsm.dao;

import edu.dsm.entity.po.College;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeMapper extends BaseMapper<College>{
    College selectByName(String collegeName);
    int deleteBatchColleges(Integer [] ids);
}
