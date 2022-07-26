package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type School.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
