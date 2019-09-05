package com.lbc.hrm.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.lbc.hrm.domain.Course;
import com.lbc.hrm.mapper.CourseMapper;
import com.lbc.hrm.query.CourseQuery;
import com.lbc.hrm.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lbc.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chuange
 * @since 2019-09-04
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page page = new Page(query.getRows(),query.getPage());
        List<Course> courses = courseMapper.loadListPage(page,query);
        return null;
    }
}
