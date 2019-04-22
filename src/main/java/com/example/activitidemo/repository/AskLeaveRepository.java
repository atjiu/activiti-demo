package com.example.activitidemo.repository;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by tomoya at 2019/4/22
 */
public interface AskLeaveRepository extends JpaRepository<AskLeave, Integer> {
  List<AskLeave> findByUser(User user);
}
