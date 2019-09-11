package com.lbc.hrm;

import com.lbc.hrm.domain.Course;
import com.lbc.hrm.service.ICourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Course9002.class)
public class XxxServiceTest {

@Autowired
private ICourseService courseService;
    @Test
    public void test()throws Exception{
        for (Course systemdictionary : courseService.selectList(null)) {
            System.out.println(systemdictionary);
        }
    }

}
