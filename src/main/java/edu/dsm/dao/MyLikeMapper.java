package edu.dsm.dao;

import edu.dsm.entity.po.MyLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyLikeMapper {
    int likeCollege(Integer userId,Integer collegeId);
    List<MyLike> selectMyLike(Integer userId);
    int deleteMyLike(Integer collegeId);

    List<MyLike> selectAll();
}
