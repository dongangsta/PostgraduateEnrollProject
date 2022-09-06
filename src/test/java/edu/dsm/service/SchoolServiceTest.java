package edu.dsm.service;

import edu.dsm.entity.po.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class SchoolServiceTest {

    @Resource
    private SchoolService schoolService;
    @Test
    public void getAll() {
        List<School> schoolList= schoolService.getAll();
        for (School s:schoolList) {
            System.out.println(s.toString());
        }
    }
}