package edu.dsm.job;

import edu.dsm.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Resource
    private CollegeService collegeService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;
    @Resource
    private MessageService messageService;
    @Resource
    private MyLikeService myLikeService;
    //3.添加定时任务
//    @Scheduled(cron = "0/300 * * * * ? *")
//    或直接指定时间间隔，例如：500秒 fixedRate=300000
    @Scheduled(fixedRate=180000)
    private void configureTasks() {
        collegeService.getAll();
        schoolService.getAll();
        userService.getAll();
        articleService.getAll();
        messageService.getAll();
        myLikeService.selectAll();
        System.out.println("院校信息院系信息用户信息文章信息已存入Redis");
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
