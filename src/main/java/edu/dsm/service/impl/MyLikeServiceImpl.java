package edu.dsm.service.impl;

import edu.dsm.dao.MyLikeMapper;
import edu.dsm.entity.po.MyLike;
import edu.dsm.service.MyLikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type My like service.
 */
@Service
public class MyLikeServiceImpl extends BaseServiceImpl<MyLikeMapper,MyLike> implements MyLikeService {

    private final static Logger logger = LoggerFactory.getLogger(MyLikeServiceImpl.class);
    /**
     * Like college int.
     *
     * @param userId    the user id
     * @param collegeId the college id
     * @return the int
     */
    @Override
    public int likeCollege(Integer userId, Integer collegeId){return this.getBaseMapper().likeCollege(userId,collegeId);}

    /**
     * Select my like list.
     *
     * @param userId the user id
     * @return the list
     */
    @Override
    public List<MyLike> selectMyLike(Integer userId){return this.getBaseMapper().selectMyLike(userId);}

    /**
     * Delete my like int.
     *
     * @param collegeId the college id
     * @return the int
     */
    @Override
    public int deleteMyLike(Integer userId, Integer collegeId){return this.getBaseMapper().deleteMyLike(userId,collegeId);}

    @Override
    public List<MyLike> selectAll() {
        return this.getBaseMapper().selectAll();
    }
}
