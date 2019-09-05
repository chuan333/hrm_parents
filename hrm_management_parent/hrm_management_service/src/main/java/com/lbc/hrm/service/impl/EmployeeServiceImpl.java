package com.lbc.hrm.service.impl;

import com.lbc.hrm.domain.Employee;
import com.lbc.hrm.mapper.EmployeeMapper;
import com.lbc.hrm.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chuange
 * @since 2019-09-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
