package edu.dsm.dao;

import edu.dsm.entity.po.MyLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyLikeMapper extends BaseMapper<MyLike> {
    int likeCollege(Integer userId,Integer collegeId);
    List<MyLike> selectMyLike(Integer userId);
    int deleteMyLike(Integer userId,Integer collegeId);
}
