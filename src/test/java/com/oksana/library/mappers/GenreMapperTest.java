package com.oksana.library.mappers;

import com.oksana.library.dtos.GenreDto;
import com.oksana.library.entities.Genre;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;

public class GenreMapperTest {

    private GenreMapper genreMapper;

    @Before
    public void init() {
        this.genreMapper = new GenreMapper(new ModelMapper());
    }

    @After
    public void desctroy() {
        this.genreMapper = null;
    }

    @Test
    public void mapToEntity() {
        GenreDto genreDto = new GenreDto();
        genreDto.setName("Comedy");

        Genre genre = this.genreMapper.mapToEntity(genreDto);
        Assert.assertEquals(genreDto.getName(), genre.getName());
    }

    @Test
    public void mapToDto() {
        Genre genre = new Genre();
        genre.setName("Humor");

        GenreDto genreDto = this.genreMapper.mapToDto(genre);
        Assert.assertEquals(genre.getName(), genreDto.getName());

    }
}