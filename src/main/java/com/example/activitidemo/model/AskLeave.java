package com.example.activitidemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tomoya at 2019/4/22
 */
@Data
@Entity
public class AskLeave implements Serializable {
  private static final long serialVersionUID = 962824912139822725L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String description;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date inTime;
  private Integer day;
  // 状态：创建, 提交, 放弃, 通过，拒绝
  private String status;

  @ManyToOne
  private User user;

}
