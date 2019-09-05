package com.lbc.hrm.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.lbc.hrm.domain.Course;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lbc.hrm.query.CourseQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chuange
 * @since 2019-09-04
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> loadListPage(Page page, CourseQuery query);
}
