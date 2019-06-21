package com.oksana.library.controllers;

import com.oksana.library.dtos.AuthorDto;
import com.oksana.library.entities.Author;
import com.oksana.library.mappers.AuthorMapper;
import com.oksana.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
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

   // public List<Author> authorList ()
}
