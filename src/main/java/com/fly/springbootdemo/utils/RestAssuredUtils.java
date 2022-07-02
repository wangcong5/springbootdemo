package com.fly.springbootdemo.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * https://www.jianshu.com/p/9e892d5b1ee8
 */
public class RestAssuredUtils {
    public RestAssuredUtils() {
    }

    public static Response get(String apiPath, String cookie, Headers headers) {

        System.out.println("Cookie = " + cookie);

        Response response = given().contentType(ContentType.JSON).
                headers("Headers", headers).
                cookies("web-session", cookie).
                when().log().all().get(apiPath.trim());

        System.out.println("\n===>" + response.getBody().prettyPrint());
        return response;
    }


    public static Response post(String apiPath, String jsonData) {

        Response response = given().
                contentType(ContentType.JSON).
                body(jsonData).
                when().log().all().post(apiPath.trim());

        System.out.println("\n===>" + response.getBody().prettyPrint());
        return response;
    }

    public static void main(String[] args) {
        /**
         * post test
         */
        // 默认值为localhost，修改为实际调用接口的目标URL
//        RestAssured.baseURI="http://localhost:8080";
        String postpath = "demo/registerjson";

        String body = "{\"name\":\"zhangsan\",\"age\":32}";
        post(postpath, body);

        /**
         * get test
         */
        String getPath = "/demo/hello?name=zhangsan";
        Headers headers = new Headers();
        get(getPath, "test", headers);

    }
}
