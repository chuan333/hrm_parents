package com.lbc.hrm.client;

import com.lbc.hrm.domain.Meal;
import com.lbc.hrm.query.MealQuery;
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
public class MealClientHystrixFallbackFactory implements FallbackFactory<MealClient> {

    @Override
    public MealClient create(Throwable throwable) {
        return new MealClient() {
            @Override
            public AjaxResult save(Meal meal) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Meal get(Long id) {
                return null;
            }

            @Override
            public List<Meal> list() {
                return null;
            }

            @Override
            public PageList<Meal> json(MealQuery query) {
                return null;
            }
        };
    }
}
