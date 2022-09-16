package edu.dsm.service.impl;

import edu.dsm.dao.CollegeMapper;
import edu.dsm.entity.po.College;
import edu.dsm.service.CollegeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type College service.
 */
@Service
public class CollegeServiceImpl extends BaseServiceImpl<CollegeMapper, College> implements CollegeService {

    /**
     * 通过大学名称查找大学相关信息
     *
     * @param collegeName the college name
     * @return the college
     */
    @Override
    public College selectByName(String collegeName){
        return this.getBaseMapper().selectByName(collegeName);
    }

    /**
     * 获取所有大学列表
     *
     * @return the list
     */
    @Override
    public List<College> getAll(){return this.getBaseMapper().selectAll();}

    /**
     * 通过大学Id查找大学
     *
     * @param collegeId the college id
     * @return the college
     */
    @Override
    public College selectById(Integer collegeId){return this.getBaseMapper().selectByPrimaryKey(collegeId);}

    /**
     * 添加一条大学信息
     *
     * @param college the college
     * @return the int
     */
    @Override
    public int addCollege(College college){ return this.getBaseMapper().updateByPrimaryKey(college);}

    /**
     * 批量删除大学信息
     *
     * @param collegeIds the college ids
     * @return the int
     */
    @Override
    public int deleteBatchColleges(Integer [] collegeIds){return this.getBaseMapper().deleteBatch(collegeIds);}
}
