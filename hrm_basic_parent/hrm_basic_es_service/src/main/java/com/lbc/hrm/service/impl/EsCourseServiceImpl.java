package com.lbc.hrm.service.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import com.lbc.hrm.doc.EsCourse;
import com.lbc.hrm.query.EsCourseQuery;
import com.lbc.hrm.repository.EsCourseRepository;
import com.lbc.hrm.service.IEsCourseService;
import com.lbc.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chuange
 * @since 2019-09-06
 *
 */


@Service
public class EsCourseServiceImpl implements IEsCourseService {
    @Autowired
    private EsCourseRepository esCourseRepository;

    @Override
    public void updateById(EsCourse esCourse) {
        esCourseRepository.save(esCourse);
    }

    @Override
    public void insert(EsCourse esCourse) {
        esCourseRepository.save(esCourse);

    }

    @Override
    public void deleteById(Long id) {
        esCourseRepository.deleteById(id);

    }

    @Override
    public EsCourse selectById(Long id) {
        return esCourseRepository.findById(id).get();
    }

    @Override
    public List<EsCourse> selectList(Object o) {
        Page page = (Page) esCourseRepository.findAll();
        return page.getContent();
    }

    @Override
    public PageList<EsCourse> selectPageList(EsCourseQuery query) {

        //用于收索
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //一个工具,用于产生query
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        //模糊查询 @TODO
        bool.must(QueryBuilders.matchQuery("intro", "zhang"));
        //精确过滤 @TODO
        List<QueryBuilder> filters = bool.filter();
        filters.add(QueryBuilders.rangeQuery("age").gte(0).lte(200));

        builder.withQuery(bool); //query bool must(filter)
        //排序 @TODO
        builder.withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC));

        //分页 当前页从0开始
        builder.withPageable(PageRequest.of(query.getPage()-1, query.getRows()));

        //构造查询条件
        NativeSearchQuery esQuery = builder.build();
        //查询
        Page<EsCourse> page = esCourseRepository.search(esQuery);
        return new PageList<>(page.getTotalElements(),page.getContent());
    }

    @Override
    public void saveBatch(List<EsCourse> esCourseList) {
        esCourseRepository.saveAll(esCourseList);
    }

    @Override
    public void deleteBatch(List<EsCourse> esCourseList) {
        esCourseRepository.deleteAll(esCourseList);
    }

}
