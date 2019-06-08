package com.oksana.library.dtos;

import com.oksana.library.Language;
import lombok.Data;

import javax.persistence.Column;

@Data
public class BookDto {

    private Long id;

    private String name;

    private String description;

    private String language;
}
