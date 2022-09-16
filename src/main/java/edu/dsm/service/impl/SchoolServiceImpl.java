package edu.dsm.service.impl;

import edu.dsm.dao.SchoolMapper;
import edu.dsm.entity.po.School;
import edu.dsm.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type School service.
 */
@Service
public class SchoolServiceImpl extends BaseServiceImpl<SchoolMapper,School> implements SchoolService {
    private final static Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
    /**
     * Get one by id school.
     *
     * @param id the id
     * @return the school
     */
    @Override
    public School getOneById(Integer id){
        return this.getBaseMapper().selectByPrimaryKey(id);
    }

    /**
     * Get all list.
     *
     * @return the list
     */
    @Override
    public List<School> getAll(){return this.getBaseMapper().selectAll();}

    /**
     * Add school int.
     *
     * @param school the school
     * @return the int
     */
    @Override
    public int addSchool(School school){ return this.getBaseMapper().insert(school); }

    /**
     * Update school int.
     *
     * @param school the school
     * @return the int
     */
    @Override
    public int updateSchool(School school){ return this.getBaseMapper().updateByPrimaryKey(school); }

    /**
     * Remove one int.
     *
     * @param sid the sid
     * @return the int
     */
    @Override
    public int removeOne(Integer sid){return this.getBaseMapper().deleteByPrimaryKey(sid);}

    /**
     * Delete batch schools int.
     *
     * @param schoolIds the school ids
     * @return the int
     */
    @Override
    public int deleteBatchSchools(Integer [] schoolIds){return this.getBaseMapper().deleteBatchSchools(schoolIds);}

    /**
     * Select by school name list.
     *
     * @param schoolName the school name
     * @return the list
     */
    @Override
    public List<School> selectBySchoolName(String schoolName){return this.getBaseMapper().selectBySchoolName(schoolName);}

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
    @Override
    public List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
                                      Integer highestScore,String mathSubject, String englishSubject){return
            this.getBaseMapper().selectSchools(province,specialtyName,schoolName,lowestScore,highestScore,mathSubject,englishSubject);}
    @Override
    public List<School> selectTwoSchools(String schoolName1,String schoolName2){
        return this.getBaseMapper().selectTwoSchools(schoolName1,schoolName2);
    }
}
