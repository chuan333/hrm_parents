package com.lbc.hrm.util;


import com.lbc.hrm.domain.Employee;
import com.lbc.hrm.domain.Tenant;

public class UserInfoHolder {
    public static Tenant getTenant(){
        Tenant currentLoginUserTenant = new Tenant();
        currentLoginUserTenant.setId(26L);
        currentLoginUserTenant.setCompanyName("源码时代");
        return currentLoginUserTenant;
    }
    public static Employee getLoginUser(){
        Employee employee = new Employee();
        employee.setId(42L);
        employee.setUsername("yhptest1");
        return employee;
    }
}
