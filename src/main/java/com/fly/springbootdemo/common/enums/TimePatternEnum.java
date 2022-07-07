package com.fly.springbootdemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间格式
 */
@AllArgsConstructor
@Getter
public enum TimePatternEnum {
    // 默认 日期格式
    DEFAULT_PATTERN("yyyy-MM-dd HH:mm:ss","年月日时分秒"),
    YM_M_PATTERN("yyyy-MM-dd","年-月-日"),
    Y_M_M_PATTERN("yyyy/MM/dd/","年/月/日");

    private String pattern;
    private String message;
}
