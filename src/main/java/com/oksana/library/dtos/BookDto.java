package com.oksana.library.dtos;

import com.oksana.library.Language;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookDto {

    private Long id;

    private String name;

    private String description;

    private String language;

    private List<AuthorDto> authorDtos = new ArrayList<>();

    private List<GenreDto> genreDtos = new ArrayList<>();
}
