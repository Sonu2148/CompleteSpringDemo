package com.example.CompleteSpringDemo.repository;

import com.example.CompleteSpringDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this is a repository interface
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByIdAndActive(int id,boolean active);
    // JpaRepository provides built-in CRUD methods
}
