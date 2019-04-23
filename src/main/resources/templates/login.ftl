<#include "layout/layout.ftl"/>
<@html page_title="Login" page_tab="login">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <form action="" onsubmit="return;" method="post" role="form">
        <legend>登录</legend>

        <div class="form-group">
          <label for="">用户名</label>
          <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
        </div>
        <div class="form-group">
          <label for="">密码</label>
          <input type="password" class="form-control" name="password" id="password" placeholder="密码">
        </div>


        <button type="button" id="btn" class="btn btn-primary">提交</button>
      </form>

    </div>
  </div>

  <script>
    $(function () {
      $("#btn").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();

        $.post('/login', {username: username, password: password}, function (data) {
          if (data) {
            window.location.href = "/"
          }
        })
      })
    })
  </script>
</@html>
