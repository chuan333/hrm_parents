package com.lbc.hrm.web.controller;

import com.lbc.hrm.doc.EsCourse;
import com.lbc.hrm.service.IEsCourseService;
import com.lbc.hrm.query.EsCourseQuery;
import com.lbc.hrm.util.AjaxResult;
import com.lbc.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esCourse")
public class EsCourseController {
    @Autowired
    public IEsCourseService esCourseService;

    /**
    * 保存和修改公用的
    * @param esCourse  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody EsCourse esCourse){
        try {
            if(esCourse.getId()!=null){
                esCourseService.updateById(esCourse);
            }else{
                esCourseService.insert(esCourse);
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
            esCourseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public EsCourse get(@PathVariable("id")Long id)
    {
        return esCourseService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<EsCourse> list(){

        return esCourseService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<EsCourse> json(@RequestBody EsCourseQuery query){
            return esCourseService.selectPageList(query);
    }
    @PostMapping("online")
    public AjaxResult saveBatch(@RequestBody List<EsCourse>  esCourseList){
        try {
            esCourseService.saveBatch(esCourseList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @PostMapping("offline")
    public AjaxResult deleteBatch(@RequestBody List<EsCourse>  esCourseList){
        try {
            esCourseService.deleteBatch(esCourseList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
}
