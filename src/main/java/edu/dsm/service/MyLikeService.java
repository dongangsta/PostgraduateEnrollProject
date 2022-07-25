package edu.dsm.service;

import edu.dsm.dao.MyLikeDao;
import edu.dsm.entity.po.MyLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyLikeService {
    @Autowired
    private MyLikeDao myLikeDao;
    public int likeCollege(Integer userId, Integer collegeId){return myLikeDao.likeCollege(userId,collegeId);}
    public List<MyLike> selectMyLike(Integer userId){return myLikeDao.selectMyLike(userId);}
    public int deleteMyLike(Integer collegeId){return myLikeDao.deleteMyLike(collegeId);}
}
