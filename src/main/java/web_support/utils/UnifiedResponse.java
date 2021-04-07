package web_support.utils;

import lombok.Data;

@Data
public class UnifiedResponse<T> {
    private static final int SUCCESS_CODE=0;
    private static final int ERROR_CODE=1;
    private int code=SUCCESS_CODE;
    private String message="success";
    private T data;


    public static UnifiedResponse success(Object t){
        UnifiedResponse unifiedResponse = new UnifiedResponse();
        unifiedResponse.setCode(SUCCESS_CODE);
        unifiedResponse.setData(t);
        return unifiedResponse;
    }

    public static UnifiedResponse error(Object t){
        UnifiedResponse unifiedResponse = new UnifiedResponse();
        unifiedResponse.setCode(ERROR_CODE);
        unifiedResponse.setMessage(t==null?"服务器异常":t.toString());
        return unifiedResponse;
    }

}
