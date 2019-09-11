package com.lbc.hrm.controller;

import com.lbc.hrm.util.RedisUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @program: hrm_parents
 * @Date: 2019/9/7 23:52
 * @Author: Mr.Deng
 * @Description:
 */

@RestController
@RequestMapping("/cache")
public class RedisController {
    @PostMapping
    public void set(@RequestParam("key")String key, @RequestParam("value")String value) {
        RedisUtils.INSTANCE.set(key, value);
    }
    @GetMapping
    public String get(@RequestParam("key")String key) {
        return RedisUtils.INSTANCE.get(key);
    }
}
