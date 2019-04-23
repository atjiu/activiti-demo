<#macro header page_tab>
  <nav class="navbar navbar-default" role="navigation">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/">Activiti</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
          <#if _user?? && _user.rank == "员工">
            <li <#if page_tab == 'myAskLeaves'>class="active"</#if>>
              <a href="/askLeave/list">我的请假</a>
            </li>
          </#if>
          <#if _user?? && _user.username != "admin">
            <li <#if page_tab == 'myTasks'>class="active"</#if>>
              <a href="/task/list">我的任务</a>
            </li>
          </#if>
          <#if _user?? && _user.rank == "管理员">
          <li class="dropdown" <#if page_tab == 'workflow'>class="active"</#if>>
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">工作流 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="/workflow/processDefinitions">流程定义列表</a></li>
              <li><a href="/workflow/processInstances">流程实例列表</a></li>
              <li><a href="/workflow/processDeploys">流程部署列表</a></li>
            </ul>
          </li>
          <li <#if page_tab == 'user'>class="active"</#if>><a href="/user/list">用户管理</a></li>
          </#if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <#if _user??>
            <li>
              <a href="javascript:;">${_user.username}</a>
            </li>
            <li>
              <a href="/logout">登出</a>
            </li>
          <#else>
            <li <#if page_tab == 'login'>class="active"</#if>>
              <a href="/login">登录</a>
            </li>
          <#--<li <#if page_tab == 'register'>class="active"</#if>>-->
          <#--<a href="/register">注册</a>-->
          <#--</li>-->
          </#if>
        </ul>
      </div>
    </div><!-- /.navbar-collapse -->
  </nav>
</#macro>
