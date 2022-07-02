package com.fly.springbootdemo.repository.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.springbootdemo.repository.mapper.UserMapper;
import com.fly.springbootdemo.repository.po.User;
import com.fly.springbootdemo.repository.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
