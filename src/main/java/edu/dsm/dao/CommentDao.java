package edu.dsm.dao;

import edu.dsm.entity.po.Comment;
import java.util.List;

public interface CommentDao {
    int deleteByPrimaryKey(Integer commentid);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer commentid);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}