package com.example.log_and_proof_back.filter;


import com.alibaba.fastjson.JSONObject;
import com.example.log_and_proof_back.model.pojo.Result;
import com.example.log_and_proof_back.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //1 获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求的url: {}",url);

        //2 判断请求url中是否包含login,如果包含 说明是登录操作,放行
        if(url.contains("login")){
            log.info("登录操作,放行...");
            filterChain.doFilter(request,response);
            return;
        }

        //3 获取请求头中的令牌
        String jwt = req.getHeader("token");

        //4 判断令牌是否存在,如果不存在,返回错误结果(未登录)
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录的信息");

            Result error = Result.error();
            // 手动转换 对象--json  ----->  阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5 解析token 如果解析失败,返回错误结果(未登录)
        try{
            JwtUtils.parseJWT(jwt);
        }catch(Exception e){
            e.printStackTrace();
            log.info("解析令牌失败");

            Result error = Result.error();
            // 手动转换 对象--json  ----->  阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6 放行
        log.info("令牌合法,放行");
        filterChain.doFilter(request,response);
    }
}


