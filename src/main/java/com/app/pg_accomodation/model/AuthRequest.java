package com.app.pg_accomodation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    private String email;
    private String password;
}
