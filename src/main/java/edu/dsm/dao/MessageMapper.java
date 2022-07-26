package edu.dsm.dao;

import edu.dsm.entity.po.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
    List<Message> selectByUserId(Integer userId);
}
