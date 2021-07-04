package cn.bricklayer.utils;

import java.io.Serializable;


public class ResponseVo<T> implements Serializable {
    private static final int SUCCESS_CODE=0;
    private static final int ERROR_CODE=1;
    private int code=0;
    private String msg="";
    private   T data;

    public ResponseVo() {
    }

    public static ResponseVo error(Object t){
        ResponseVo unifiedResponse = new ResponseVo();
        unifiedResponse.setCode(ERROR_CODE);
        unifiedResponse.setMsg(t==null?"服务器异常":t.toString());
        return unifiedResponse;
    }

    public static ResponseVo success(Object t){
        ResponseVo unifiedResponse = new ResponseVo();
        unifiedResponse.setData(t);
        unifiedResponse.setCode(SUCCESS_CODE);
        unifiedResponse.setMsg("操作成功");
        return unifiedResponse;
    }


    public ResponseVo(int code, String msg) {
        this(code, msg, null);
    }


    public ResponseVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}