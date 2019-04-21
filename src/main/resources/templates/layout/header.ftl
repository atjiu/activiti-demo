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
        <a class="navbar-brand" href="/">Activiti Demo</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
          <li <#if page_tab == 'index'>class="active"</#if>>
            <a href="/">My Tasks</a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <#if _user??>
            <li>
              <a href="javascript:;">${_user.username}</a>
            </li>
            <li>
              <a href="/logout">Logout</a>
            </li>
          <#else>
            <li <#if page_tab == 'login'>class="active"</#if>>
              <a href="/login">Login</a>
            </li>
            <li <#if page_tab == 'register'>class="active"</#if>>
              <a href="/register">Register</a>
            </li>
          </#if>
        </ul>
      </div>
    </div><!-- /.navbar-collapse -->
  </nav>
</#macro>