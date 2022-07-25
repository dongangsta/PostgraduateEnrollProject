package edu.dsm.service;

import edu.dsm.dao.MessageDao;
import edu.dsm.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    public List<Message> selectByUserId(Integer userId){return messageDao.selectByUserId(userId);}
    public int addMessage(Message message){return messageDao.addMessage(message);}
}
