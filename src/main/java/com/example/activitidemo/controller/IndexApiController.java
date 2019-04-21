package com.example.activitidemo.controller;

import com.example.activitidemo.model.User;
import com.example.activitidemo.service.UserService;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexApiController {

  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private IdentityService identityService;
  @Autowired
  private RepositoryService repositoryService;
  @Autowired
  private ProcessEngine processEngine;
  @Autowired
  private HistoryService historyService;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public Object login(User user, HttpSession session) {
    user = userService.save(user);
    session.setAttribute("_user", user);
    return user;
  }

  @PostMapping("/register")
  public Object register(User user, HttpSession session) {
    user = userService.save(user);
    session.setAttribute("_user", user);
    return user;
  }

  @PostMapping("/tasks")
  public Object tasks(HttpSession session) {
    User user = (User) session.getAttribute("_user");
    ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("AskLeave").singleResult();
    List<Task> tasks = taskService.createTaskQuery().processDefinitionId(definition.getId()).list();
    return tasks;
  }

  @PostMapping("/createTask")
  public Object createTask(String title, String desc, HttpSession session) {
    User user = (User) session.getAttribute("_user");
    Map<String, Object> variables = new HashMap<>();
    variables.put("title", title);
    variables.put("desc", desc);
    variables.put("username", user.getUsername());
    // find processDefinition
    ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("AskLeave").singleResult();
    // create processInstance
    runtimeService.startProcessInstanceById(definition.getId(), variables);
    return true;
  }

  @PostMapping("/publishTask")
  public Object publishTask(String taskId) {
    // find next assignee
    User division_manager = userService.findByRank("Division manager");
    Map<String, Object> variables = new HashMap<>();
    variables.put("username", division_manager.getUsername());
    taskService.complete(taskId, variables);
    return true;
  }

  @PostMapping("/firstReview")
  public Object firstReview(String taskId) {
    // find next assignee
    User general_manager = userService.findByRank("General manager");
    Map<String, Object> variables = new HashMap<>();
    variables.put("username", general_manager.getUsername());
    taskService.complete(taskId, variables);
    return true;
  }

  @PostMapping("/lastReview")
  public Object lastReview(String taskId) {
    taskService.complete(taskId);
    return true;
  }
}
