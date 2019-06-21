package com.oksana.library.services;

import com.oksana.library.entities.Author;
import com.oksana.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
     public Author create(Author author){
        return this.save(author);
     }

     private Author save(Author author){
        return this.authorRepository.save(author);
     }

     public Optional<Author> findById(Long id){
        return this.authorRepository.findById(id);
     }

     public void delete (Author author){
        this.authorRepository.delete(author);
     }
}
