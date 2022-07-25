package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type My like.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLike {
    private Integer likeId;
    private Integer userId;
    private Integer collegeId;

}
