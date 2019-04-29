package com.example.besteducation.v1.dao;

import com.example.besteducation.v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
    User findById(Long id);
}
