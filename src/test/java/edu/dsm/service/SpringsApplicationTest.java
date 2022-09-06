package edu.dsm.service;

import edu.dsm.entity.po.School;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class SpringsApplicationTest {
    @Resource
    public SchoolService schoolService;
    //引入 ContiPerf 进行性能测试
    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    //10个线程 执行10次
    @PerfTest(invocations = 100,threads = 10)
    public void test() {
        List<School> schoolList = schoolService.getAll();
        System.out.println(schoolList.isEmpty());
    }
}
