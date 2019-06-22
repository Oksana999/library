package com.oksana.library.controllers;

import com.oksana.library.dtos.AuthorDto;
import com.oksana.library.dtos.BookDto;
import com.oksana.library.entities.Author;
import com.oksana.library.mappers.AuthorMapper;
import com.oksana.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;
    @Autowired
    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }
    @PostMapping
    public AuthorDto create(@RequestBody AuthorDto authorDto){
        Author author = this.authorMapper.mapToEntity(authorDto);
        return this.authorMapper.mapToDto(this.authorService.create(author));
    }
    @GetMapping(value = "/{id}")
    public AuthorDto findById(@PathVariable Long id){
        Author author = this.authorService.findById(id).orElseThrow(() -> new RuntimeException("Author was not found"));
       return this.authorMapper.mapToDto(author);

    }

    @GetMapping
    public Page<AuthorDto> getAll(Pageable pageable){
       return this.authorService.findAll(pageable).map(this.authorMapper::mapToDto);
    }

    @PutMapping(value = "/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto authorDto){

        Author newAuthor = this.authorMapper.mapToEntity(authorDto);
        this.authorService.findById(id).orElseThrow(() -> new RuntimeException("This author does not exist"));
        newAuthor.setId(id);
        this.authorService.save(newAuthor);
        authorDto.setId(id);
        return authorDto;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAuthor (@PathVariable Long id){
       Author author =  this.authorService.findById(id).orElseThrow(() -> new RuntimeException("This author does not exist"));
        this.authorService.delete(author);
        return "Deleted";
    }
}
