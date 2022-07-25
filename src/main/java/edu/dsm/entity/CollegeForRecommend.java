package edu.dsm.entity;

public class CollegeForRecommend {
    private Integer myScore;
    private String collegeName;

    public CollegeForRecommend(Integer myScore, String collegeName) {
        this.myScore = myScore;
        this.collegeName = collegeName;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
