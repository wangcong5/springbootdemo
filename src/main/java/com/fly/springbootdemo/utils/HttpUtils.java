package com.fly.springbootdemo.utils;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class HttpUtils {
    public HttpUtils() {
    }


    public static void main(String[] args) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.select("name")
                .eq(true,"name","MC")
                .eq(true,"age",20)
                .ne(true,"email","739@qq.com")
                .ge(true,"id",0);

    }
}
