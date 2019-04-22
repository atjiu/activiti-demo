package com.example.activitidemo.service;

import com.example.activitidemo.model.User;
import com.example.activitidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User login(User user) {
    User byUsername = userRepository.findByUsername(user.getUsername());
    if (byUsername != null && byUsername.getPassword().equals(user.getPassword())) return byUsername;
    return null;
  }

  public User findById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public List<User> findByRank(String rank) {
    return userRepository.findByRank(rank);
  }

  public Object findAll() {
    return userRepository.findAll();
  }

  public User findByUsername(String leader) {
    return userRepository.findByUsername(leader);
  }

  public List<User> findLeaders() {
    return userRepository.findByRankIn(Arrays.asList("总经理", "部门经理"));
  }

  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }
}
