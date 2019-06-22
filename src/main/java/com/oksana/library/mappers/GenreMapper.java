package com.oksana.library.mappers;

import com.oksana.library.dtos.GenreDto;
import com.oksana.library.entities.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Genre mapToEntity(GenreDto genreDto){
        Genre entity = this.modelMapper.map(genreDto, Genre.class);
        return entity;
    }
    public GenreDto mapToDto(Genre genre){
        GenreDto dto = this.modelMapper.map(genre, GenreDto.class);
        return dto;
    }
}
