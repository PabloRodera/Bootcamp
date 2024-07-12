package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.LoginResponse;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;
import com.prodera.CarRegistry.service.AuthenticationService;
import com.prodera.CarRegistry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody SignUpRequest request){
        try {
            return ResponseEntity.ok(authenticationService.signup(request));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/{id}/addImage")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public ResponseEntity<String> uploadUserImage(@PathVariable Long id, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            userService.storeUserImage(id, imageFile);
            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }

    @GetMapping("/{id}/image")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long id) {
        byte[] imageData = userService.getUserImage(id);

        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
