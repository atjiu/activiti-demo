<#include "layout/layout.ftl"/>
<@html page_title="Login" page_tab="login">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <form action="" onsubmit="return;" method="post" role="form">
        <legend>Login</legend>

        <div class="form-group">
          <label for="">Username</label>
          <input type="text" class="form-control" name="uername" id="username" placeholder="Input...">
        </div>
        <div class="form-group">
          <label for="">Password</label>
          <input type="password" class="form-control" name="password" id="password" placeholder="Input...">
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

        $.post('/api/login', {username: username, password: password}, function (data) {
          if (data) {
            window.location.href = "/"
          }
        })
      })
    })
  </script>
</@html>