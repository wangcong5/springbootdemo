package com.fly.springbootdemo.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.springbootdemo.repository.po.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
