package com.oksana.library.dtos;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String username;
    private String password;
}
