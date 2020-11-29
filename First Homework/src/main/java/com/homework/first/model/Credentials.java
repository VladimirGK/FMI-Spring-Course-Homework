package com.homework.first.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    @NotNull
    @Size(min = 2, max = 15)
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
}