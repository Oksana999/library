package com.oksana.library.controllers;

import com.oksana.library.config.UserHelper;
import com.oksana.library.dtos.ReadBookDto;
import com.oksana.library.entities.ReadBook;
import com.oksana.library.mappers.ReadBookMapper;
import com.oksana.library.services.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/readbook")
public class ReadBookController {

    private final ReadBookService readBookService;
    private final ReadBookMapper readBookMapper;

    @Autowired
    public ReadBookController(ReadBookService readBookService, ReadBookMapper readBookMapper) {
        this.readBookService = readBookService;
        this.readBookMapper = readBookMapper;
    }
    @PostMapping
    public ReadBookDto create(@RequestBody ReadBookDto readBookDto){
        ReadBook readBook = this.readBookMapper.mapToEntity(readBookDto);
        ReadBook createdReadBook = this.readBookService.create(readBook);
        ReadBookDto dto = this.readBookMapper.mapToDto(createdReadBook);
        return dto;
    }
    @GetMapping("/{id}")
    public ReadBookDto getReadBookById(@PathVariable Long id)  {
        ReadBook readBook = this.readBookService.getById(id).orElseThrow(EntityNotFoundException::new);
        return this.readBookMapper.mapToDto(readBook);
    }
    @PutMapping("/{id")
    public ReadBookDto update(@PathVariable Long id, @RequestBody ReadBookDto readBookDto){
        ReadBook readBook = this.readBookService.getById(id).orElseThrow(EntityNotFoundException::new);
        ReadBook newReadBook = this.readBookMapper.mapToEntity(readBookDto);
        newReadBook.setId(id);
        this.readBookService.update(newReadBook);
        readBookDto.setId(id);
        return readBookDto;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        ReadBook deletedReadBook = this.readBookService.getById(id).orElseThrow(EntityNotFoundException::new);
        this.readBookService.delete(deletedReadBook);
        return "Information about this read book has been deleted";
    }

    @GetMapping
    public Page<ReadBookDto> findAllByUser(Pageable pageable){
       return this.readBookService.findAllByUser(UserHelper.getCurrentUser(), pageable).map(this.readBookMapper::mapToDto);

//        Page<ReadBook> allByUser = this.readBookService.findAllByUser(user, pageable);
//        List<ReadBookDto> result = new ArrayList<>();
//        for (ReadBook readBook : allByUser) {
//            result.add(this.readBookMapper.mapToDto(readBook));
//        }
//        return new PageImpl<>(result, pageable, result.size());
    }
}
