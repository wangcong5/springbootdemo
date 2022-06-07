package com.fly.springbootdemo.controller;

import com.fly.springbootdemo.repository.mapper.UserMapper;
import com.fly.springbootdemo.repository.po.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String getMethodDemo(@RequestParam(value = "name", defaultValue = "word") String name) {
        log.info("name is {}", name);
        return String.format("hello %s ! ", name);
    }

    @PostMapping("/register")
    public String postMethodDemo(@RequestParam(value = "name", defaultValue = "zhangsan") String name,@NonNull @RequestParam(value = "age") int age) {
        log.info("request name is {}, age is {}", name, age);
        return String.format("%s age is %d", name, age);
    }

    @GetMapping("/hello2")
    public String getMethodDemo2(@RequestParam(value = "name", defaultValue = "word") String name) {
        log.info("name is {}", name);
        List<User> users=userMapper.selectList(null);

        return users.get(0).getName();
    }
}
