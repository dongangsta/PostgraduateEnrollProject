package edu.dsm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type College for recommend.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollegeForRecommend {
    private Integer myScore;
    private String collegeName;
}
