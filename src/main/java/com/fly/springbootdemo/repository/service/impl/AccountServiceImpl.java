package com.fly.springbootdemo.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.springbootdemo.repository.mapper.AccountMapper;
import com.fly.springbootdemo.repository.po.Account;
import com.fly.springbootdemo.repository.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    AccountMapper accountMapper;
    public void k(){
        Account account =new Account();
        account.setId(null);
        account.setName("mc1");
        account.setMoney(100.00);
//        int result=accountMapper.insert(account);
        int result=accountMapper.insert(account);
    }
    public static void main(String[] args) {
        new AccountServiceImpl().k();
    }
}
