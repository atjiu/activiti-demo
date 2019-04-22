package com.example.activitidemo.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tomoya at 2019/4/22
 */
@Data
public class MyProcessDefinitionEntity implements Serializable {
  private static final long serialVersionUID = -8824191265626016139L;
  private String id;
  private String name;
  private String description;
  private String key;
  private int version;
  private String category;
  private String deploymentId;
  private String resourceName;
  private Integer historyLevel;
  private String diagramResourceName;
}
