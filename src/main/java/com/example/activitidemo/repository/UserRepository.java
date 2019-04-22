package com.example.activitidemo.repository;

import com.example.activitidemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByRank(String rank);

  User findByUsername(String username);

  List<User> findByLeader(String leader);

  List<User> findByRankIn(List<String> ranks);

}
