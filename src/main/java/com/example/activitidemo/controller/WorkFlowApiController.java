package com.example.activitidemo.controller;

import com.example.activitidemo.model.vo.MyDeploymentEntity;
import com.example.activitidemo.model.vo.MyProcessDefinitionEntity;
import com.example.activitidemo.model.vo.MyProcessInstanceEntity;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoya at 2019/4/22
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkFlowApiController extends BaseController {

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

  @PostMapping("/queryProcessDeploy")
  public Object queryProcessDeploy() {
    List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
    List<MyDeploymentEntity> list = new ArrayList<>();
    for (Deployment deployment : deployments) {
      MyDeploymentEntity myDeploymentEntity = new MyDeploymentEntity();
      BeanUtils.copyProperties(deployment, myDeploymentEntity);
      list.add(myDeploymentEntity);
    }
    return list;
  }

  @PostMapping("/queryProcessDefinition")
  public Object queryProcessDefinition() {
    List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().list();
    List<MyProcessDefinitionEntity> list = new ArrayList<>();
    for (ProcessDefinition definition : definitions) {
      MyProcessDefinitionEntity myProcessDefinitionEntity = new MyProcessDefinitionEntity();
      BeanUtils.copyProperties(definition, myProcessDefinitionEntity);
      list.add(myProcessDefinitionEntity);
    }
    return list;
  }

  @PostMapping("/queryProcessInstances")
  public Object queryProcessInstances() {
    List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
    List<MyProcessInstanceEntity> list = new ArrayList<>();
    for (ProcessInstance instance : instances) {
      MyProcessInstanceEntity myProcessInstanceEntity = new MyProcessInstanceEntity();
      BeanUtils.copyProperties(instance, myProcessInstanceEntity);
      list.add(myProcessInstanceEntity);
    }
    return list;
  }

}
