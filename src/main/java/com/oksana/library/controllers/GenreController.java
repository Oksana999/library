package com.oksana.library.controllers;

import com.oksana.library.dtos.GenreDto;
import com.oksana.library.entities.Genre;
import com.oksana.library.mappers.GenreMapper;
import com.oksana.library.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;
    @Autowired
    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @PostMapping
    public GenreDto create(GenreDto genreDto){
        Genre genre = this.genreMapper.mapToEntity(genreDto);
        return this.genreMapper.mapToDto(this.genreService.create(genre));
    }
    @GetMapping(value = "/{id}")
    public GenreDto findById(@PathVariable Long id){
        Genre genre = this.genreService.findById(id).orElseThrow(() -> new RuntimeException("Genre is not found"));
        return this.genreMapper.mapToDto(genre);

    }
    @GetMapping
    public Page<GenreDto> allGenres(Pageable pageable){
      return   this.genreService.findAll(pageable).map(this.genreMapper::mapToDto);

    }

    @PutMapping(value = "/{id}")
    public GenreDto update(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        Genre newGenre = this.genreService.findById(id).orElseThrow(() -> new RuntimeException("This genre does not found"));
        newGenre.setId(id);
        this.genreService.update(newGenre);
        genreDto.setId(id);
        return genreDto;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(Long id){
        Genre genre = this.genreService.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
        this.genreService.delete(genre);

        return "Deleted";

    }


}
