package com.example.activitidemo.service;

import com.example.activitidemo.model.User;
import com.example.activitidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public User findByRank(String rank) {
    return userRepository.findByRank(rank);
  }

}
