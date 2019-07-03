package com.oksana.library.controllers;

import com.oksana.library.dtos.GenreDto;
import com.oksana.library.mappers.GenreMapper;
import com.oksana.library.services.GenreService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GenreControllerTest {

    private GenreController genreController;

    @Mock
    private GenreService genreService;
    @Mock
    private GenreMapper genreMapper;

    @Before
    public void setup() {
        this.genreController = new GenreController(this.genreService, this.genreMapper);
    }

    @Test
    public void create() {
        GenreDto dto = new GenreDto();
        dto.setName("fiction");
        GenreDto result = this.genreController.create(dto);

        assertNull(result);
        assertEquals("fiction", result.getName());


    }
}