package com.fly.springbootdemo.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.springbootdemo.repository.mapper.AccountMapper;
import com.fly.springbootdemo.repository.po.Account;
import com.fly.springbootdemo.repository.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
