package com.app.pg_accomodation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;


        public UserDto(String firstName, String username, String lastName, String email, String password, String role, String phoneNumber) {
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }
}
