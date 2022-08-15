package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolTestVo {
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
    private Integer score21sub20;
}
