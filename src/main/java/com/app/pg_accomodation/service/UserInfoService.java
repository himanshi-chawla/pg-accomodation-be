package com.app.pg_accomodation.service;

import com.app.pg_accomodation.entity.User;
import com.app.pg_accomodation.model.UserDto;
import com.app.pg_accomodation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signup(UserDto userDto) {
        try {
            //check if email exists, if yes then return UserAlreadyExists message
            if(userRepository.existsByEmail(userDto.getEmail())){
                return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
            }
            User user = mapUserDtoToUser(userDto);
            userRepository.save(user);
            //if not then signup the user i.e. add the user to the database
            return new ResponseEntity<>("User data saved successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while saving user data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userDetail = userRepository.findByEmail(username); // Assuming 'email' is used as username

        // Converting User to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    private User mapUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found for email : " + email));
    }
}
