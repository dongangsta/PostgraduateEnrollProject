package edu.dsm.service;

import edu.dsm.dao.SchoolDao;
import edu.dsm.entity.po.School;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type School service.
 */
@Service
public class SchoolService {

    @Resource
    private SchoolDao schoolDao;

    /**
     * Get one by id school.
     *
     * @param id the id
     * @return the school
     */
    public School getOneById(Integer id){
        return schoolDao.selectById(id);
    }

    /**
     * Get all list.
     *
     * @return the list
     */
    public List<School> getAll(){return schoolDao.selectAll();}

    /**
     * Add school int.
     *
     * @param school the school
     * @return the int
     */
    public int addSchool(School school){ return schoolDao.addSchool(school); }

    /**
     * Update school int.
     *
     * @param school the school
     * @return the int
     */
    public int updateSchool(School school){ return schoolDao.updateSchool(school); }

    /**
     * Remove one int.
     *
     * @param sid the sid
     * @return the int
     */
    public int removeOne(Integer sid){return schoolDao.deleteOne(sid);}

    /**
     * Delete batch schools int.
     *
     * @param schoolIds the school ids
     * @return the int
     */
    public int deleteBatchSchools(Integer [] schoolIds){return schoolDao.deleteBatchSchools(schoolIds);}

    /**
     * Select by school name list.
     *
     * @param schoolName the school name
     * @return the list
     */
    public List<School> selectBySchoolName(String schoolName){return schoolDao.selectBySchoolName(schoolName);}

    /**
     * Select schools list.
     *
     * @param province       the province
     * @param specialtyName  the specialty name
     * @param schoolName     the school name
     * @param lowestScore    the lowest score
     * @param highestScore   the highest score
     * @param mathSubject    the math subject
     * @param englishSubject the english subject
     * @return the list
     */
    public List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
                                      Integer highestScore,String mathSubject, String englishSubject){return
            schoolDao.selectSchools(province,specialtyName,schoolName,lowestScore,highestScore,mathSubject,englishSubject);}
    public List<School> selectTwoSchools(String schoolName1,String schoolName2){
        return schoolDao.selectTwoSchools(schoolName1,schoolName2);
    }
}
