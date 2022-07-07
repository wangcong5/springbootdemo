package com.fly.springbootdemo.utils;

import com.fly.springbootdemo.common.enums.StatusCode;
import com.fly.springbootdemo.exception.CaseServerException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Json 操作方法
 */
public class JsonUtil {
    public JsonUtil() {
    }

    /**
     * 读取解析json文件
     */
    public static String readJsonFile(String filePath) {
        String jsonStr = "";
        String jsonFile = filePath.replace("/", File.separator);
        try {
            File file = new File(jsonFile);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CaseServerException("读取json文件失败：" + e.getMessage(), StatusCode.FILE_IMPORT_ERROR);
        }
    }

}
