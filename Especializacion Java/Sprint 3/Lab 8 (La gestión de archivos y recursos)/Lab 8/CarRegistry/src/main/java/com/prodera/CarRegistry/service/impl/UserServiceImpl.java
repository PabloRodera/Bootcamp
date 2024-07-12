package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.repository.entity.UserEntity;
import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }
    @Override
    public void storeUserImage(Long id, MultipartFile imageFile) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        log.info("Saving user image...");
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        user.setImage(imageBytes);
        userRepository.save(user);
    }

    @Override
    public byte[] getUserImage(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getImage() != null && user.getImage().length > 0) {
            return user.getImage();
        } else {
            return null;
        }
    }
}