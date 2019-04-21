package com.example.activitidemo.repository;

import com.example.activitidemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByRank(String rank);
}
