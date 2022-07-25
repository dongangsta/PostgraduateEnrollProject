package edu.dsm.dao;

import edu.dsm.entity.School;
import edu.dsm.util.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {
    public School selectById(Integer id);
    public List<School> selectAll();
    public int addSchool(School school);
    public int updateSchool(School school);
    public int deleteOne(Integer id);
    public int deleteBatchSchools(Integer [] ids);
    public List<School> selectSchoolByPage(PageUtil pageUtil);
    public List<School> selectBySchoolName(String schoolName);
    public List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
                                      Integer highestScore,String mathSubject, String englishSubject);
}
