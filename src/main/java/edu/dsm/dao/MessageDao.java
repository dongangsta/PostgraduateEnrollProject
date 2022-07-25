package edu.dsm.dao;

import edu.dsm.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    public List<Message> selectByUserId(Integer userId);
    public int addMessage(Message message);
}
