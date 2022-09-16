package edu.dsm.service;

import edu.dsm.entity.po.Message;

import java.util.List;

public interface MessageService extends BaseService<Message>{
    List<Message> selectByUserId(Integer userId);
    int addMessage(Message message);

    List<Message> getAll();
}
