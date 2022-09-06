package edu.dsm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type My like.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLike implements Serializable {
    private Integer likeId;
    private Integer userId;
    private Integer collegeId;

}
