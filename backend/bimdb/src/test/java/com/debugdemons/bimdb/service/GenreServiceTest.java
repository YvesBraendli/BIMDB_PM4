package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.GenreListWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class GenreServiceTest extends BaseServiceTest {
    @Autowired
    private GenreService genreService;

    @Test
    void testGetAllTvGenres() throws JsonProcessingException {
        GenreListWrapper expectedGenreListWrapper = new GenreListWrapper();
        Genre expectedGenre = new Genre();
        expectedGenre.setId(35);
        expectedGenre.setName("Comedy");
        expectedGenreListWrapper.setGenres(List.of(expectedGenre));
        mockServerExpectGet("https://api.themoviedb.org/3/genre/tv/list?language=en", expectedGenreListWrapper);
        GenreListWrapper actualGenreList = genreService.getAllTvGenres();
        assertJsonEquals(expectedGenreListWrapper.getGenres(), actualGenreList.getGenres());
    }

    @Test
    void testGetAllMovieGenres() throws JsonProcessingException {
        GenreListWrapper expectedGenreListWrapper = new GenreListWrapper();
        Genre expectedGenre = new Genre();
        expectedGenre.setId(35);
        expectedGenre.setName("Comedy");
        expectedGenreListWrapper.setGenres(List.of(expectedGenre));
        mockServerExpectGet("https://api.themoviedb.org/3/genre/movie/list?language=en", expectedGenreListWrapper);
        GenreListWrapper actualGenreList = genreService.getAllMovieGenres();
        assertJsonEquals(expectedGenreListWrapper.getGenres(), actualGenreList.getGenres());
    }
}
