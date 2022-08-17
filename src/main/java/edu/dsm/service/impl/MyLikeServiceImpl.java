package edu.dsm.service.impl;

import edu.dsm.dao.MyLikeDao;
import edu.dsm.entity.po.MyLike;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type My like service.
 */
@Service
public class MyLikeServiceImpl {
    @Resource
    private MyLikeDao myLikeDao;

    /**
     * Like college int.
     *
     * @param userId    the user id
     * @param collegeId the college id
     * @return the int
     */
    public int likeCollege(Integer userId, Integer collegeId){return myLikeDao.likeCollege(userId,collegeId);}

    /**
     * Select my like list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<MyLike> selectMyLike(Integer userId){return myLikeDao.selectMyLike(userId);}

    /**
     * Delete my like int.
     *
     * @param collegeId the college id
     * @return the int
     */
    public int deleteMyLike(Integer collegeId){return myLikeDao.deleteMyLike(collegeId);}
}
