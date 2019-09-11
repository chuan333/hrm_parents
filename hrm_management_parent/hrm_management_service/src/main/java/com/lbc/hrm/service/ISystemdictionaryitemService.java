package com.lbc.hrm.service;

import com.lbc.hrm.domain.Systemdictionaryitem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yhptest
 * @since 2019-08-30
 */
public interface ISystemdictionaryitemService extends IService<Systemdictionaryitem> {

    List<Systemdictionaryitem> listByParentSn(String sn);
}
