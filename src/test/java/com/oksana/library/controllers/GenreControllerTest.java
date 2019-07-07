package com.oksana.library.controllers;

import com.oksana.library.dtos.GenreDto;
import com.oksana.library.entities.Genre;
import com.oksana.library.entities.ReadBook;
import com.oksana.library.mappers.GenreMapper;
import com.oksana.library.repositories.GenreRepository;
import com.oksana.library.services.GenreService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {

    private GenreController genreController;

    @Mock
    private GenreService genreService;
    @Mock
    private GenreMapper genreMapper;

    @Mock
    private GenreRepository genreRepository;

    @Before
    public void setup() {
        this.genreController = new GenreController(this.genreService, this.genreMapper);
    }

    @Test
    public void create() {
        GenreDto dto = new GenreDto();
        dto.setName("fiction");

        Genre genre = new Genre();
        genre.setName("fiction");

        when(this.genreService.create(genre)).thenReturn(genre);
        when(this.genreMapper.mapToDto(genre)).thenReturn(dto);
        when(this.genreMapper.mapToEntity(dto)).thenReturn(genre);

        GenreDto result = this.genreController.create(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getName(), result.getName());

//        assertNull(result);
//        assertEquals("fiction", result.getName());

        verify(this.genreMapper, times(1)).mapToEntity(dto);
        verify(this.genreMapper, times(1)).mapToDto(genre);
        verify(this.genreService, times(1)).create(genre);

    }

    @Test
    public void findById() {

        Genre genre = new Genre();
        genre.setName("comedy");
        genre.setId(5L);

        GenreDto genreDto = new GenreDto();
        genreDto.setName("comedy");
        genreDto.setId(5L);

        Long id = 5L;

       Optional<Genre> genreOpt = Optional.of(genre);

        when(this.genreService.findById(id)).thenReturn(genreOpt);
       // when(this.genreMapper.mapToEntity(genreDto)).thenReturn(genre);
        when(this.genreMapper.mapToDto(genre)).thenReturn(genreDto);

        GenreDto result = this.genreController.findById(id);

        assertEquals(genreOpt.get().getName(), result.getName());
        assertEquals(genreOpt.get().getId(), result.getId());
        assertNotNull(result);

        verify(this.genreService, times(1)).findById(id);
        verify(this.genreMapper, times(1)).mapToDto(genre);
    }
    @Test
    public void findAll(){
        Genre genre = new Genre();
        genre.setName("comedy");
        genre.setId(5L);

        GenreDto genreDto = new GenreDto();
        genreDto.setName("comedy");
        genreDto.setId(5L);

        Genre genre1 = new Genre();
        genre1.setName("adventure");
        genre1.setId(7L);

        GenreDto genreDto1 = new GenreDto();
        genreDto1.setName("adventure");
        genreDto1.setId(7L);


        Page<Genre> genres = new PageImpl<>(Arrays.asList(genre, genre1));
        PageRequest pageRequest = new PageRequest(1,20);

        when(this.genreService.findAll(pageRequest)).thenReturn(genres);
     //   when(this.genreMapper.mapToEntity(genreDto)).thenReturn(genre);
     //   when(this.genreMapper.mapToEntity(genreDto1)).thenReturn(genre1);
        when(this.genreMapper.mapToDto(genre)).thenReturn(genreDto);
        when(this.genreMapper.mapToDto(genre1)).thenReturn(genreDto1);

        List<GenreDto> result = this.genreController.allGenres(pageRequest).getContent();

        assertEquals(2, result.size());
        assertEquals(genre.getName(), result.get(0).getName());
        assertEquals(genre1.getName(), result.get(1).getName());

        verify(this.genreService, times(1)).findAll(pageRequest);
        verify(this.genreMapper, times(1)).mapToDto(genre);
        verify(this.genreMapper, times(1)).mapToDto(genre1);
    }
}