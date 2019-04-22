<#include "layout/layout.ftl"/>
<@html page_title="WorkFlow" page_tab="workflow">

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
      <th>操作</th>
    </tr>
    </thead>
    <tbody id="tbody">
    </tbody>
  </table>

  <script>

    loadProcessInstances();

    function loadProcessInstances() {
      $.post("/api/workflow/queryProcessInstances", function (data) {
        console.log(data);
        $("#tbody").html('');
        $.each(data, function (index, item) {
          $("#tbody").append('<tr>' +
              '<td>' + item.processInstanceId + '</td>' +
              '<td>' + item.processDefinitionId + '</td>' +
              '<td>' + item.processDefinitionKey + '</td>' +
              '<td>' + item.description + '</td>' +
              '<td>' + item.startTime + '</td>' +
              '<td>' + item.deploymentId + '</td>' +
              '<td><button onclick="deploymentProcess(\'' + item.processInstanceId + '\')" class="btn btn-sm btn-primary hidden">部署</button></td>' +
              '</tr>');
        });
      });
    }

    function deploymentProcess(definitionId) {
      $.post('/api/workflow/deploymentProcess', {definitionId: definitionId}, function (data) {
        loadProcessInstances();
      })
    }
  </script>
</@html>
