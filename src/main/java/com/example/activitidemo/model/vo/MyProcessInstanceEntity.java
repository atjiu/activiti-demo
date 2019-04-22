package com.example.activitidemo.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tomoya at 2019/4/22
 */
@Data
public class MyProcessInstanceEntity implements Serializable {
  private static final long serialVersionUID = -4436481344260649583L;

  private String processDefinitionId;
  private String processDefinitionKey;
  private String processDefinitionName;
  private String activityId;
  private String activityName;
  private String processInstanceId;
  private String deploymentId;
  private String name;
  private String description;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;

}
