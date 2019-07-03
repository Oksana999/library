package com.oksana.library.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ReadBookDto {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime date;

}
