package com.taotao.rest.controller;

import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Description:    缓存同步Controller
* @Author:          李耀钦
* @CreateDate:     2018/9/5 11:35
* @UpdateUser:     李耀钦
* @UpdateDate:     2018/9/5 11:35
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    @Autowired
    private RedisService redisService;
    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCid){
        TaotaoResult result=redisService.syncContent(contentCid);
        return result;
    }
}
