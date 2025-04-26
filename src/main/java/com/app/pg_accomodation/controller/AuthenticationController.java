package com.app.pg_accomodation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.app.pg_accomodation.entity.User;
import com.app.pg_accomodation.model.AuthRequest;
import com.app.pg_accomodation.model.UserDto;
import com.app.pg_accomodation.service.JwtService;
import com.app.pg_accomodation.service.UserInfoService;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.access.prepost.PreAuthorize;

import com.app.pg_accomodation.service.UserInfoDetails;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        log.info("User Registration Request: {}", userDto);
        try {
            // Register the user
            userService.registerUser(userDto);

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );

            // If authentication is successful, generate the token
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
                String token = jwtService.generateToken(userDetails);

                
                // Load the user by username (email or username check)
                User registeredUser = userService.getUserByEmail(userDto.getEmail());

                // Prepare response map
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", registeredUser);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed after signup");
            }
        } catch (Exception e) {
            log.error("Signup failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed. Please try again later.");
        }
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    try {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
            String token = jwtService.generateToken(userDetails);


            // Fetch full user details to send back role and ID
            User user = userService.getUserByEmail(authRequest.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    } catch (UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
    }
}


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
