package com.example.activitidemo.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskApiController {

  @Autowired
  private TaskService taskService;
  @Autowired
  private RuntimeService runtimeService;
}
