package com.example.activitidemo.service;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.model.User;
import com.example.activitidemo.repository.AskLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tomoya at 2019/4/22
 */
@Service
@Transactional
public class AskLeaveService {

  @Autowired
  private AskLeaveRepository askLeaveRepository;

  public AskLeave save(AskLeave askLeave) {
    return askLeaveRepository.save(askLeave);
  }

  public List<AskLeave> findByUser(User user) {
    return askLeaveRepository.findByUser(user);
  }

  public AskLeave findById(Integer id) {
    return askLeaveRepository.findById(id).orElse(null);
  }
}
