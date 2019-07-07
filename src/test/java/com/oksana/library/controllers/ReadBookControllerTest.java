package com.oksana.library.controllers;

import com.oksana.library.config.UserHelper;
import com.oksana.library.dtos.ReadBookDto;
import com.oksana.library.entities.Book;
import com.oksana.library.entities.ReadBook;
import com.oksana.library.entities.User;
import com.oksana.library.mappers.ReadBookMapper;
import com.oksana.library.services.ReadBookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadBookControllerTest {

    private ReadBookController readBookController;

    @Mock
    private ReadBookService readBookService;

    @Mock
    private ReadBookMapper readBookMapper;

    @Mock
    private UserHelper userHelper;

    @Before
    public void setup() {
        this.readBookController = new ReadBookController(this.readBookService, this.readBookMapper);
    }

    @Test
    public void create() {
        ReadBookDto readBookDto = new ReadBookDto();
        readBookDto.setUserId(140L);
        readBookDto.setBookId(9L);
//        readBookDto.setDate(LocalDateTime.now());
        readBookDto.setDate(LocalDateTime.of(2019, 1, 1, 15, 15, 0));

        ReadBook readBook = new ReadBook(
                new User(){{setId(140L);}},
                new Book(){{setId(9L);}},
                LocalDateTime.of(2019, 1, 1, 15, 15, 0));

        when(this.readBookService.create(readBook)).thenReturn(readBook);
        when(this.readBookMapper.mapToDto(readBook)).thenReturn(readBookDto);
        when(this.readBookMapper.mapToEntity(readBookDto)).thenReturn(readBook);

        ReadBookDto result = this.readBookController.create(readBookDto);

        assertEquals(readBookDto.getUserId(), result.getUserId());
        assertEquals(readBookDto.getBookId(), result.getBookId());
        assertEquals(readBookDto.getDate(), result.getDate());

        verify(this.readBookMapper, times(1)).mapToEntity(readBookDto);
        verify(this.readBookMapper, times(1)).mapToDto(readBook);
        verify(this.readBookService, times(1)).create(readBook);
    }

    @Test
    public void findAllByUser() {
        User user = new User();
        user.setId(1L);

        ReadBook readBook = new ReadBook();
        readBook.setBook(new Book(){{setId(5L);}});
        readBook.setDate(LocalDateTime.now());
        readBook.setUser(user);

        ReadBookDto readBookDto = new ReadBookDto();
        readBookDto.setDate(LocalDateTime.now());
        readBookDto.setUserId(user.getId());
        readBookDto.setBookId(readBook.getBook().getId());

        Page<ReadBook> readBooksList = new PageImpl<>(Arrays.asList(readBook));
        PageRequest pageRequest = new PageRequest(1,20);

        when(this.readBookService.findAllByUser(user, pageRequest)).thenReturn(readBooksList);
        when(this.readBookMapper.mapToEntity(readBookDto)).thenReturn(readBook);
        when(this.readBookMapper.mapToDto(readBook)).thenReturn(readBookDto);

        List<ReadBookDto> result = this.readBookController.findAllByUser(pageRequest).getContent(); // Проверить UserHelper ?

        assertEquals(1, result.size());
        assertEquals(readBookDto.getDate(), result.get(0).getDate());
        assertEquals(readBookDto.getBookId(), result.get(0).getBookId());
        assertEquals(readBookDto.getUserId(), result.get(0).getUserId());

        verify(this.readBookService, times(1)).findAllByUser(user,pageRequest);
        verify(this.readBookMapper,times(1)).mapToDto(readBook);
        verify(this.readBookMapper, times(1)).mapToEntity(readBookDto);
    }
}