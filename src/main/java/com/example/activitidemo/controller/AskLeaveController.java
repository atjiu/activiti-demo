package com.example.activitidemo.controller;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.service.AskLeaveService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/askLeave")
public class AskLeaveController extends BaseController {

  @Autowired
  private AskLeaveService askLeaveService;
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private HistoryService historyService;

  @GetMapping("/list")
  public String list(Model model) {
    List<Map<String, Object>> list = new ArrayList<>();
    List<AskLeave> askLeaves = askLeaveService.findByUser(getUser());
    for (AskLeave askLeave : askLeaves) {
      Map<String, Object> map = new HashMap<>();
      map.put("askLeave", askLeave);
      // 查询历史实例
      HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
          .processInstanceBusinessKey(String.valueOf(askLeave.getId())).singleResult();
      if (historicProcessInstance != null) {
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
            .processInstanceBusinessKey(String.valueOf(askLeave.getId())).list();
        if (!historicTaskInstances.isEmpty()) {
          List<Comment> comments = new ArrayList<>();
          for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            comments.addAll(taskService.getTaskComments(historicTaskInstance.getId()));
          }
          map.put("comments", comments);
        }
      }
      list.add(map);
    }
    model.addAttribute("askLeaves", list);
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
    variables.put("username", getUser().getUsername());
    variables.put("pass", "1");
    // 启动流程
    ProcessInstance instance = runtimeService.startProcessInstanceByKey("AskLeave", String.valueOf(id), variables);
    Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
    // 增加批注
    Authentication.setAuthenticatedUserId(getUser().getUsername());
    taskService.addComment(task.getId(), instance.getId(), "[提交请假]");
    // 自己的任务自动完成
    variables.put("username", askLeave.getUser().getLeader().getUsername());
    taskService.complete(task.getId(), variables);
    // 更改请假状态
    askLeave.setStatus("提交");
    askLeaveService.save(askLeave);
    return true;
  }


}
