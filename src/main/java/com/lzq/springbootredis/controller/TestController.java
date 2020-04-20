package com.lzq.springbootredis.controller;

import com.lzq.springbootredis.dao.UserMessageMapper;
import com.lzq.springbootredis.model.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springboot-redis
 * @description:
 * @author: liuzhenqi
 * @create: 2020-04-16 16:53
 **/
@RestController
public class TestController {
    @Autowired
    private UserMessageMapper mapper;

    @RequestMapping(value = "/slave/list", method = RequestMethod.GET)
    @ResponseBody
    public String getTest(UserMessage age){
        mapper.insert(age);
        return "添加成功";
    }
    @RequestMapping(value = "/master/list", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMessage> getTest1(){
        return mapper.selectByExample();
    }
}
