
> 一个简单的使用Activiti实现的请假流程项目

![](/assets/QQ20190425-180744.png)

## 功能

- 查看流程图
- 查看流程处理批注
- 驳回

## 启动

1. git clone 项目
2. 创建数据库(创建脚本在下面)
3. 启动项目(这一步会创建表)
4. 执行下面初始化sql语句
5. 重启项目, 使用员工角色登录系统
6. 创建请假流程并提交申请
7. 换成部门经理登录, 查看我的任务处理任务
8. 换成总经理登录, 查看我的任务处理任务
9. 员工重新登录, 查看请假流程的处理结果以及批注信息

---

执行下面sql创建数据库

```sql
create database `activiti-demo` CHARACTER SET 'utf8';
```

执行下面sql初始化用户关系

```sql
INSERT INTO `activiti-demo`.`user`(`id`, `in_time`, `password`, `rank`, `username`, `leader_id`) VALUES (1, '2019-04-22 19:14:12', '123123', '管理员', 'admin', NULL);
INSERT INTO `user` (`id`, `password`, `rank`, `username`, `leader_id`, `in_time`)
VALUES
	(1, '123123', '管理员', 'admin', NULL, '2019-04-22 14:19:23'),
	(2, '123123', '部门经理', 'user2', 3, '2019-04-22 14:19:23'),
	(3, '123123', '总经理', 'user1', NULL, '2019-04-22 14:19:23'),
	(5, '123123', '员工', 'user3', 2, '2019-04-22 14:21:38'),
	(6, '123123', '员工', 'user4', 2, '2019-04-22 14:22:56');
```

## 博客

关于 Activiti 的相关博客可以查看 [activiti学习笔记](https://tomoya92.github.io/category/#activiti%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0)

## 贡献

本项目现在只内置了一个请假流程的实现，如果你对流程控制感兴趣，或者正在学习，欢迎以本项目为例开发其它功能的流程，然后pr到本项目
