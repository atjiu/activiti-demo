package com.example.activitidemo.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tomoya at 2019/4/22
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowController extends BaseController {

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

  @GetMapping("/processDeploys")
  public String processDeploys(Model model) {
    List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
    model.addAttribute("deployments", deployments);
    return "workflow/processDeploys";
  }

  @GetMapping("/processDefinitions")
  public String processDefinitions(Model model) {
    List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().list();
    model.addAttribute("definitions", definitions);
    return "workflow/processDefinitions";
  }

  @GetMapping("/processInstances")
  public String processInstances(Model model) {
    List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
    model.addAttribute("instances", instances);
    return "workflow/processInstances";
  }

}
