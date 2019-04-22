<#include "layout/layout.ftl"/>
<@html page_title="WorkFlow" page_tab="workflow">

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
    <tbody id="tbody">
    </tbody>
  </table>

  <script>

    loadProcessDefinitions();

    function loadProcessDefinitions() {
      $.post("/api/workflow/queryProcessDefinition", function (data) {
        console.log(data);
        $("#tbody").html('');
        $.each(data, function (index, item) {
          $("#tbody").append('<tr>' +
              '<td>' + item.id + '</td>' +
              '<td>' + item.name + '</td>' +
              '<td>' + item.description + '</td>' +
              '<td>' + item.key + '</td>' +
              '<td>' + item.deploymentId + '</td>' +
              '</tr>');
        });
      });
    }

    function deploymentProcess(definitionId) {
      $.post('/api/workflow/deploymentProcess', {definitionId: definitionId}, function (data) {
        console.log(data);
        loadProcessDefinitions();
      })
    }
  </script>
</@html>
