package edu.dsm.entity.po;

import java.io.Serializable;


public class MyLike implements Serializable {
    private Integer likeId;
    private Integer userId;
    private Integer collegeId;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public MyLike(Integer likeId, Integer userId, Integer collegeId) {
        this.likeId = likeId;
        this.userId = userId;
        this.collegeId = collegeId;
    }

    public MyLike() {
    }
}
