package com.prodera.CarRegistry.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtServiceTest {

    @Mock
    private JwtService jwtService;

    private static final String jwtSecretKey = "ZXN0b2RlYmVyaWFzZGVleHBsaWNhcmxvbWVqb3Jwb3JxdWVub3F1ZWRhbmFkYWNsYXJv";
    private static final Long jwtExpirationMs = 3600000L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateToken() {
        when(jwtService.generateToken(any(), any())).thenCallRealMethod();

        UserDetails userDetails = new User("tester", "password", null);
        String token = jwtService.generateToken(new HashMap<>(), userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUserName() {
        String username = "tester";
        String token = generateJwtToken(username);

        when(jwtService.extractUserName(any())).thenCallRealMethod();

        String extractedUserName = jwtService.extractUserName(token);

        assertEquals(username, extractedUserName);
    }

    @Test
    void isTokenValid_validToken() {
        String username = "tester";
        String token = generateJwtToken(username);

        when(jwtService.isTokenValid(any(), any())).thenCallRealMethod();

        UserDetails userDetails = new User(username, "password", null);
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void isTokenValid_invalidToken() {
        String username = "tester";
        String token = generateExpiredJwtToken(username);

        when(jwtService.isTokenValid(any(), any())).thenCallRealMethod();

        UserDetails userDetails = new User(username, "password", null);
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertFalse(isValid);
    }

    private String generateJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateExpiredJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // Expired token
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
