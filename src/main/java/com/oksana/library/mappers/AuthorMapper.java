package com.oksana.library.mappers;

import com.oksana.library.dtos.AuthorDto;
import com.oksana.library.entities.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Author mapToEntity(AuthorDto authorDto) {
        Author result = this.modelMapper.map(authorDto, Author.class);
        return result;
    }

    public AuthorDto mapToDto(Author author){
        AuthorDto result = this.modelMapper.map(author, AuthorDto.class);
        return result;
    }
}
