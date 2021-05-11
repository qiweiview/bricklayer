package cn.anicert.utils;

import cn.anicert.model.vo.BricklayerUserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<String> currentUerName = new ThreadLocal<>();

    public static String CURRENT_NAME = "CURRENT_NAME";

    private static Set<String> freeSet = new HashSet<>();
    private static Set<String> adminSet = new HashSet<>();

    static {
        freeSet.add("/bricklayerUser/doLogin");

        adminSet.add("bricklayerDb");//数据库操作
        adminSet.add("bricklayerDictionary/save");
        adminSet.add("bricklayerDictionary/delete");
        adminSet.add("bricklayerDictionary/batchDelete");
        adminSet.add("bricklayerDictionary/update");
        adminSet.add("bricklayerUser");//用户操作
        adminSet.add("bricklayerLog");//日志记录操作


    }

    public static String getCurrentName() {
        String s = currentUerName.get();
        return s == null ? "anonymous" : s;
    }

    public static Boolean isCurrentUser(String name) {
        String currentName = getCurrentName();
        if ("anonymous".equals(currentName)) {
            return false;
        }

        return currentName.equals(name);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (freeSet.contains(servletPath)) {
            return true;
        }
        String header = request.getHeader("auth-token");
        if (header == null) {
            authError(response);
            return false;
        } else {


            BricklayerUserVO bricklayerUserVO = BricklayerUserVO.fromToken(header);

            String userRole = bricklayerUserVO.getUserRole();
            if (!"admin".equals(userRole)) {
                boolean[] check = {false};
                adminSet.forEach(x -> {
                    check[0] = servletPath.indexOf(x) != -1;
                });
                if (check[0]) {
                    authError(response, "无接口权限");
                    return false;
                }
            }


            request.setAttribute(CURRENT_NAME, bricklayerUserVO);
            currentUerName.set(bricklayerUserVO.getUserName());
            return true;
        }
    }

    public void authError(HttpServletResponse response, String error) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String msg = objectMapper.writeValueAsString(ResponseVo.error(error));
            out.write(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authError(HttpServletResponse response) {
        authError(response, "token缺失");
    }


}
