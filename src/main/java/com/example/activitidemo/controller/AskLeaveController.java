package com.example.activitidemo.controller;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.service.AskLeaveService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/askLeave")
public class AskLeaveController extends BaseController {

  @Autowired
  private AskLeaveService askLeaveService;
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private HistoryService historyService;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("askLeaves", askLeaveService.findByUser(getUser()));
    return "myAskLeaves";
  }

  @PostMapping("/add")
  @ResponseBody
  public Object add(AskLeave askLeave) {
    askLeave.setUser(getUser());
    askLeave.setInTime(new Date());
    return askLeaveService.save(askLeave);
  }

  @PostMapping("/commit")
  @ResponseBody
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
