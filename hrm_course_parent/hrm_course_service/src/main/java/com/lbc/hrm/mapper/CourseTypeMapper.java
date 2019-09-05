package com.lbc.hrm.mapper;

import com.lbc.hrm.domain.CourseType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lbc.hrm.query.CourseTypeQuery;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author yhptest
 * @since 2019-08-31
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    List<CourseType> loadListPage(Pagination page, @Param("query") CourseTypeQuery query);

}
