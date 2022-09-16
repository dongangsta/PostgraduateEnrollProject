package edu.dsm.service.impl;

import edu.dsm.dao.MyLikeMapper;
import edu.dsm.entity.po.MyLike;
import edu.dsm.service.MyLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type My like service.
 */
@Service
public class MyLikeServiceImpl implements MyLikeService {
    @Resource
    private MyLikeMapper myLikeMapper;

    /**
     * Like college int.
     *
     * @param userId    the user id
     * @param collegeId the college id
     * @return the int
     */
    @Override
    public int likeCollege(Integer userId, Integer collegeId){return myLikeMapper.likeCollege(userId,collegeId);}

    /**
     * Select my like list.
     *
     * @param userId the user id
     * @return the list
     */
    @Override
    public List<MyLike> selectMyLike(Integer userId){return myLikeMapper.selectMyLike(userId);}

    /**
     * Delete my like int.
     *
     * @param collegeId the college id
     * @return the int
     */
    @Override
    public int deleteMyLike(Integer collegeId){return myLikeMapper.deleteMyLike(collegeId);}

    @Override
    public List<MyLike> selectAll() {
        return myLikeMapper.selectAll();
    }
}
