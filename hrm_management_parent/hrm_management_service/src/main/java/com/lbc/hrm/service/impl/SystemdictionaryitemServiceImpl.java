package com.lbc.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lbc.hrm.domain.Systemdictionary;
import com.lbc.hrm.domain.Systemdictionaryitem;
import com.lbc.hrm.mapper.SystemdictionaryMapper;
import com.lbc.hrm.mapper.SystemdictionaryitemMapper;
import com.lbc.hrm.service.ISystemdictionaryitemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhptest
 * @since 2019-08-30
 */
@Service
public class SystemdictionaryitemServiceImpl extends ServiceImpl<SystemdictionaryitemMapper, Systemdictionaryitem> implements ISystemdictionaryitemService {

    private Logger logger = LoggerFactory.getLogger(SystemdictionaryitemServiceImpl.class);
    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;
    @Autowired
    private SystemdictionaryitemMapper systemdictionaryitemMapper;
    @Override
    public List<Systemdictionaryitem> listByParentSn(String sn) {
        //用wrapper对sn进行封装
        Wrapper<Systemdictionary> wrapper = new EntityWrapper<Systemdictionary>().eq("sn", sn);
        //select * from t_Systemdictionary
        //Wrapper(EntityWrapper)可以用它封装查询条件
        //Wrapper.eq表示等于 eq("sn","courseLevel")
        //select * from t_Systemdictionary where sn = courseLevel
        List<Systemdictionary> systemdictionaries = systemdictionaryMapper.selectList(wrapper);
        //当sn不存在时
        if (systemdictionaries == null || systemdictionaries.size()<1){
            logger.error(" systemdictionaries not exist!");
            return null;
        }
        //存在时,得到对应的字典对象包括id
        Systemdictionary systemdictionary = systemdictionaries.get(0);
        EntityWrapper<Systemdictionaryitem> wrapper1 = new EntityWrapper<>();

        // where parent_id = #{id}
        wrapper1.eq("parent_id",systemdictionary.getId());
        return systemdictionaryitemMapper.selectList(wrapper1);
    }
}
