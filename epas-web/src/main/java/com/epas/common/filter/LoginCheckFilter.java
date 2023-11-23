package com.epas.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {
    @Override
    public void init(
            FilterConfig filterConfig
    ) throws ServletException {
        //log.info("LoginCheckFilter init()");
    }

    @Override
    public void destroy() {
        //log.info("LoginCheckFilter destroy()");
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        //log.info("[{}] LoginFilter doFilter Start", requestURI);

        try {
            chain.doFilter(request, response);
        } finally {
            //log.info("[{}] LoginFilter doFilter End", requestURI);
        }
    }
}