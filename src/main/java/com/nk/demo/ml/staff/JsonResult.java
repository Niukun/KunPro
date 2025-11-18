package com.nk.demo.ml.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonResult<T> implements Serializable {

    private final static String SUCCESS = "success";

    private boolean success = true;
    private T data;
    private String msg;
    private String errCode;
    private String message;

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(boolean success, String msg, T data) {
        this(success, msg);
        this.data = data;
    }

    public JsonResult(boolean success, T data, String msg, String errCode) {
        this(success, msg);
        this.data = data;
        this.errCode = errCode;
    }

    /**
     * @param message
     * @param data
     * @param errCode
     * @author: yuchao
     * @date: 2021-03-22 08:31:27
     * @description: 携带消息体
     */
    public static <T> JsonResult ok(String message, T data, String errCode) {
        return new JsonResult(true, data, message, errCode);
    }

    public static JsonResult ok() {
        return new JsonResult(true, SUCCESS);
    }

    /**
     * 传参作为 msg 值
     *
     * @param msg msg
     */
    public static JsonResult ok(String msg) {
        return new JsonResult(true, msg);
    }

    /**
     * 传参作为 data 值
     *
     * @param data data
     */
    public static JsonResult ok2(String data) {
        return new JsonResult(true, SUCCESS, data);
    }

    /**
     * 使用 StatusEnum 接口类构造响应数据
     */
    public static JsonResult ok(StatusEnum se) {
        return new JsonResult(true, se.getText());
    }

    public static <T> JsonResult ok(String msg, T data) {
        return new JsonResult(true, msg, data);
    }

    public static <T> JsonResult ok(T data) {
        return new JsonResult(true, SUCCESS, data);
    }

    /**
     * 使用 StatusEnum 接口类 和 data 构造响应数据
     */
    public static <T> JsonResult ok(StatusEnum se, T data) {
        return new JsonResult(true, data, se.getText(), null);
    }

    public static JsonResult error() {
        return new JsonResult(false, null);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(false, msg);
    }

    public static JsonResult error(String msg, String errCode) {
        return new JsonResult(false, null, msg, errCode);
    }

    public static JsonResult error(BaseException e) {
        return new JsonResult(false, e.getErrorCode(), e.getMessage(), null);
    }

    /**
     * 使用 ErrorEnum 接口类构造响应数据
     */
    public static JsonResult error(ErrorEnum ee) {
        return new JsonResult(false, null, ee.getText(), ee.getCode());
    }

    /**
     * FIXME web,app返回值统一 过渡方案
     *
     * @return
     */
    public String getMessage() {
        return this.msg;
    }
}