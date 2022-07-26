package edu.dsm.controller;

import edu.dsm.entity.po.Message;
import edu.dsm.entity.po.User;
import edu.dsm.entity.vo.MessageForShow;
import edu.dsm.service.impl.MessageServiceImpl;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 留言相关功能
 */
@Api(tags = "留言管理")
@Controller
public class MessageController {
    @Resource
    private MessageServiceImpl messageService;
    @Resource
    private UserService userService;

    /**
     * 查看留言板
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("toShowMyMessage")
    public String toShowMessage(Model model, HttpServletRequest request) {
        String userName = CookieUtil.getCookieUserName(request);
        User me = userService.getByUserName(userName);
        List<Message> mList = messageService.selectByUserId(me.getUserId());
        model.addAttribute("messageList",extractMessageList(mList));
        return "user_show_mymessage";
    }

    /**
     * 进入留言页面
     *
     * @return the string
     */
    @GetMapping("toAddMessage") //
    public String toAddMessage(){
        return "user_add_message";
    }

    /**
     * 添加留言
     *
     * @param model   the model
     * @param request the request
     * @param text    the text
     * @return the string
     */
    @GetMapping("addMessage")
    public String addMessage(Model model, HttpServletRequest request,String text) {
        String myName = CookieUtil.getCookieUserName(request);
        String hisName = CookieUtil.getCookieHisName(request);
        User me = userService.getByUserName(myName);
        User him = userService.getByUserName(hisName);
        Message m = new Message(him.getUserId(),me.getUserId(),text);
        messageService.addMessage(m);
        List<Message> hisList = messageService.selectByUserId(him.getUserId());
        model.addAttribute("messageList",extractMessageList(hisList));
        return "user_show_message";
    }

    /**
     * 查看其他人的留言板
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("toShowHisMessage")
    public String toShowHisssage(Model model, HttpServletRequest request) {
        String hisName = CookieUtil.getCookieHisName(request);
        User him = userService.getByUserName(hisName);
        List<Message> mList = messageService.selectByUserId(him.getUserId());
        model.addAttribute("messageList",extractMessageList(mList));
        return "user_show_message";
    }

    public List<MessageForShow> extractMessageList(List<Message> mList){
        List<MessageForShow> messageList = new ArrayList<>();
        for (Message message:mList){
            User user = userService.getOneById(message.getUserId());
            User reader = userService.getOneById(message.getReaderId());
            MessageForShow messageForShow = new MessageForShow(user.getUserName(),reader.getUserName(),message.getMessage(),message.getMessageDate());
            messageList.add(messageForShow);
        }
        return messageList;
    }

}
