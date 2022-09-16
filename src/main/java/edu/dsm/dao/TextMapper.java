package edu.dsm.dao;

import edu.dsm.entity.po.Text;
import java.util.List;

public interface TextMapper {
    int deleteByPrimaryKey(Integer textId);

    int insert(Text record);

    Text selectByPrimaryKey(Integer textId);

    List<Text> selectAll();

    int updateByPrimaryKey(Text record);
}