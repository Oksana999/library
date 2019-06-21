package com.oksana.library.controllers;

import com.oksana.library.dtos.BookDto;
import com.oksana.library.entities.Book;
import com.oksana.library.mappers.BookMapper;
import com.oksana.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}")
    public BookDto get(@PathVariable Long id){
        //1. обращаемся к сервису, чтобы найти книгу
        //2. найденную книгу превращ. в dto
        // 3. возвращаем dto на фронтент
//        Optional<Book> bookOpt = this.bookService.get(id);
//        if(!bookOpt.isPresent()){
//            throw new RuntimeException("Book was not found");
//        }
//        Book book = bookOpt.get();
        Book book = this.bookService.get(id).orElseThrow(() -> new RuntimeException("Book was not found"));
       return this.bookMapper.mapToDto(book);
    }

    @GetMapping
    public Page<BookDto> getAll(Pageable pageable){     // pageable - номер запрашиваемой страницы
        //1. Получаем книги из базы посредством сервиса (entity)
        //2. Преобразуем entity в dto
        //3.Список dto возвращаем на фронтент
//        Page<Book> books = this.bookService.findAll(pageable);
//        List<BookDto> list = new ArrayList();
//        for (Book book : books) {
//            BookDto bookDto = this.bookMapper.mapToDto(book);
//            list.add(bookDto);
//        }
//        Page<BookDto> result = new PageImpl<>(list);
//        return result;

        return this.bookService.findAll(pageable).map(this.bookMapper::mapToDto);
    }

    @PutMapping (value = "/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        // 1. находим книгу в базе по id --> entity
        // 2. dto --> new entity
        // 3. new entity. set (id)
        // 4. save new entity
        // 5. dto.set(id)
        //6. return dto

        this.bookService.get(id).orElseThrow(() -> new RuntimeException("Book was not found"));
        Book newBook = this.bookMapper.mapToEntity(bookDto);
        newBook.setId(id);
        this.bookService.save(newBook);
        bookDto.setId(id);
        return bookDto;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBook (@PathVariable Long id){
        Book book = this.bookService.get(id).orElseThrow(() -> new RuntimeException("Book was not found"));
        this.bookService.delete(book);

        return "Deleted";

    }

}
