package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.repository.entity.UserEntity;
import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserEntity save(UserEntity newUser) {
        return userRepository.save(newUser);
    }
}