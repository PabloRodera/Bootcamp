package com.prodera.CarRegistry.service;

import com.prodera.CarRegistry.repository.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public UserEntity save(UserEntity newUser);
    void storeUserImage(Long id, MultipartFile imageFile) throws IOException;
    byte[] getUserImage(Long id);
}
