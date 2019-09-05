package com.lbc.hrm.service;

import com.lbc.hrm.domain.Course;
import com.baomidou.mybatisplus.service.IService;
import com.lbc.hrm.query.CourseQuery;
import com.lbc.hrm.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chuange
 * @since 2019-09-04
 */
public interface ICourseService extends IService<Course> {

    PageList<Course> selectListPage(CourseQuery query);
}
