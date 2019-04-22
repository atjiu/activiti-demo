package com.example.activitidemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by tomoya at 2019/4/22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "site")
public class SiteConfig {
  private List<String> ranks;
}
