package com.example.activitidemo.interceptor;


import com.example.activitidemo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    User user = (User) request.getSession().getAttribute("_user");
    if (user == null) {
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }
}
