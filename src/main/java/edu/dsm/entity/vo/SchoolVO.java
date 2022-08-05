package edu.dsm.entity.vo;

import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolVO implements Serializable, IExcelDataModel, IExcelModel {
    private static final long serialVersionUID = 1L;
    private int rowNum;
    private String errorMsg;
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
}
