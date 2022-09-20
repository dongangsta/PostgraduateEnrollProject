package edu.dsm.job;

import edu.dsm.entity.po.School;
import edu.dsm.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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
    int cntUpdate = 0;
    @Scheduled(fixedRate=1800000)
    private void start(){
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("TaskAverageScore");
        List<School> schoolList = schoolService.getAll();
        for (School school:schoolList) {
            int cnt = 3;
            if (ObjectUtils.isEmpty(school.getScore19())) cnt--;
            if (ObjectUtils.isEmpty(school.getScore20())) cnt--;
            Integer score19 = ObjectUtils.isEmpty(school.getScore19())? 0 : school.getScore19();
            Integer score20 = ObjectUtils.isEmpty(school.getScore20())? 0 : school.getScore20();
            Integer sum = score19 + score20 + school.getScore21();
            Integer averageScore = sum/cnt;
            int numCnt = schoolService.updateAverageScore(school.getSchoolId(),averageScore);
            cntUpdate = cntUpdate + numCnt;
            System.out.println(cntUpdate+"条数据已经被更新，本条数据的schoolId = " + school.getSchoolId()
                    +", averageScore = "+averageScore);
        }
        stopWatch.stop();
        stopWatch.start("SaveIntoRedis");
        collegeService.getAll();
        schoolService.getAll();
        userService.getAll();
        articleService.getAll();
        messageService.getAll();
        myLikeService.selectAll();
        System.out.println("院校信息院系信息用户信息文章信息已存入Redis");
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
