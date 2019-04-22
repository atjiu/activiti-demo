package com.example.activitidemo.controller;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

  @Autowired
  private TaskService taskService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/myAskLeaves")
  public String myAskLeaves() {
    return "myAskLeaves";
  }

  @GetMapping("/myTasks")
  public String myTasks(Model model) {
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(getUser().getUsername()).list();
    model.addAttribute("tasks", tasks);
    return "myTasks";
  }

  @GetMapping("/processDefinitions")
  public String processDefinitions() {
    return "processDefinitions";
  }

  @GetMapping("/processInstances")
  public String processInstances() {
    return "processInstances";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  //  @GetMapping("/register")
  //  public String register() {
  //    return "register";
  //  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("_user");
    return "redirect:/";
  }
}
