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
    private String id;
    private String firstName;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
}
