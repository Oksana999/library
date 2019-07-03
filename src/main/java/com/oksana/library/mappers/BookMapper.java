package com.oksana.library.mappers;

import com.oksana.library.Language;
import com.oksana.library.dtos.AuthorDto;
import com.oksana.library.dtos.BookDto;
import com.oksana.library.dtos.GenreDto;
import com.oksana.library.entities.Author;
import com.oksana.library.entities.Book;
import com.oksana.library.entities.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final ModelMapper modelMapper; //dto --> entity  и наоборот
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    @Autowired
    public BookMapper(ModelMapper modelMapper, AuthorMapper authorMapper, GenreMapper genreMapper) {
        this.modelMapper = modelMapper;
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }

    public Book mapToEntity(BookDto bookDto){
        Book result = this.modelMapper.map(bookDto, Book.class); // dto --> entity
        result.setLanguage(Language.valueOf(bookDto.getLanguage()));
        for (AuthorDto authorDto : bookDto.getAuthorDtos() ) {
            Author authors = this.authorMapper.mapToEntity(authorDto);
            result.getAutors().add(authors);
        }
        for (GenreDto genreDto : bookDto.getGenreDtos() ) {
           result.getGenres().add( this.genreMapper.mapToEntity(genreDto));
        }
        return result;
    }

    public BookDto mapToDto(Book book){
        BookDto result = this.modelMapper.map(book, BookDto.class); // entity --> dto
        result.setLanguage(book.getLanguage().toString());
        for (Author autor : book.getAutors()) {
            result.getAuthorDtos().add(this.authorMapper.mapToDto(autor));
        }

        for (Genre genre : book.getGenres()) {
            result.getGenreDtos().add(this.genreMapper.mapToDto(genre));
        }
        return result;
    }
}
