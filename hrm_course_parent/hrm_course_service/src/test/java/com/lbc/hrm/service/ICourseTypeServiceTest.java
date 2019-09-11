package com.lbc.hrm.service;

import com.lbc.hrm.Course9002;
import com.lbc.hrm.domain.CourseType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: hrm_parents
 * @Date: 2019/9/9 0:52
 * @Author: Mr.Deng
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Course9002.class)
public class ICourseTypeServiceTest {
@Autowired
private ICourseTypeService courseTypeService;
    @Test
    public void selectTree() throws Exception {
        System.out.println("进来了");
        List<CourseType> courseTypes = courseTypeService.selectTree(0L);
        for (CourseType courseType : courseTypes) {
            System.out.println(courseType);
            List<CourseType> children = courseType.getChildren();
            if (children !=null){
                for (CourseType child : children) {
                    System.out.println(child+"666666666666666666666666666666666666");
                }
            }
        }
    }
}