package com.homework.first.model;

import lombok.Data;

@Data
public class JwtResponse {
    private final User user;
    private final String token;
}