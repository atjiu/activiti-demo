<#include "../layout/layout.ftl"/>
<@html page_title="流程管理" page_tab="workflow">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">流程实例</li>
  </ol>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>实例ID</th>
      <th>定义ID</th>
      <th>定义Key</th>
      <th>描述</th>
      <th>启动时间</th>
      <th>部署ID</th>
    </tr>
    </thead>
    <tbody>
    <#list instances as item>
    <tr>
      <td>${item.processInstanceId!}</td>
      <td>${item.processDefinitionId!}</td>
      <td>${item.processDefinitionKey!}</td>
      <td>${item.description!}</td>
      <td>${item.startTime?string('yyyy-MM-dd HH:mm:ss')}</td>
      <td>${item.deploymentId!}</td>
    </tr>
    </#list>
    </tbody>
  </table>
</@html>
