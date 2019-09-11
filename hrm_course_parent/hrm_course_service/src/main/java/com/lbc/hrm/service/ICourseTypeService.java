package com.lbc.hrm.service;

import com.lbc.hrm.domain.CourseType;
import com.baomidou.mybatisplus.service.IService;
import com.lbc.hrm.query.CourseTypeQuery;
import com.lbc.hrm.util.PageList;

import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author yhptest
 * @since 2019-08-31
 */
public interface ICourseTypeService extends IService<CourseType> {

    PageList<CourseType> selectListPage(CourseTypeQuery query);

    List<CourseType> selectTree(Long pid);
}
