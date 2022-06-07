package com.fly.springbootdemo;

import com.fly.springbootdemo.repository.mapper.AccountMapper;
import com.fly.springbootdemo.repository.mapper.UserMapper;
import com.fly.springbootdemo.repository.po.Account;
import com.fly.springbootdemo.repository.po.User;
import com.fly.springbootdemo.repository.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.fly.springbootdemo.utils.CmdUtils.execCmd;

@SpringBootTest
public class SqlTest {
    @Resource
    private UserMapper userMapper;

    @Resource
    private AccountMapper accountMapper;

    @Autowired
    private UserService userService;

    @Test
    void queryUser(){
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void queryAccount(){
        List<Account> users=accountMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testDynamicDS() throws InterruptedException, ExecutionException, IOException {
        System.out.println(userService.getById(1));

        System.out.println(execCmd("cd /Users/mtdp1/Code/cdb/study;ls"));
    }
}
