package com.lbc.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lbc.hrm.doc.EsCourse;
import com.lbc.hrm.client.EsCourseClient;
import com.lbc.hrm.domain.Course;
import com.lbc.hrm.mapper.CourseDetailMapper;
import com.lbc.hrm.mapper.CourseMapper;
import com.lbc.hrm.query.CourseQuery;
import com.lbc.hrm.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lbc.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CourseServiceImpl<course2EsCourse> extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDetailMapper courseDetailMapper;

    @Autowired
    private EsCourseClient esCourseClient;

    //分页,高级查询方法重写,并有关联查询功能
    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        List<Course> courses = courseMapper.loadListPage(page,query);
        return new PageList<>(page.getTotal(),courses);
    }

    @Override
    public void saveBatch(Long[] ids) {
        //将数组转化为list
        List<Course> courseList = courseMapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.saveBatch(esCourseList);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        courseMapper.saves(Arrays.asList(ids));

    }

    @Override
    public void deleteBatch(Long[] ids) {
        //将数组转化为list
        List<Course> courseList = courseMapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.deleteBatch(esCourseList);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        courseMapper.deletes(Arrays.asList(ids));
    }

    //删除
    @Override
    public boolean deleteById(Serializable id) {
        //先删除数据库
        courseMapper.deleteById(id);
        //判断状态,还要删除索引库
        Course course = courseMapper.selectById(id);
        if (course.getStatus() ==  1)
            esCourseClient.delete(Integer.valueOf(id.toString()));
        return true;
    }

    //修改
    @Override
    public boolean updateById(Course entity) {
        //修改数据库
        courseMapper.updateById(entity);
        //判断状态-还要修改索引库
        Course course = courseMapper.selectById(entity.getId());
        if ( course.getStatus() ==1)
            esCourseClient.save(course2EsCourse(entity));
        return true;
    }


    //添加
    @Override
    public boolean insert(Course entity) {
        entity.setStatus(0);
        courseMapper.insert(entity);

        //添加课程详情
        /**
         * 此处设计为反三范式,课程详情中的id和外键id合并,所以不用重写mapper
         */
        entity.getCourseDetail().setCourseId(entity.getId());
        courseDetailMapper.insert(entity.getCourseDetail());
        return true;


    }


    /**
     *
     * @param courseList
     * @return
     * 为满足controller要求,将course集合转化为escourse集合
     */
    private List<EsCourse> courseList2EsCourse(List<Course> courseList) {
        List<EsCourse> result = new ArrayList<>();
        for (Course course : courseList) {
            //course2EsCourse该方法进行转换
            result.add(course2EsCourse(course));
        }
        return result;
    }

    /**
     * @TODO: 2019/9/6 不同得服务,反三范式设计字段多
     * @// TODO: 2019/9/7 相同服务关联查询
     * @param course
     * @return
     */
    private EsCourse course2EsCourse(Course course) {
        EsCourse esCourse = new EsCourse();
        esCourse.setId(course.getId());
        esCourse.setName(course.getName());
        esCourse.setUsers(course.getUsers());
        if (course.getCourseType() != null) {

            esCourse.setCourseTypeName(course.getCourseType().getName());
        }
        esCourse.setCourseTypeId(course.getCourseTypeId());
        esCourse.setGradeId(course.getGrade());
        esCourse.setGradeName(null);
        esCourse.setStatus(course.getStatus());
        esCourse.setTenantId(course.getTenantId());
        esCourse.setTenantName(course.getTenantName());
        esCourse.setUserId(course.getUserId());
        esCourse.setUserName(course.getUserName());
        esCourse.setStartTime(course.getStartTime());
        esCourse.setEndTime(course.getEndTime());
        esCourse.setIntro(null);
        esCourse.setResources(null);
        esCourse.setExpires(null);
        esCourse.setPriceOld(null);
        esCourse.setPrice(null);
        esCourse.setQq(null);
        return esCourse;
        }




}
