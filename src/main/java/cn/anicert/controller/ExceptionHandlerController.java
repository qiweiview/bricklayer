package cn.anicert.controller;

import cn.anicert.utils.DataNotFoundException;
import cn.anicert.utils.MessageRuntimeException;
import cn.anicert.utils.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseVo exceptionHandler(Exception e, HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(500);
        if (e instanceof MessageRuntimeException) {
            return ResponseVo.error(e.getMessage());
        }

        if (e instanceof DataNotFoundException) {
            return ResponseVo.error("查询不到对应数据");
        }

        //补充
        if (e instanceof DataNotFoundException) {
            return ResponseVo.error("查询不到对应数据");
        }

        e.printStackTrace();
        return ResponseVo.error("服务器异常");
    }
}
