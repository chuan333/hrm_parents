package com.lbc.hrm.web.controller;

import com.lbc.hrm.service.ICourseTypeService;
import com.lbc.hrm.domain.CourseType;
import com.lbc.hrm.query.CourseTypeQuery;
import com.baomidou.mybatisplus.plugins.Page;
import com.lbc.hrm.util.AjaxResult;
import com.lbc.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    public ICourseTypeService courseTypeService;

    /**
    * 保存和修改公用的
    * @param courseType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseType courseType){
        try {
            if(courseType.getId()!=null){
                courseTypeService.updateById(courseType);
            }else{
                courseTypeService.insert(courseType);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseTypeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CourseType get(@PathVariable("id")Long id)
    {
        return courseTypeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<CourseType> list(){

        return courseTypeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<CourseType> json(@RequestBody CourseTypeQuery query){
        /*Page<CourseType> page = new Page<CourseType>(query.getPage(),query.getRows());
            page = courseTypeService.selectPage(page);
            return new PageList<CourseType>(page.getTotal(),page.getRecords());*/

        /**
         *  原有的方法不能关联查询,需要重写方法
         *
         *  重写方法:
         *             分页,高级查寻,关联查询
         *
         */
        return courseTypeService.selectListPage(query);

    }

    //无限级查询
    @RequestMapping(value = "/treeData",method = RequestMethod.GET)
    public List<CourseType> getTree(){
        return courseTypeService.selectTree(0L);
    }
}
