package com.oksana.library.controllers;

import com.oksana.library.dtos.BookDto;
import com.oksana.library.mappers.BookMapper;
import com.oksana.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookMapper bookMapper;

    private final BookService bookService;
    @Autowired
    public BookController(BookMapper bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }



//    private final BookRepository bookRepository;
//    @Autowired
//    public BookController(BookRepository bookRepository) {
      //  this.bookRepository = bookRepository;
   // }

//    @PostMapping
////    public String createBook(){
////        Book book = new Book();
////        book.setName("Гарри Потер");
////        book.setDescription("Гарри Потер хороший добрый волшебник");
////        book.setLanguage(Language.ENG);
////        this.bookRepository.save(book);
////        return "Ok!";
////    }
    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto){
        // 1. полученную с фронтента dto --> entity
        // 2. save entity  --> DB
        // 3. entity --> dto
        // 4. return dto на фронтент

      return  this.bookMapper.mapToDto(this.bookService.create(this.bookMapper.mapToEntity(bookDto)));
    }
}
