package com.oksana.library.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
