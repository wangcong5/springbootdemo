package com.fly.springbootdemo.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {
    public TimeUtil() {
    }

    /**
     * Java8 提供的 LocalDateTime 来替代传统的 Date 来处理时间
     *
     * @return 示例：2022-07-05T21:30:47.709
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取 Date
     *
     * @return 示例：Tue Jul 05 21:36:37 CST 2022
     */
    public static Date getDate() {
        LocalDateTime localDateTime = getLocalDateTime();

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

    /**
     * 获取自然时间
     * pattern：DEFAULT_PATTERN
     *
     * @return 示例：2022-07-06 20:05:01
     */
    public static String getDate2(String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);

        return df.format(getLocalDateTime());
    }

    /**
     * timeStamp 转为 自然时间
     * @param timeStamp：1657197272332L
     * @param pattern
     * @return 示例：2022-07-07 20:34:32
     */
    public static String getDateByTimeStamp(long timeStamp,String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);

        Instant instant = Instant.ofEpochMilli(timeStamp);
        LocalDateTime localDateTime= LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return df.format(localDateTime);
    }

    /**
     * 使用 SimpleDateFormat 获取当前日期
     *
     * @param pattern：Y_M_M_PATTERN
     * @return 示例：2022/07/07/
     */
    @Deprecated
    public static String getDate3(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(new Date());
        return format;
    }
}
