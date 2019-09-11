package com.lbc.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lbc.hrm.cache.CourseTypeCache;
import com.lbc.hrm.domain.CourseType;
import com.lbc.hrm.mapper.CourseTypeMapper;
import com.lbc.hrm.query.CourseTypeQuery;
import com.lbc.hrm.service.ICourseTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lbc.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author yhptest
 * @since 2019-08-31
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private CourseTypeCache courseTypeCache;

    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(), query.getRows());
        List<CourseType> courseTypes = courseTypeMapper.loadListPage(page, query);
        return new PageList<>(page.getTotal(), courseTypes);
    }

    //无线级查询
    @Override
    public List<CourseType> selectTree(Long pid) {
        List<CourseType> courseTypes = courseTypeCache.getCourseTypes();
        if (courseTypes== null || courseTypes.size()<1){
            System.out.println("db...............");

        //方案一,递归,发很多条sql,不采纳
        //return getCourseTypesRecursion(pid);
        //方案二,循环,发一条sql,采纳

        //未使用缓存前
        //return getCourseTypesLoop(pid);

        //使用缓存后
            List<CourseType> courseTypesDb = getCourseTypesLoop(pid);
            courseTypeCache.setCourseTypes(courseTypesDb);
            return courseTypesDb;
        }
        System.out.println("cache...............");
        return  courseTypes;
    }

    /**
     * 方案一,递归,发送多条sql
     * @param pid
     * @return
     *
     */
    private List<CourseType> getCourseTypesRecursion(Long pid) {
        //方案1:递归-自己调用自己,要有出口
        List<CourseType> children = courseTypeMapper
                .selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        //要有出口
        if (children==null || children.size()<1)
        {
            return null;
        }
        for (CourseType child : children) {
            //自己调用自己
            List<CourseType> courseTypes = selectTree(child.getId());
            child.setChildren(courseTypes);
        }
        return children;
    }

    /**
     * 方案2:循环方案:一次sql
     * @param pid 0
     * @return
     */
    private List<CourseType> getCourseTypesLoop(Long pid) { //0
        //new一个容器
        List<CourseType> result = new ArrayList<>();
        // 查询所有类型数据
        List<CourseType> allTypes = courseTypeMapper.selectList(null);

        //建立id和CourseType的关联关系
        Map<Long,CourseType> allTypesDto = new HashMap<>();
        for (CourseType allType : allTypes) {
            allTypesDto.put(allType.getId(),allType);
        }
        //2 遍历判断是否是第一级  pid为传入id,说明为父类
        for (CourseType type : allTypes) {
            Long pidTmp = type.getPid();

            /**
             *  2.1是直接加入返回列表
             *      longValue() 把字符串转化为long类型
             *  如果存在pid说明他是父亲,将父亲添加容器中
             *
             */
            if (pidTmp.longValue()== pid.longValue()){
                result.add(type);
            }else{
                //2.2不是要把自己作为父亲儿子
                //通过pid获取父亲
                //方案1:遍历所有,通过父亲id来获取父亲
                    /*
                    for (CourseType courseType : allTypes) {
                        if(courseType.getId().longValue() == pidTmp.longValue()){
                            courseType.getChildren().add(type);
                        }

                    }*/
                //方案2:通过map获取
                CourseType parent = allTypesDto.get(pidTmp);
                parent.getChildren().add(type);
            }
        }

        return result;
    }

    @Override
    public boolean insert(CourseType entity) {
        courseTypeMapper.insert(entity);
        List<CourseType> courseTypes = selectTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        courseTypeMapper.deleteById(id);
        List<CourseType> courseTypes = selectTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        courseTypeMapper.updateById(entity);
        List<CourseType> courseTypes = selectTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }
}
