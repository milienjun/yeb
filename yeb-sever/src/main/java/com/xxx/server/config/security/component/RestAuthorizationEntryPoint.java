package com.xxx.server.config.security.component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token登录失效时访问接口，自定义返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    httpServletResponse.setCharacterEncoding("UTf-8");
    httpServletResponse.setContentType("application/jason");
        PrintWriter out=httpServletResponse.getWriter();
        RespBean bean=RespBean.error("未登录，请登录！");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
