package com.app.pg_accomodation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.app.pg_accomodation.model.AuthRequest;
import com.app.pg_accomodation.model.UserDto;
import com.app.pg_accomodation.service.JwtService;
import com.app.pg_accomodation.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        // invoke the service methods to implement logic to signup the user i.e. add the user
        // to the database
        return userInfoService.signup((userDto));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getEmail());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
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














