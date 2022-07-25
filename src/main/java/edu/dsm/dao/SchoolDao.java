package edu.dsm.dao;

import edu.dsm.entity.po.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {
    School selectById(Integer id);
    List<School> selectAll();
    int addSchool(School school);
    int updateSchool(School school);
    int deleteOne(Integer id);
    int deleteBatchSchools(Integer [] ids);
    List<School> selectBySchoolName(String schoolName);
    List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
                                      Integer highestScore,String mathSubject, String englishSubject);
    List<School> selectTwoSchools(String schoolName1,String schoolName2);
}
