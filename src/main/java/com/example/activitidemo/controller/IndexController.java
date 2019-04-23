package com.example.activitidemo.controller;

import com.example.activitidemo.model.User;
import com.example.activitidemo.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

  @Autowired
  private TaskService taskService;
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/myTasks")
  public String myTasks(Model model) {
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(getUser().getUsername()).list();
    model.addAttribute("tasks", tasks);
    return "myTasks";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }


  @PostMapping("/login")
  @ResponseBody
  public Object login(User user, HttpSession session) {
    user = userService.login(user);
    session.setAttribute("_user", user);
    return user;
  }

  //  @GetMapping("/register")
  //  public String register() {
  //    return "register";
  //  }

  //  @PostMapping("/register")
  //  public Object register(User user, HttpSession session) {
  //    user = userService.save(user);
  //    session.setAttribute("_user", user);
  //    return user;
  //  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("_user");
    return "redirect:/";
  }
}
