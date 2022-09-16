package edu.dsm.service;

import edu.dsm.entity.po.MyLike;

import java.util.List;

public interface MyLikeService extends BaseService<MyLike>{
    int likeCollege(Integer userId, Integer collegeId);
    List<MyLike> selectMyLike(Integer userId);
    int deleteMyLike(Integer collegeId);
    List<MyLike> selectAll();
}
