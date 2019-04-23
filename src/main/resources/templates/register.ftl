<#include "layout/layout.ftl"/>
<@html page_title="Register" page_tab="register">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <form action="" onsubmit="return;" method="post" role="form">
        <legend>Register</legend>

        <div class="form-group">
          <label for="">Username</label>
          <input type="text" class="form-control" name="uername" id="username" placeholder="Input...">
        </div>
        <div class="form-group">
          <label for="">Password</label>
          <input type="password" class="form-control" name="password" id="password" placeholder="Input...">
        </div>
        <div class="form-group">
          <label for="">Rank</label>
          <select name="" id="rank" class="form-control">
            <option value="员工">员工</option>
            <option value="部门经理">部门经理</option>
            <option value="总经理">总经理</option>
            <option value="管理员">管理员</option>
          </select>
        </div>

        <button type="button" id="btn" class="btn btn-primary">Submit</button>
      </form>
    </div>
  </div>

  <script>
    $(function () {
      $("#btn").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var rank = $("#rank").val();

        $.post('/register', {username: username, password: password, rank: rank}, function (data) {
          if (data) {
            window.location.href = "/"
          }
        })
      })
    })
  </script>
</@html>
