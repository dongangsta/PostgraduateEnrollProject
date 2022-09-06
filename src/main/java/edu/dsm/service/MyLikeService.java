package edu.dsm.service;

import edu.dsm.entity.po.MyLike;

import java.util.List;

public interface MyLikeService {
    int likeCollege(Integer userId, Integer collegeId);
    List<MyLike> selectMyLike(Integer userId);
    int deleteMyLike(Integer collegeId);
    List<MyLike> selectAll();
}
