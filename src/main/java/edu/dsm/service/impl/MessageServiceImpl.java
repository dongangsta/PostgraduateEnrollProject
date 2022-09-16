package edu.dsm.service.impl;

import edu.dsm.dao.MessageMapper;
import edu.dsm.entity.po.Message;
import edu.dsm.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Message service.
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageMapper,Message> implements MessageService {
    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    /**
     * Select by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    @Override
    public List<Message> selectByUserId(Integer userId){return this.getBaseMapper().selectByUserId(userId);}

    /**
     * Add message int.
     *
     * @param message the message
     * @return the int
     */
    @Override
    public int addMessage(Message message){return this.getBaseMapper().insert(message);}

    @Override public List<Message> getAll() {
        return this.getBaseMapper().selectAll();
    }
}
