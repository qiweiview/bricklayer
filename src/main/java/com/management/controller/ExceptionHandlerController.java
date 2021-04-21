package com.management.controller;

import com.management.utils.MessageRuntimeException;
import com.management.utils.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseVo exceptionHandler(Exception e, HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(500);
        if (e instanceof MessageRuntimeException){
            return ResponseVo.error(e.getMessage());
        }else{
            e.printStackTrace();
        }
        return ResponseVo.error("服务器异常");
    }
}
