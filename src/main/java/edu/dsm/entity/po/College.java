package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class College implements Serializable {
    private Integer collegeId;
    private String collegeName;
    private String collegeArea;
    private String collegeIntro;
    private String collegeNet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        College college = (College) o;
        return Objects.equals(collegeId, college.collegeId) && Objects.equals(collegeName, college.collegeName) && Objects.equals(collegeArea, college.collegeArea) && Objects.equals(collegeIntro, college.collegeIntro) && Objects.equals(collegeNet, college.collegeNet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeName, collegeArea, collegeIntro, collegeNet);
    }

}
