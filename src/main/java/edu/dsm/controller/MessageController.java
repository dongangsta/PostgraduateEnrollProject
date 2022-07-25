package edu.dsm.controller;

import edu.dsm.entity.po.Message;
import edu.dsm.entity.po.User;
import edu.dsm.entity.vo.MessageForShow;
import edu.dsm.service.CollegeService;
import edu.dsm.service.MessageService;
import edu.dsm.service.UserService;
import edu.dsm.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    @Resource
    CollegeService collegeService;
    @Resource
    MessageService messageService;
    @Resource
    UserService userService;

    @GetMapping("toShowMyMessage")        // 查看留言板
    public String toShowMessage(Model model, HttpServletRequest request) {
        String userName = CookieUtil.getCookieUserName(request);
        User me = userService.getByUserName(userName);
        List<Message> mList = messageService.selectByUserId(me.getUserId());
        List<MessageForShow> messageList = new ArrayList<>();
        for (Message message:mList){
            User user = userService.getOneById(message.getUserId());
            User reader = userService.getOneById(message.getReaderId());
            MessageForShow messageForShow = new MessageForShow(user.getUserName(),reader.getUserName(),message.getMessage(),message.getMessageDate());
            messageList.add(messageForShow);
        }
        model.addAttribute("messageList",messageList);
        return "user_show_mymessage";
    }
    @GetMapping("toAddMessage") //  进入留言页面
    public String toAddMessage(Model model){
        return "user_add_message";
    }
    @GetMapping("addMessage")        // 添加留言
    public String addMessage(Model model, HttpServletRequest request,String text) {
        String myName = CookieUtil.getCookieUserName(request);
        String hisName = CookieUtil.getCookieHisName(request);
        User me = userService.getByUserName(myName);
        User him = userService.getByUserName(hisName);
        Message m = new Message(him.getUserId(),me.getUserId(),text);
        int cnt = messageService.addMessage(m);
        List<Message> hisList = messageService.selectByUserId(him.getUserId());
        List<MessageForShow> messageList = new ArrayList<>();
        for (Message message:hisList){
            User user = userService.getOneById(message.getUserId());
            User reader = userService.getOneById(message.getReaderId());
            MessageForShow messageForShow = new MessageForShow(user.getUserName(),reader.getUserName(),message.getMessage(),message.getMessageDate());
            messageList.add(messageForShow);
        }
        model.addAttribute("messageList",messageList);
        return "user_show_message";
    }

    @GetMapping("toShowHisMessage")        // 查看留言板
    public String toShowHisssage(Model model, HttpServletRequest request) {
        String hisName = CookieUtil.getCookieHisName(request);
        User him = userService.getByUserName(hisName);
        List<Message> mList = messageService.selectByUserId(him.getUserId());
        List<MessageForShow> messageList = new ArrayList<>();
        for (Message message:mList){
            User user = userService.getOneById(message.getUserId());
            User reader = userService.getOneById(message.getReaderId());
            MessageForShow messageForShow = new MessageForShow(user.getUserName(),reader.getUserName(),message.getMessage(),message.getMessageDate());
            messageList.add(messageForShow);
        }
        model.addAttribute("messageList",messageList);
        return "user_show_message";
    }
}
