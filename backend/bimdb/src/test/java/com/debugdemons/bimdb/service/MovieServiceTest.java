package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceTest {

    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private MovieService movieService;

    @Test
    void discoverMovie() {
        DiscoverMovie discoverMovie = new DiscoverMovie();
        discoverMovie.setTotalPages(20);
        when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=api_key", DiscoverMovie.class)).thenReturn(discoverMovie);
        assertEquals(movieService.getMovies(), discoverMovie);
    }

    @Test
    void movieDetails() {
        MovieDetails movie = new MovieDetails();
        movie.setApiId(538);
        movie.setName("Interstellar");
        when(restTemplate.getForObject("https://api.themoviedb.org/3/movie/538?api_key=api_key", MovieDetails.class)).thenReturn(movie);
        assertEquals(movie, movieService.getMovieById(538L));
    }
}
