package com.example.activitidemo.controller;

import com.example.activitidemo.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by tomoya at 2019/4/22
 */
public class BaseController {

  protected User getUser() {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = requestAttributes.getRequest();
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("_user");
    return user;
  }
}
