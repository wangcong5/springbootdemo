package com.fly.springbootdemo.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeUtil {
    public TimeUtil() {
    }

    /**
     * Java8 提供的 LocalDateTime 来替代传统的 Date 来处理时间
     *
     * @return 示例：2022-07-05T21:30:47.709
     */
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取 Date
     *
     * @return 示例：Tue Jul 05 21:36:37 CST 2022
     */
    public Date getDate() {
        LocalDateTime localDateTime = this.getLocalDateTime();

        Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
        return date;
    }

    /**
     * 获取时间戳
     *
     * @return 示例：1657028339780
     */
    public long getTimeStamp() {
        long timestamp = getLocalDateTime().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        return timestamp;

    }
}
