package com.epas.login.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.epas.login.dto.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	System.out.println(">>>>>>>> interceptor preHandle <<<<<<<<");
        // 1. 세션에서 회원 정보 조회
        HttpSession session = request.getSession();
        MemberInfoDto member = (MemberInfoDto) session.getAttribute("loginMember");

        // 2. 회원 정보 체크
        if (member == null) {
            response.sendRedirect("/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}