package com.anicert.utils;

import com.anicert.model.vo.BricklayerUserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("auth-token");
        System.out.println("find token " + header);
        if (header == null) {
            authError(response);
            return false;
        } else {
            BricklayerUserVO bricklayerUserVO = BricklayerUserVO.fromToken(header);

            return true;
        }
    }


    public void authError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String msg = objectMapper.writeValueAsString(ResponseVo.error("token缺失"));
            out.write(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
