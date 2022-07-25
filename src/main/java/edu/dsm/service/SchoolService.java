package edu.dsm.service;

import edu.dsm.dao.SchoolDao;
import edu.dsm.entity.po.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public School getOneById(Integer id){
        return schoolDao.selectById(id);
    }
    public List<School> getAll(){return schoolDao.selectAll();}
    // List<School> getByRetrieval(School query){return schoolDao.selectByRetrieval(query);}
    public int addSchool(School school){ return schoolDao.addSchool(school); }
    public int updateSchool(School school){ return schoolDao.updateSchool(school); }
    public int removeOne(Integer sid){return schoolDao.deleteOne(sid);}
    public int deleteBatchSchools(Integer [] schoolIds){return schoolDao.deleteBatchSchools(schoolIds);}
    public List<School> selectBySchoolName(String schoolName){return schoolDao.selectBySchoolName(schoolName);}
    public List<School> selectSchools(String province,String specialtyName,String schoolName,Integer lowestScore,
                                      Integer highestScore,String mathSubject, String englishSubject){return
            schoolDao.selectSchools(province,specialtyName,schoolName,lowestScore,highestScore,mathSubject,englishSubject);}
}
