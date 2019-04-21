<#include "layout/layout.ftl"/>
<@html page_title="My Tasks" page_tab="index">

  <ol class="breadcrumb">
    <li>
      <a href="/">Home</a>
    </li>
    <li class="active">My Tasks</li>
  </ol>
  <a class="btn btn-primary btn-sm pull-right" data-toggle="modal" style="margin-bottom: 20px" href="#modal-id">Create
    Task</a>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Desc</th>
      <th>Date</th>
      <th>Options</th>
    </tr>
    </thead>
    <tbody id="tasksTable">
    <tr>
      <td></td>
    </tr>
    </tbody>
  </table>

  <div class="modal fade" id="modal-id">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Create Task</h4>
        </div>
        <div class="modal-body">
          <form action="" onsubmit="return;" method="post" role="form">

            <div class="form-group">
              <label for="">Title</label>
              <input type="text" class="form-control" name="" id="title" placeholder="Input...">
            </div>
            <div class="form-group">
              <label for="">Desc</label>
              <input type="text" class="form-control" name="" id="desc" placeholder="Input...">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" id="btn" class="btn btn-primary">Submit</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <script>
    $(function () {
      loadTasks();

      $("#btn").click(function () {
        var title = $("#title").val();
        var desc = $("#desc").val();
        $.post("/api/createTask", {title: title, desc: desc}, function (data) {
          // if (data) loadTasks();
          console.log(data)
        })
      });

      function loadTasks() {
        $.post("/api/tasks", function (data) {
          console.log(data);
        })
      }
    })
  </script>
</@html>