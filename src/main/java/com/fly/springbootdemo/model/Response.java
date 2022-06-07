package com.fly.springbootdemo.model;

import com.fly.springbootdemo.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public Response() {

    }

    /**
     * 请求成功：code、msg默认，data自定义
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> sucess(T data) {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setMsg("SUCCESS");
        response.setData(data);
        return response;
    }

    /**
     * 请求成功：自定义msg
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> sucess(String msg, T data) {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    /**
     * 请求失败返回：自定义 code、msg、data
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    /**
     * 请求失败返回：返回枚举失败类型
     *
     * @param errorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(ErrorCodeEnum errorCodeEnum) {
        Response<T> response = new Response<>();
        response.setCode(errorCodeEnum.getCode());
        response.setMsg(errorCodeEnum.getMsg());
        return response;
    }
}
