package com.fly.springbootdemo.utils;

import com.alibaba.fastjson.JSONObject;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UnirestUtils {
    public UnirestUtils() {
    }

    public static HttpResponse<String> getRequest(String url, Map<String,String> header) {

        HttpResponse<String> httpResponse = Unirest.get(url)
                // 设置header
                .header("Accept", "application/json")
                .header("x-custom-header", "hello")
                .header("Cookie", header.get("Cookie"))
                // Basic Authentication
                .basicAuth("username", "password!")

                // 设置请求参数
                .queryString("dept", "技术部")
                .queryString("address", "china")
                .queryString("age", 18)
                .asString();

        log.info(httpResponse.getBody());
        return httpResponse;
    }

//    public static HttpResponse<JsonNode> PostRequest(String url,Map<String,String> header, Map<String,String> body) {
//        HttpResponse<JsonNode> response = Unirest.post(url)
//                .header("accept", "application/json")
//
//                .header("Cookie", "xxx")
//                .body(JSON.toJSONString(body))
//
////                .queryString("apiKey", "123")
////                .field("name", "张三")
////                .field("部门", "技术部")
////                .field("地址", "朝阳")
//                .asJson();
//
//        log.info(response.getBody().toString());
//        return response;
//    }

    public static HttpResponse<JsonNode> PostRequest(String url,Map<String,String> header, String body) {
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("Content-Type", "application/json")

//                .header("Cookie", "xxx")
                .body(JSONObject.parseObject(body))

//                .queryString("apiKey", "123")
//                .field("name", "张三")
//                .field("部门", "技术部")
//                .field("地址", "朝阳")
                .asJson();

        log.info(response.getBody().toString());
        return response;
    }

    public static void main(String[] args) {}
}
