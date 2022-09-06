package edu.dsm.service.impl;

import edu.dsm.dao.MessageDao;
import edu.dsm.entity.po.Message;
import edu.dsm.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Message service.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    /**
     * Select by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    @Override
    public List<Message> selectByUserId(Integer userId){return messageDao.selectByUserId(userId);}

    /**
     * Add message int.
     *
     * @param message the message
     * @return the int
     */
    @Override
    public int addMessage(Message message){return messageDao.addMessage(message);}

    @Override public List<Message> getAll() {
        return messageDao.getAll();
    }
}
