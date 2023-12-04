/**
 * @author FW
 * @date 2023/9/7 16:18
 * @Software: IntelliJ IDEA WEB-study
 * @version 1.0
 */
package com.bbwweb.Result;

public class Result {
    private Integer code ;//1 成功 , 0 失败
    private String msg; //提示信息
    private Object data; //数据 data

    public Result() {
    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object data){    //响应成功并返回指定数据
        return new Result(1, "success", data);
    }
    public static Result success(){               //响应成功但不返回数据
        return new Result(1, "success", null);
    }
    public static Result error(String msg){       //响应失败,返回提示文本msg
        return new Result(0, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
