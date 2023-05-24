package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.MovieDetails;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class FilterCalculator {

    public Filter getFilter(Set<MovieDetails> favorites, Set<Long> favoriteActors) {
        Filter filter = new Filter();
        Map<Integer, Integer> genreOccurrence = new HashMap<>();
        Integer releaseYearFrom = null;
        Integer releaseYearTo = null;
        Double ratingThreshold = null;

        for (MovieDetails movieDetails : favorites) {
            // Genres
            List<Genre> genres = movieDetails.getGenres();
            if (genres != null && !genres.isEmpty()) {
                for (Genre genre : genres) {
                    Integer count = genreOccurrence.getOrDefault(genre.getId(), 0);
                    genreOccurrence.put(genre.getId(), count + 1);
                }
            }

            // Release Year
            Date movieReleaseDate = movieDetails.getReleaseDate();
            if (movieReleaseDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(movieReleaseDate);
                int movieReleaseYear = calendar.get(Calendar.YEAR);
                if (releaseYearFrom == null || movieReleaseYear < releaseYearFrom) {
                    releaseYearFrom = movieReleaseYear;
                }
                if (releaseYearTo == null || movieReleaseYear > releaseYearTo) {
                    releaseYearTo = movieReleaseYear;
                }
            }

            // Rating Threshold
            Float movieRating = movieDetails.getVoteAverage();
            if (movieRating != null) {
                if (ratingThreshold == null || movieRating < ratingThreshold) {
                    ratingThreshold = movieRating.doubleValue();
                }
            }
        }

        List<Integer> topGenres = new ArrayList<>(genreOccurrence.keySet());
        topGenres.sort((g1, g2) -> genreOccurrence.get(g2) - genreOccurrence.get(g1));


        filter.setGenresToInclude(topGenres.subList(0, Math.min(topGenres.size(), 3)));
        if (!CollectionUtils.isEmpty(favoriteActors)) {
            filter.setActors(favoriteActors.stream().toList().subList(0, Math.min(topGenres.size(), 3)));
        }
        filter.setReleaseYearFrom(releaseYearFrom);
        filter.setReleaseYearTo(releaseYearTo);
        filter.setRatingThreshold(ratingThreshold);

        return filter;
    }
}