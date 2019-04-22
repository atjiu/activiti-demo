<#include "../layout/layout.ftl"/>
<@html page_title="用户管理" page_tab="user">

  <ol class="breadcrumb">
    <li>
      <a href="/">主页</a>
    </li>
    <li class="active">用户管理</li>
  </ol>

  <button class="btn btn-sm btn-primary pull-right" data-toggle="modal" style="margin-bottom: 20px" href="#modal-id"
          id="addUserBtn">
    添加员工
  </button>

    <table class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>职级</th>
        <th>领导</th>
        <th>时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <#list users as item>
      <tr id="tr_${item.id?c}">
        <td>${item.id?c}</td>
        <td>${item.username!}</td>
        <td>${item.rank!}</td>
        <td>
          <#if item.leader??>
            ${item.leader.username!}
          </#if>
        </td>
        <td>${item.inTime!}</td>
        <td>
          <button data-toggle="modal" onclick="editUser(${item.id?c})"
                  class="btn btn-xs btn-warning">编辑
          </button>&nbsp;
          <button class="btn btn-xs btn-danger" onclick="deleteUser(${item.id?c})">删除</button>
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
        <h4 class="modal-title">新增员工</h4>
      </div>
      <div class="modal-body">
        <form action="" onsubmit="return;" id="form" method="post" role="form">
          <input type="hidden" id="id" value=""/>
          <div class="form-group">
            <label for="">用户名</label>
            <input type="text" class="form-control" name="" id="username" placeholder="用户名">
          </div>
          <div class="form-group">
            <label for="">密码</label>
            <input type="password" class="form-control" name="" id="password" placeholder="密码">
          </div>

          <div class="form-group">
            <label for="">职级</label>
            <select name="" id="rank" class="form-control">
              <#list ranks as item>
                <option value="${item}">${item}</option>
              </#list>
            </select>
          </div>

          <div class="form-group">
            <label for="">领导</label>
            <select name="" id="leader" class="form-control">
              <option value="">--选择领导--</option>
              <#list leaders as item>
                <option value="${item.username!}">${item.username} - ${item.rank!}</option>
              </#list>
            </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" onclick="createUser()" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

  <script>

    function createUser() {
      var id = $("#id").val();
      var username = $("#username").val();
      var password = $("#password").val();
      var rank = $("#rank").val();
      var leader = $("#leader").val();
      var url = "/user/createUser";
      if (id) url = "/user/editUser";
      $.post(url, {
        id: id,
        username: username,
        password: password,
        rank: rank,
        leader: leader,
      }, function (data) {
        $("#form")[0].reset();
        window.location.reload();
      })
    }

    function editUser(id) {
      var id = $("#tr_" + id).find('td:eq(0)').text();
      var username = $("#tr_" + id).find('td:eq(1)').text();
      var rank = $("#tr_" + id).find('td:eq(2)').text();
      var leader = $("#tr_" + id).find('td:eq(3)').text();
      $("#id").val(id);
      $("#username").val(username.trim());
      $("#rank").val(rank.trim());
      $("#leader").val(leader.trim());
      $("#addUserBtn").click();
    }

    function deleteUser(id) {
      if (confirm("确定要删除吗？")) {
        $.post('/user/deleteUser', {id: id}, function (data) {
          window.location.reload();
        })
      }

    }
  </script>
</@html>
