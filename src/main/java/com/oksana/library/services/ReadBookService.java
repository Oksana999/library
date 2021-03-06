package com.oksana.library.services;

import com.oksana.library.entities.ReadBook;
import com.oksana.library.entities.User;
import com.oksana.library.repositories.ReadBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadBookService {
    private final ReadBookRepository readBookRepository;

    @Autowired
    public ReadBookService(ReadBookRepository readBookRepository) {
        this.readBookRepository = readBookRepository;
    }
    public ReadBook create(ReadBook readBook){
       return this.readBookRepository.save(readBook);
    }

    public Optional<ReadBook> getById(Long id){
        return this.readBookRepository.findById(id);
    }

    public ReadBook update(ReadBook readBook){
        return this.readBookRepository.save(readBook);
    }

    public void delete(ReadBook readBook){
       this.readBookRepository.delete(readBook);
    }

    public Page<ReadBook> findAllByUser (User user, Pageable pageable){
        return  this.readBookRepository.findAllByUser(user, pageable);

    }
}
