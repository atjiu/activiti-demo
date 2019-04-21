package com.example.activitidemo;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActivitiDemoApplication.class, args);
  }

  @Bean
  public StandaloneProcessEngineConfiguration engineConfiguration() {
    StandaloneProcessEngineConfiguration engineConfiguration = new StandaloneProcessEngineConfiguration();
    engineConfiguration.setActivityFontName("宋体");
    engineConfiguration.setLabelFontName("宋体");
    return engineConfiguration;
  }
}
