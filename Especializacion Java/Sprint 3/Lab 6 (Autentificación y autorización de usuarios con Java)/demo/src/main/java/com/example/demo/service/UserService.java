package com.example.demo.service;

import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;

public interface UserService {

  public UserEntity save(UserEntity newUser);
}
