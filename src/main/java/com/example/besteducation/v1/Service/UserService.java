package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.domain.User;

public interface UserService {
    User findUserByUsernameAndPassword(String username,String password);
    User findUserByUsername(String username);
    User createUser(User user);
    User findById(Long id);
}
