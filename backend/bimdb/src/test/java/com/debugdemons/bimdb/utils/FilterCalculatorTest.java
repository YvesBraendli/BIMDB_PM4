package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.MovieDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class FilterCalculatorTest {

    @Test
    void testGetFilter() {
        // Create some sample MovieDetails objects
        MovieDetails movie1 = createMovieDetailsWithGenres(Arrays.asList(new Genre(1, "Action"), new Genre(2, "Drama")));
        movie1.setReleaseDate(getDate(2010, 5, 15));
        movie1.setVoteAverage(7.5f);

        MovieDetails movie2 = createMovieDetailsWithGenres(Arrays.asList(new Genre(2, "Drama"), new Genre(3, "Comedy")));
        movie2.setReleaseDate(getDate(2005, 10, 20));
        movie2.setVoteAverage(6.8f);

        MovieDetails movie3 = createMovieDetailsWithGenres(Arrays.asList(new Genre(1, "Action"), new Genre(4, "Thriller")));
        movie3.setReleaseDate(getDate(2015, 2, 8));
        movie3.setVoteAverage(8.2f);

        MovieDetails movie4 = createMovieDetailsWithGenres(Arrays.asList(new Genre(2, "Drama"), new Genre(4, "Thriller")));
        movie4.setReleaseDate(getDate(2012, 12, 1));
        movie4.setVoteAverage(7.9f);

        Set<MovieDetails> favorites = new HashSet<>(Arrays.asList(movie1, movie2, movie3, movie4));

        Set<Long> favoriteActors = new HashSet<>(Arrays.asList(1001L, 1002L, 1003L, 1004L));

        FilterCalculator filterCalculator = new FilterCalculator();
        Filter filter = filterCalculator.getFilter(favorites, favoriteActors);

        // Verify the expected filter attributes based on the provided sample movies and actors
        List<Integer> expectedGenres = Arrays.asList(2, 1, 4); // Drama, Action, Thriller
        List<Long> expectedActors = Arrays.asList(1001L, 1002L, 1003L); // No actors with occurrence count >= 2
        Integer expectedReleaseYearFrom = 2005;
        Integer expectedReleaseYearTo = 2015;
        Double expectedRatingThreshold = 6.8;

        Assertions.assertEquals(expectedGenres, filter.getGenresToInclude());
        Assertions.assertEquals(expectedActors, filter.getActors());
        Assertions.assertEquals(expectedReleaseYearFrom, filter.getReleaseYearFrom());
        Assertions.assertEquals(expectedReleaseYearTo, filter.getReleaseYearTo());
    }

    // Helper method to create a MovieDetails object with the given genres
    private MovieDetails createMovieDetailsWithGenres(List<Genre> genres) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setGenres(genres);
        return movieDetails;
    }

    // Helper method to create a Date object with the given year, month, and day
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // Month is 0-based in Calendar
        return calendar.getTime();
    }
}