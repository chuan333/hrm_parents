package com.lbc.hrm.repository;

import com.lbc.hrm.doc.EsCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chuange
 * @since 2019-09-06
 */

public interface EsCourseRepository extends ElasticsearchRepository<EsCourse,Long> {


}
