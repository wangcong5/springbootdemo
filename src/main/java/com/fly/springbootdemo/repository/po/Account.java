package com.fly.springbootdemo.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account")
public class Account {
    private Long id;
    private String name;
    private Double money;
}
