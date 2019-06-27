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
        return this.modelMapper.map(readBook, ReadBookDto.class);
    }
    public ReadBook mapToEntity(ReadBookDto readBookDto){
      return   this.modelMapper.map(readBookDto, ReadBook.class);
    }
}
