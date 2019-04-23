<#include "layout/layout.ftl"/>
<@html page_title="我的任务" page_tab="myAskLeaves">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">我的请假</li>
  </ol>
  <a class="btn btn-primary btn-sm pull-right" data-toggle="modal" style="margin-bottom: 20px" href="#modal-id">新增请假</a>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>标题</th>
      <th>描述</th>
      <th>天数</th>
      <th>状态</th>
      <th>日期</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list askLeaves as item>
    <tr>
      <td>${item.id?c}</td>
      <td>${item.title!}</td>
      <td>${item.description!}</td>
      <td>${item.day!}</td>
      <td>${item.status!}</td>
      <td>${item.inTime!}</td>
      <td>
        <#if item.status == "创建">
          <button class="btn btn-xs btn-primary" onclick="publishAskLeave(${item.id?c})">提交请假</button>
        </#if>
      </td>
    </tr>
    </#list>
    </tbody>
  </table>

  <div class="modal fade" id="modal-id">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">新增请假</h4>
        </div>
        <div class="modal-body">
          <form action="" onsubmit="return;" id="form" method="post" role="form">

            <div class="form-group">
              <label for="">标题</label>
              <input type="text" class="form-control" name="" id="title" placeholder="标题">
            </div>
            <div class="form-group">
              <label for="">描述</label>
              <input type="text" class="form-control" name="" id="description" placeholder="描述">
            </div>
            <div class="form-group">
              <label for="">天数</label>
              <input type="number" class="form-control" name="" id="day" placeholder="天数">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button type="button" id="btn" class="btn btn-primary">提交</button>
        </div>
      </div>
    </div>
  </div>

  <script>

    $("#btn").click(function () {
      var title = $("#title").val();
      var description = $("#description").val();
      var day = $("#day").val();
      $.post("/api/askLeave/add", {
        title: title,
        description: description,
        status: '创建',
        day: day
      }, function () {
        window.location.reload();
      })
    });

    function publishAskLeave(id) {
      if (confirm("确定要提交请假吗？")) {
        $.post("/askLeave/commit", {id: id}, function () {
          window.location.reload();
        })
      }
    }
  </script>
</@html>
