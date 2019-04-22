package com.example.activitidemo.controller;

import com.example.activitidemo.config.SiteConfig;
import com.example.activitidemo.model.User;
import com.example.activitidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tomoya at 2019/4/22
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private SiteConfig siteConfig;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("users", userService.findAll());
    model.addAttribute("ranks", siteConfig.getRanks());
    List<User> leaders = userService.findLeaders();
    leaders = leaders.stream().filter(item -> !item.getUsername().equals("admin")).collect(Collectors.toList());
    model.addAttribute("leaders", leaders);
    return "user/list";
  }

  @PostMapping("/createUser")
  @ResponseBody
  public Object createUser(String username, String password, String rank, String leader) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setInTime(new Date());
    user.setRank(rank);
    if (!StringUtils.isEmpty(leader)) {
      User leaderUser = userService.findByUsername(leader);
      user.setLeader(leaderUser);
    }
    userService.save(user);
    return user;
  }

  @PostMapping("/editUser")
  @ResponseBody
  public Object editUser(Integer id, String username, String password, String rank, String leader) {
    User user = userService.findById(id);
    user.setUsername(username);
    if (!StringUtils.isEmpty(password)) user.setPassword(password);
    user.setRank(rank);
    if (!StringUtils.isEmpty(leader)) {
      User leaderUser = userService.findByUsername(leader);
      user.setLeader(leaderUser);
    }
    userService.save(user);
    return user;
  }

  @PostMapping("/deleteUser")
  @ResponseBody
  public Object deleteUser(Integer id) {
    userService.deleteById(id);
    return true;
  }

}
