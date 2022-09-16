package edu.dsm.entity.po;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type College.
 */

public class College implements Serializable {
    private Integer collegeId;
    private String collegeName;
    private String collegeArea;
    private String collegeIntro;
    private String collegeNet;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        College college = (College) o;
        return Objects.equals(collegeId, college.collegeId) && Objects.equals(collegeName, college.collegeName) && Objects.equals(collegeArea, college.collegeArea) && Objects.equals(collegeIntro, college.collegeIntro) && Objects.equals(collegeNet, college.collegeNet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeName, collegeArea, collegeIntro, collegeNet);
    }

    public College(String collegeName, String collegeArea, String collegeIntro, String collegeNet) {
        this.collegeName = collegeName;
        this.collegeArea = collegeArea;
        this.collegeIntro = collegeIntro;
        this.collegeNet = collegeNet;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeArea() {
        return collegeArea;
    }

    public void setCollegeArea(String collegeArea) {
        this.collegeArea = collegeArea;
    }

    public String getCollegeIntro() {
        return collegeIntro;
    }

    public void setCollegeIntro(String collegeIntro) {
        this.collegeIntro = collegeIntro;
    }

    public String getCollegeNet() {
        return collegeNet;
    }

    public void setCollegeNet(String collegeNet) {
        this.collegeNet = collegeNet;
    }

    public College(Integer collegeId, String collegeName, String collegeArea, String collegeIntro, String collegeNet) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.collegeArea = collegeArea;
        this.collegeIntro = collegeIntro;
        this.collegeNet = collegeNet;
    }

    public College() {
    }
}
