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
      <th>执行人</th>
      <th>时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list tasks as item>
    <tr>
      <td>${item.task.id!}</td>
      <td>${item.task.name!}</td>
      <td>${item.task.assignee!}</td>
      <td>${item.task.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
      <td>
        <button class="btn btn-xs btn-primary" onclick="review('${item.task.id}')">处理</button>&nbsp;
        <button class="btn btn-xs btn-info" onclick="showTaskProcessImage(${item.task.id})">显示流程图</button>
        <div class="hidden" id="taskProcessTable_${item.task.id}">
          <h4>请假信息</h4>
          <table class="table table-bordered table-hover">
            <thead>
            <tr>
              <th>请假人</th>
              <th>标题</th>
              <th>描述</th>
              <th>天数</th>
              <th>时间</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>${item.askLeave.user.username}</td>
              <td>${item.askLeave.title!}</td>
              <td>${item.askLeave.description!}</td>
              <td>${item.askLeave.day!}</td>
              <td>${item.askLeave.inTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            </tr>
            </tbody>
          </table>
          <textarea class="form-control" name="content" rows="3" placeholder="批注"></textarea>
          <h4>批注信息</h4>
          <table class="table table-bordered table-hover">
            <thead>
            <tr>
              <th>时间</th>
              <th>受理人</th>
              <th>批注</th>
            </tr>
            </thead>
            <tbody>
            <#list item.comments as comment>
            <tr>
              <td>${comment.time?string('yyyy-MM-dd HH:mm:ss')}</td>
              <td>${comment.userId!}</td>
              <td>${comment.message!}</td>
            </tr>
            </#list>
            </tbody>
          </table>
          <p class="text-center">
            <#if item.askLeave.user.username == _user.username>
              <button class="btn btn-sm btn-warning" onclick="completeTask('${item.task.id}', '0', '放弃')">放弃</button>
            </#if>
            <button type="button" onclick="completeTask('${item.task.id}', '1')" class="btn btn-sm btn-success">通过
            </button>
            <button type="button" onclick="completeTask('${item.task.id}', '0')" class="btn btn-sm btn-danger">驳回
            </button>
          </p>
        </div>
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
              <label for="">标题</label>
            </div>

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
          <button type="button" onclick="completeTask(undefined, '1')" class="btn btn-primary">通过</button>
          <button type="button" onclick="completeTask(undefined, '0')" class="btn btn-warning">驳回</button>
        </div>
      </div>
    </div>
  </div>

  <script>
    function review(id) {
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['600px', 'auto'], //宽高
        content: '<div style="padding: 20px;" id="review_div_' + id + '">' + $("#taskProcessTable_" + id).html() + '</div>'
      });
    }

    function completeTask(id, pass, giveup) {
      var content = $("#review_div_" + id).find('textarea[name="content"]').val();
      $.post('/task/completeTask', {
            taskId: id,
            content: content,
            pass: pass,
            giveup: giveup
          },
          function (data) {
            window.location.reload();
          }
      )
    }

    function showTaskProcessImage(id) {
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['600px', '600px'], //宽高
        content: '<img width="580" src="/task/queryProcessDefResource?taskId=' + id + '"/>'
      });
    }

  </script>
</@html>
