package com.prodera.CarRegistry.service;

import com.prodera.CarRegistry.repository.entity.UserEntity;

public interface UserService {
    public UserEntity save(UserEntity newUser);
}
