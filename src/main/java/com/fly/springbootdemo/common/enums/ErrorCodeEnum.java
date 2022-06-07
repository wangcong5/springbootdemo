package com.fly.springbootdemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    // 文件操作
    FILE_NOT_FOUND(1001,"未找到文件"),
    FILE_FORMAT_ERROR(1002,"文件格式不正确"),

    // http 请求
    URL_NOT_FOUND(2001,"URL不正确");

    private int code;
    private String msg;
}
