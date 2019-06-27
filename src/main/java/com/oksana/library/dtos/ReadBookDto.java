package com.oksana.library.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReadBookDto {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime date;

}
