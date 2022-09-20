package edu.dsm.service;

import edu.dsm.entity.po.School;

import java.util.List;

public interface SchoolService extends BaseService<School>{
    School getOneById(Integer id);
    List<School> getAll();
    int addSchool(School school);
    int updateSchool(School school);
    int removeOne(Integer sid);
    int deleteBatchSchools(Integer [] schoolIds);
    List<School> selectBySchoolName(String schoolName);
    List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
            Integer highestScore,String mathSubject, String englishSubject);
    List<School> selectTwoSchools(String schoolName1,String schoolName2);

    int updateAverageScore(Integer schoolId, Integer averageScore);
}
