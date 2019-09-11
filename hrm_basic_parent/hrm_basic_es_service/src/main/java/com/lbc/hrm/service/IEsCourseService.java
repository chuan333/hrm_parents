package com.lbc.hrm.service;

import com.baomidou.mybatisplus.service.IService;
import com.lbc.hrm.doc.EsCourse;
import com.lbc.hrm.query.EsCourseQuery;
import com.lbc.hrm.util.PageList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chuange
 * @since 2019-09-06
 */
public interface IEsCourseService {

    void updateById(EsCourse esCourse);

    void insert(EsCourse esCourse);

    void deleteById(Long id);

    EsCourse selectById(Long id);

    List<EsCourse> selectList(Object o);

    PageList<EsCourse> selectPageList(EsCourseQuery query);

    void saveBatch(List<EsCourse> esCourseList);

    void deleteBatch(List<EsCourse> esCourseList);
}
