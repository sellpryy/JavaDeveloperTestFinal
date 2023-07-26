package com.example.javadevelopertest.repository;


import com.example.javadevelopertest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
