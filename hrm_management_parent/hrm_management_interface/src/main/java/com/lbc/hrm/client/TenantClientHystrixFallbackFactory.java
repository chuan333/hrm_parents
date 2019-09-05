package com.lbc.hrm.client;

import com.lbc.hrm.domain.Tenant;
import com.lbc.hrm.query.TenantQuery;
import com.lbc.hrm.util.AjaxResult;
import com.lbc.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yaohuaipeng
 * @date 2018/10/8-16:18
 */
@Component
public class TenantClientHystrixFallbackFactory implements FallbackFactory<TenantClient> {

    @Override
    public TenantClient create(Throwable throwable) {
        return new TenantClient() {
            @Override
            public AjaxResult save(Tenant tenant) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Tenant get(Long id) {
                return null;
            }

            @Override
            public List<Tenant> list() {
                return null;
            }

            @Override
            public PageList<Tenant> json(TenantQuery query) {
                return null;
            }
        };
    }
}
