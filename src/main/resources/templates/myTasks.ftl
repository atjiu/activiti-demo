<#include "layout/layout.ftl"/>
<@html page_title="我的任务" page_tab="myTasks">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">我的任务</li>
  </ol>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>任务ID</th>
      <th>任务名</th>
      <th>描述</th>
      <th>执行ID</th>
      <th>实例ID</th>
      <th>定义ID</th>
      <th>任务定义Key</th>
      <th>创建时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list tasks as item>
    <tr>
      <td>${item.id!}</td>
      <td>${item.name!}</td>
      <td>${item.description!}</td>
      <td>${item.executionId!}</td>
      <td>${item.processInstanceId!}</td>
      <td>${item.processDefinitionId!}</td>
      <td>${item.taskDefinitionKey!}</td>
      <td>${item.createTime?datetime}</td>
      <td>
        <button class="btn btn-xs btn-primary" onclick="review('${item.id}')">审批</button>
      </td>
    </tr>
    </#list>
    </tbody>
  </table>

  <a data-toggle="modal" href="#modal-id" id="showModalBtn" class="hidden"></a>
  <div class="modal fade" id="modal-id">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">审批请假</h4>
        </div>
        <div class="modal-body">
          <form action="" onsubmit="return;" id="form" method="post" role="form">

            <input type="hidden" id="taskId" value=""/>

            <div class="form-group">
              <label for="">批注</label>
              <textarea name="" id="content" class="form-control" rows="4"></textarea>
            </div>

            <table class="table table-bordered table-hover">
              <thead>
              <tr>
                <th>时间</th>
                <th>批注人</th>
                <th>内容</th>
              </tr>
              </thead>
              <tbody id="tbody">
              </tbody>
            </table>
          </form>
        </div>
        <div class="modal-footer">
        <#--这地方的按钮应该从流程图里获取-->
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button type="button" onclick="completeTask()" class="btn btn-primary">通过</button>
        </div>
      </div>
    </div>
  </div>

  <script>
    function review(id) {
      $.post('/task/queryTaskComments', {taskId: id}, function (data) {
        $("#tbody").html('');
        $.each(data, function (index, item) {
          $("#tbody").append('<tr>' +
              '<td>' + formatDate(item.time) + '</td>' +
              '<td>' + item.userId + '</td>' +
              '<td>' + item.message + '</td>' +
              '</tr>')
        })
        $("#taskId").val(id);
        $("#showModalBtn").click();
      })
    }

    function completeTask() {
      $.post('/task/completeTask', {
        taskId: $("#taskId").val(),
        content: $("#content").val()
      }, function (data) {
        window.location.reload();
      })
    }
  </script>
</@html>
