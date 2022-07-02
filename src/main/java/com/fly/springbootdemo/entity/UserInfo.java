package com.fly.springbootdemo.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private String address;
    private Long phone;
    private Long age;
}
