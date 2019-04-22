package com.example.activitidemo.interceptor;


import com.example.activitidemo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    User user = (User) request.getSession().getAttribute("_user");
    if (!user.getRank().equalsIgnoreCase("管理员")) {
      response.setHeader("charset", "utf8");
      response.setHeader("content-type", "application/json");
      response.sendError(403, "你没有权限访问这个链接");
      return false;
    }
    return true;
  }
}
