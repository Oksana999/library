package com.oksana.library.mappers;

import com.oksana.library.Language;
import com.oksana.library.dtos.BookDto;
import com.oksana.library.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final ModelMapper modelMapper; //dto --> entity  и наоборот
    @Autowired
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Book mapToEntity(BookDto bookDto){
        Book result = this.modelMapper.map(bookDto, Book.class); // dto --> entity
        result.setLanguage(Language.valueOf(bookDto.getLanguage()));
        return result;
    }

    public BookDto mapToDto(Book book){
        BookDto result = this.modelMapper.map(book, BookDto.class); // entity --> dto
        result.setLanguage(book.getLanguage().toString());
        return result;
    }
}
