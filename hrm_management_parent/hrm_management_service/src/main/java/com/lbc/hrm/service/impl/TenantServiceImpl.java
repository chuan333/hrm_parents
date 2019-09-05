package com.lbc.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lbc.hrm.domain.Employee;
import com.lbc.hrm.domain.Tenant;
import com.lbc.hrm.mapper.EmployeeMapper;
import com.lbc.hrm.mapper.MealMapper;
import com.lbc.hrm.mapper.TenantMapper;
import com.lbc.hrm.service.ITenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chuange
 * @since 2019-09-03
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private EmployeeMapper employeeMapper;



    @Override
    public boolean insert(Tenant tenant) {

        System.out.println(tenant.getId()+"11111111111111111111111111111111111");
        tenantMapper.insert(tenant);

        Employee admin = tenant.getAdmin();
        admin.setInputTime(new Date());
        admin.setState(0);
        admin.setType(true); //是否是租户管理员
        //admin.setTenantId(tenant.getId());
        employeeMapper.insert(admin);

        tenantMapper.saveTenantMeals(tenant.getTenantMeals());


        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {

        //删除机构
        tenantMapper.deleteById(id);
        //删除管理员
        Wrapper<Employee> wapper = new EntityWrapper<>();
        wapper.eq("tenant_id",id);
        employeeMapper.delete(wapper);
        //删除中间表
        tenantMapper.removeTenantMeal(id);
        return true;
    }

    @Override
    public boolean updateById(Tenant tenant) {
        // 修改机构
        tenantMapper.updateById(tenant);
        //修改管理员
        employeeMapper.updateById(tenant.getAdmin());
        //修改中间表-先删除后添加
        tenantMapper.removeTenantMeal(tenant.getId());
        tenantMapper.saveTenantMeals(tenant.getTenantMeals());
        return true;
    }
}
