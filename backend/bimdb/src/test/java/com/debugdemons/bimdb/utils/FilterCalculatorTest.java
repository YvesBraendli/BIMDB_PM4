package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.TvShowDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class FilterCalculatorTest {

    private static final String DRAMA = "Drama";
    private static final String THRILLER = "Thriller";
    private static final String ACTION = "Action";

    @Test
    void testGetMovieFilter() {
        // Create some sample MovieDetails objects
        MovieDetails movie1 = createMovieDetailsWithGenres(Arrays.asList(new Genre(1, ACTION), new Genre(2, DRAMA)));
        movie1.setReleaseDate(getDate(2010, 5, 15));
        movie1.setVoteAverage(7.5f);

        MovieDetails movie2 = createMovieDetailsWithGenres(Arrays.asList(new Genre(2, DRAMA), new Genre(3, "Comedy")));
        movie2.setReleaseDate(getDate(2005, 10, 20));
        movie2.setVoteAverage(6.8f);

        MovieDetails movie3 = createMovieDetailsWithGenres(Arrays.asList(new Genre(1, ACTION), new Genre(4, THRILLER)));
        movie3.setReleaseDate(getDate(2015, 2, 8));
        movie3.setVoteAverage(8.2f);

        MovieDetails movie4 = createMovieDetailsWithGenres(Arrays.asList(new Genre(2, DRAMA), new Genre(4, THRILLER)));
        movie4.setReleaseDate(getDate(2012, 12, 1));
        movie4.setVoteAverage(7.9f);

        Set<MovieDetails> favorites = new HashSet<>(Arrays.asList(movie1, movie2, movie3, movie4));

        Set<Long> favoriteActors = new HashSet<>(Arrays.asList(1001L, 1002L, 1003L, 1004L));

        FilterCalculator<MovieDetails> filterCalculator = new FilterCalculator<MovieDetails>();
        Filter filter = filterCalculator.getFilter(favorites, favoriteActors);

        // Verify the expected filter attributes based on the provided sample movies and actors
        List<Integer> expectedGenres = Arrays.asList(2, 1, 4); // Drama, Action, Thriller
        List<Long> expectedActors = Arrays.asList(1001L, 1002L, 1003L, 1004L); // No actors with occurrence count >= 2
        Integer expectedReleaseYearFrom = 2005;
        Integer expectedReleaseYearTo = 2015;

        Assertions.assertEquals(expectedGenres, filter.getGenresToInclude());
        Assertions.assertEquals(expectedActors, filter.getActors());
        Assertions.assertEquals(expectedReleaseYearFrom, filter.getReleaseYearFrom());
        Assertions.assertEquals(expectedReleaseYearTo, filter.getReleaseYearTo());
    }

    @Test
    void testGetTvShowFilter() {
        // Prepare test data
        Set<TvShowDetails> favorites = createTvShowDetailsSet();

        // Create the TvShowFilterCalculator instance
        FilterCalculator<TvShowDetails> filterCalculator = new FilterCalculator<TvShowDetails>();

        // Calculate the filter
        Filter filter = filterCalculator.getFilter(favorites);

        // Assertions
        Assertions.assertEquals(Arrays.asList(2, 1, 3), filter.getGenresToInclude());
        Assertions.assertNull(filter.getActors());
        Assertions.assertEquals(2005, filter.getReleaseYearFrom());
        Assertions.assertEquals(2022, filter.getReleaseYearTo());
    }

    private Set<TvShowDetails> createTvShowDetailsSet() {
        Set<TvShowDetails> tvShowDetailsSet = new HashSet<>();

        // Tv Show 1
        TvShowDetails tvShow1 = new TvShowDetails();
        tvShow1.setGenres(Arrays.asList(new Genre(1, DRAMA)));
        tvShow1.setReleaseDate(getDate(2005, 3, 15));
        tvShow1.setVoteAverage(7.5F);
        tvShowDetailsSet.add(tvShow1);

        // Tv Show 2
        TvShowDetails tvShow2 = new TvShowDetails();
        tvShow2.setGenres(Arrays.asList(new Genre(2, DRAMA), new Genre(3, "Romance")));
        tvShow2.setReleaseDate(getDate(2022, 8, 10));
        tvShow2.setVoteAverage(8.1F);
        tvShowDetailsSet.add(tvShow2);

        // Tv Show 3
        TvShowDetails tvShow3 = new TvShowDetails();
        tvShow3.setGenres(Arrays.asList(new Genre(2, "Comedy"), new Genre(4, ACTION)));
        tvShow3.setReleaseDate(getDate(2010, 5, 20));
        tvShow3.setVoteAverage(6.9F);
        tvShowDetailsSet.add(tvShow3);

        // Tv Show 4
        TvShowDetails tvShow4 = new TvShowDetails();
        tvShow4.setGenres(Arrays.asList(new Genre(5, THRILLER)));
        tvShow4.setReleaseDate(getDate(2015, 11, 1));
        tvShow4.setVoteAverage(7.3F);
        tvShowDetailsSet.add(tvShow4);

        return tvShowDetailsSet;
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