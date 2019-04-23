<#include "../layout/layout.ftl"/>
<@html page_title="流程管理" page_tab="workflow">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">流程定义</li>
  </ol>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>名称</th>
      <th>描述</th>
      <th>Key</th>
      <th>部署ID</th>
    </tr>
    </thead>
    <tbody>
    <#list definitions as item>
    <tr>
      <td>${item.id!}</td>
      <td>${item.name!}</td>
      <td>${item.description!}</td>
      <td>${item.key!}</td>
      <td>${item.deploymentId!}</td>
    </tr>
    </#list>
    </tbody>
  </table>
</@html>
