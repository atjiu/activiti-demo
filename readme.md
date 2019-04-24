```sql
create database `activiti-demo` CHARACTER SET 'utf8';
INSERT INTO `activiti-demo`.`user`(`id`, `in_time`, `password`, `rank`, `username`, `leader_id`) VALUES (1, '2019-04-22 19:14:12', '123123', '管理员', 'admin', NULL);
```

注意

1. springboot里部署流程会自动将流程图片的名字改成 `流程.流程.png` 比如流程图名是 `aa.bpmn` 那么在项目启动时流程图片的名字在数据库里就是 `aa.aa.png`
