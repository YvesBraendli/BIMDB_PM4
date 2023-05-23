package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.MovieDetails;

import java.util.*;

public class FilterCalculator {

    public static Filter calculateMovieFilter(Set<MovieDetails> favorites) {
        Filter filter = new Filter();
        Map<Integer, Integer> genreOccurrence = new HashMap<>();

        for (MovieDetails movieDetails : favorites) {
            List<Genre> genres = movieDetails.getGenres();

            for (Genre genre : genres) {
                Integer count = genreOccurrence.getOrDefault(genre.getId(), 0);
                genreOccurrence.put(genre.getId(), count + 1);
            }
        }

        List<Integer> topGenres = new ArrayList<>(genreOccurrence.keySet());

        topGenres.sort((g1, g2) -> genreOccurrence.get(g2) - genreOccurrence.get(g1));

        filter.setGenresToInclude(topGenres.subList(0, Math.min(topGenres.size(), 5)));
        return filter;
    }
}
