<#include "../layout/layout.ftl"/>
<@html page_title="流程管理" page_tab="workflow">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">流程部署</li>
  </ol>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>名称</th>
      <th>类别</th>
      <th>Key</th>
      <th>部署时间</th>
    </tr>
    </thead>
    <tbody>
    <#list deployments as item>
    <tr>
      <td>${item.id!}</td>
      <td>${item.name!}</td>
      <td>${item.category!}</td>
      <td>${item.key!}</td>
      <td>${item.deploymentTime?string('yyyy-MM-dd HH:mm:ss')}</td>
    </tr>
    </#list>
    </tbody>
  </table>
</@html>
