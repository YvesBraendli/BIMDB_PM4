package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvider;
import com.debugdemons.bimdb.domain.WatchProviders;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        assertEquals(movieService.getMovies(null), discoverMovie);
    }

    @Test
    void discoverMoviePage() {
        DiscoverMovie discoverMovie = new DiscoverMovie();
        discoverMovie.setTotalPages(20);
        when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?page=15&api_key=api_key", DiscoverMovie.class)).thenReturn(discoverMovie);
        assertEquals(movieService.getMovies(15), discoverMovie);
    }

    @Test
    void movieDetails() {
        MovieDetails movie = new MovieDetails();
        movie.setApiId(538);
        movie.setName("Interstellar");
        when(restTemplate.getForObject("https://api.themoviedb.org/3/movie/538?api_key=api_key&append_to_response=credits,recommendations,similar", MovieDetails.class)).thenReturn(movie);
        assertEquals(movie, movieService.getMovieById(538L));
    }

    @Test
    void watchProviders() {
        WatchProvider watchProvider = new WatchProvider();
        watchProvider.setProviderName("Google Play Movies");
        watchProvider.setLogoPath("/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg");

        WatchProviders watchProviders = new WatchProviders();
        watchProviders.setCountry("CH");
        watchProviders.getFlatrate().add(watchProvider);
        watchProviders.getRent().add(watchProvider);
        watchProviders.getBuy().add(watchProvider);

        WatchProvidersResult result = new WatchProvidersResult();
        result.setWatchProviders(List.of(watchProviders));

        when(restTemplate.getForObject("https://api.themoviedb.org/3/movie/538/watch/providers?api_key=api_key", WatchProvidersResult.class)).thenReturn(result);
        assertEquals(result, movieService.getWatchProviders(538L));
    }
}
