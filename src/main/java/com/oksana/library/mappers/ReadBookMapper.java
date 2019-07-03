package com.oksana.library.mappers;

import com.oksana.library.dtos.ReadBookDto;
import com.oksana.library.entities.ReadBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class ReadBookMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ReadBookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReadBookDto mapToDto(ReadBook readBook){
        ReadBookDto result = this.modelMapper.map(readBook, ReadBookDto.class);
        result.setBookId(readBook.getBook().getId());
        result.setBookId(readBook.getUser().getId());
        return result;
    }
    public ReadBook mapToEntity(ReadBookDto readBookDto){
      return   this.modelMapper.map(readBookDto, ReadBook.class);
    }
}
