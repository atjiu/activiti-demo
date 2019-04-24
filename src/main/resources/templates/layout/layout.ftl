<#macro html page_title page_tab>
  <!doctype html>
  <html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Activiti Demo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/layer/2.3/layer.js"></script>
    <script>
      function formatDate(d) {
        var date = new Date(d);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
      }
    </script>
  </head>
  <body>
  <#include "header.ftl"/>
  <@header page_tab=page_tab/>
  <div class="container">
    <#nested />
  </div>
  </body>
  </html>
</#macro>
