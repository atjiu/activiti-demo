package com.example.activitidemo.controller;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.service.AskLeaveService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tomoya at 2019/4/22
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

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
  private AskLeaveService askLeaveService;

  @GetMapping("/list")
  public String list(Model model) {
    List<Map<String, Object>> list = new ArrayList<>();
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(getUser().getUsername()).list();
    for (Task task : tasks) {
      Map<String, Object> map = new HashMap<>();
      map.put("task", task);
      ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task
          .getProcessInstanceId()).singleResult();
      Integer askLeaveId = Integer.parseInt(processInstance.getBusinessKey());
      AskLeave askLeave = askLeaveService.findById(askLeaveId);
      if (getUser().getId().equals(askLeave.getUser().getId())) {
        map.put("myTask", true);
      } else {
        map.put("myTask", false);
      }
      map.put("askLeave", askLeave);
      // 查询批注信息
      List<Comment> comments = taskService.getProcessInstanceComments(task.getProcessInstanceId());
      map.put("comments", comments);
      list.add(map);
    }
    model.addAttribute("tasks", list);
    return "myTasks";
  }

  @PostMapping("/completeTask")
  @ResponseBody
  public Object completeTask(String taskId, String content, String pass, String giveup) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    String processInstanceId = task.getProcessInstanceId();
    // 拿到请假记录的id
    ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
        .singleResult();
    Integer askLeaveId = Integer.parseInt(instance.getBusinessKey());

    // 如果放弃了增加日志
    if (!StringUtils.isEmpty(giveup)) {
      content = "[" + giveup + "]" + content;
    }
    Authentication.setAuthenticatedUserId(getUser().getUsername());
    taskService.addComment(taskId, processInstanceId, content);
    Map<String, Object> variables = new HashMap<>();
    variables.put("pass", pass);
    if (getUser().getLeader() != null) {
      variables.put("username", getUser().getLeader().getUsername());
    }
    if (!pass.equals("1")) {
      // 找到上一个代理人并设置回去
      List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
          .orderByTaskCreateTime().asc().processInstanceId(task.getProcessInstanceId()).list();
      variables.put("username", historicTaskInstances.get(0).getAssignee());
    }
    taskService.complete(taskId, variables);
    // 判断流程是否走完 重新获取一次流程实例，如果为空 则表示流程结束了
    instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    if (instance == null) {
      // 更新请假状态
      AskLeave askLeave = askLeaveService.findById(askLeaveId);
      askLeave.setStatus(StringUtils.isEmpty(giveup) ? "通过" : giveup);
      askLeaveService.save(askLeave);
    }
    return true;
  }

  @GetMapping(value = "/queryProcessDefResource", produces = MediaType.IMAGE_PNG_VALUE)
  @ResponseBody
  public FileSystemResource queryProcessDefResource(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(task
        .getProcessInstanceId()).singleResult();
    ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionId(instance
        .getProcessDefinitionId()).singleResult();
    return new FileSystemResource(definition.getDiagramResourceName());
  }

  private Map<String, Object> queryTaskActivity(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(task
        .getProcessInstanceId()).singleResult();
    ProcessDefinitionEntity definition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(instance
        .getProcessDefinitionId());
    //    definition.fin
    return null;
  }

}
