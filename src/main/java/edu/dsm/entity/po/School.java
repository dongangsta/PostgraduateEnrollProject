package edu.dsm.entity.po;

import java.io.Serializable;


public class School implements Serializable {
    private Integer schoolId;
    private String province;
    private String schoolName;
    private String collegeName;
    private String specialtyName;
    private Integer averageScore;
    private Integer score21;
    private Integer score20;
    private Integer score19;
    private String mathSubject;
    private String englishSubject;
    private String majorSubject;

    public School(String province, String schoolName, String collegeName, String specialtyName, Integer averageScore,
            Integer score21, Integer score20, Integer score19, String mathSubject, String englishSubject,
            String majorSubject) {
        this.province = province;
        this.schoolName = schoolName;
        this.collegeName = collegeName;
        this.specialtyName = specialtyName;
        this.averageScore = averageScore;
        this.score21 = score21;
        this.score20 = score20;
        this.score19 = score19;
        this.mathSubject = mathSubject;
        this.englishSubject = englishSubject;
        this.majorSubject = majorSubject;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getScore21() {
        return score21;
    }

    public void setScore21(Integer score21) {
        this.score21 = score21;
    }

    public Integer getScore20() {
        return score20;
    }

    public void setScore20(Integer score20) {
        this.score20 = score20;
    }

    public Integer getScore19() {
        return score19;
    }

    public void setScore19(Integer score19) {
        this.score19 = score19;
    }

    public String getMathSubject() {
        return mathSubject;
    }

    public void setMathSubject(String mathSubject) {
        this.mathSubject = mathSubject;
    }

    public String getEnglishSubject() {
        return englishSubject;
    }

    public void setEnglishSubject(String englishSubject) {
        this.englishSubject = englishSubject;
    }

    public String getMajorSubject() {
        return majorSubject;
    }

    public void setMajorSubject(String majorSubject) {
        this.majorSubject = majorSubject;
    }

    public School(Integer schoolId, String province, String schoolName, String collegeName, String specialtyName,
            Integer averageScore, Integer score21, Integer score20, Integer score19, String mathSubject,
            String englishSubject, String majorSubject) {
        this.schoolId = schoolId;
        this.province = province;
        this.schoolName = schoolName;
        this.collegeName = collegeName;
        this.specialtyName = specialtyName;
        this.averageScore = averageScore;
        this.score21 = score21;
        this.score20 = score20;
        this.score19 = score19;
        this.mathSubject = mathSubject;
        this.englishSubject = englishSubject;
        this.majorSubject = majorSubject;
    }

    public School() {
    }
}
