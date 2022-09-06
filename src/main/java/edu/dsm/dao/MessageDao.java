package edu.dsm.dao;

import edu.dsm.entity.po.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    List<Message> selectByUserId(Integer userId);
    int addMessage(Message message);

    List<Message> getAll();
}
