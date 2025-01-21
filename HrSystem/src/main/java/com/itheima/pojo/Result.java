package com.itheima.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果封装类
 */
@Data
public class Result implements Serializable {

    private Integer code; // 编码：1 成功，0 失败
    private String msg;   // 提示信息
    private Object data;  // 数据对象

    // 私有构造方法，外部通过静态方法调用
    private Result() {
    }

    /**
     * 请求成功（无返回数据）
     */
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 请求成功（有返回数据）
     *
     * @param data 需要返回的具体数据
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }

    /**
     * 请求成功（自定义提示信息 + 数据）
     *
     * @param msg  自定义成功信息
     * @param data 需要返回的具体数据
     */
    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = msg;
        result.data = data;
        return result;
    }

    /**
     * 请求失败（自定义错误信息）
     *
     * @param msg 失败提示信息
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }

    /**
     * 请求失败（自定义错误信息 + 返回数据）
     *
     * @param msg  自定义失败信息
     * @param data 需要返回的具体数据
     */
    public static Result error(String msg, Object data) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }
}
