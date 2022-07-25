package edu.dsm.dao;

import edu.dsm.entity.po.MyLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyLikeDao {
    public int likeCollege(Integer userId,Integer collegeId);
    public List<MyLike> selectMyLike(Integer userId);
    public int deleteMyLike(Integer collegeId);
}
