package com.hope.hope.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private boolean isDoctor;
    private boolean isFunctionary;
}
