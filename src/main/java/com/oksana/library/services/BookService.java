package com.oksana.library.services;

import com.oksana.library.entities.Book;
import com.oksana.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create (Book book){
        return this.save(book);
    }

    public Optional<Book> get(Long id) {
        return this.bookRepository.findById(id);
    }

    public Book save (Book book){
        return this.bookRepository.save(book); // update
    }

    public void delete(Book book){
        this.bookRepository.delete(book);
    }

    public Page<Book> findAll(Pageable pageable){
        return this.bookRepository.findAll(pageable);

    }
}
