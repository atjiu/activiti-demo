package com.example.activitidemo.controller;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.service.AskLeaveService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/askLeave")
public class AskLeaveApiController extends BaseController {

  @Autowired
  private AskLeaveService askLeaveService;
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;

  @PostMapping("/list")
  public Object list() {
    return askLeaveService.findByUser(getUser());
  }

  @PostMapping("/add")
  public Object add(AskLeave askLeave) {
    askLeave.setUser(getUser());
    askLeave.setInTime(new Date());
    return askLeaveService.save(askLeave);
  }

  @PostMapping("/commit")
  public Object commit(Integer id) {
    AskLeave askLeave = askLeaveService.findById(id);
    Map<String, Object> variables = new HashMap<>();
    variables.put("username", askLeave.getUser().getUsername());
    variables.put("pass", "1");
    runtimeService.startProcessInstanceByKey("AskLeave", String.valueOf(id), variables);
    // 更改请假状态
    askLeave.setStatus("提交");
    askLeaveService.save(askLeave);
    return true;
  }

}
