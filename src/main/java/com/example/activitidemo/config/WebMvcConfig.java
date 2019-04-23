package com.example.activitidemo.config;

import com.example.activitidemo.interceptor.AdminInterceptor;
import com.example.activitidemo.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

  @Autowired
  private UserInterceptor userInterceptor;
  @Autowired
  private AdminInterceptor adminInterceptor;

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(userInterceptor).addPathPatterns("/**").excludePathPatterns("/logout", "/login", "/register", "/api/login", "/api/register");
    registry.addInterceptor(adminInterceptor).addPathPatterns("/workflow/**");
  }
}
