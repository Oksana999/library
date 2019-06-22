package com.oksana.library.services;

import com.oksana.library.entities.Genre;
import com.oksana.library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public Genre create(Genre genre){
        return this.genreRepository.save(genre);
    }
    public Genre update(Genre genre){
        return this.genreRepository.save(genre);
    }
    public Optional<Genre> findById(Long id){
        return this.genreRepository.findById(id);
    }

    public void delete(Genre genre){
        this.genreRepository.delete(genre);
    }

    public Page<Genre> findAll(Pageable pageable){
        return this.genreRepository.findAll(pageable);
    }
}
