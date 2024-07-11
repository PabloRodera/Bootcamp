package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setEmail("test@example.com");
        userEntity1.setPassword("password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity1));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity1);
    }

    @Test
    void userDetailsService_existingUser() {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        // Example of converting UserDetails to UserEntity if necessary
        if (userDetails instanceof UserEntity foundUser) {
            assertEquals(userEntity1.getId(), foundUser.getId());
            assertEquals(userEntity1.getEmail(), foundUser.getEmail());
            assertEquals(userEntity1.getPassword(), foundUser.getPassword());
        }

        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void userDetailsService_userNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.userDetailsService().loadUserByUsername("nonexistent@example.com"));

        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void saveUser() {
        UserEntity newUser = new UserEntity();
        newUser.setId(2L);
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("newpassword");

        UserEntity savedUser = userService.save(newUser);

        assertNotNull(savedUser);
        assertEquals("newuser@example.com", savedUser.getEmail());
        assertEquals("newpassword", savedUser.getPassword());

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}