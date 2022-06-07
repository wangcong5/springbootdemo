package com.fly.springbootdemo.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends People {
    private String name;
    private Integer age;
}
