${contextModel.utilsPackage}

import java.io.Serializable;


public class ResponseVo<T> implements Serializable {
    private String code="0";
    private String msg="";
    private   T data;

    public ResponseVo() {
    }



    public ResponseVo(String code, String msg) {
        this(code, msg, null);
    }


    public ResponseVo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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