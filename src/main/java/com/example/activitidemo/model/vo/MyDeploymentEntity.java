package com.example.activitidemo.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tomoya at 2019/4/22
 */
@Data
public class MyDeploymentEntity implements Serializable {
  private static final long serialVersionUID = -1789015769554636690L;
  private String name;
  private String category;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date deploymentTime;
  private String key;
}
