package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FilterCalculator<T extends Filterable> {

    private static final int NUMBER_OF_RELEVANT_GENRES = 3;


    public Filter getFilter(Set<T> favorites) {
        return getFilter(favorites, Collections.emptySet());
    }

    public Filter getFilter(Set<T> favorites, Set<Long> favoriteActors) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Filter filter = new Filter();
        Map<Integer, Integer> genreOccurrence = calculateGenreOccurrences(favorites);
        Float minVoteAverage = calculateFloatValue(favorites,
                Filterable::getVoteAverage,
                Float::min);

        Date latestReleaseDate = calculateDateValue(favorites,
                Filterable::getReleaseDate,
                BinaryOperator.maxBy(Date::compareTo));

        List<Integer> topGenres = getTopGenres(genreOccurrence);
        if (!CollectionUtils.isEmpty(topGenres)) {
            filter.setGenresToInclude(topGenres.stream().limit(3).toList());
        }
        if (!CollectionUtils.isEmpty(favoriteActors)) {
            filter.setActors(favoriteActors.stream().limit(5).toList());
        }

        filter.setLatestReleaseDate(formatter.format(latestReleaseDate));
        filter.setMinVoteAverage(minVoteAverage);

        return filter;
    }

    private Map<Integer, Integer> calculateGenreOccurrences(Set<T> favorites) {
        return favorites.stream()
                .flatMap(movieDetails -> movieDetails.getGenres().stream().limit(NUMBER_OF_RELEVANT_GENRES))
                .collect(Collectors.toMap(Genre::getId, genre -> 1, Integer::sum));
    }

    private Float calculateFloatValue(Set<T> favorites, Function<T, Float> valueExtractor, BinaryOperator<Float> valueReducer) {
        return favorites.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .reduce(valueReducer)
                .orElse(null);
    }

    private Date calculateDateValue(Set<T> favorites, Function<T, Date> valueExtractor, BinaryOperator<Date> valueReducer) {
        return favorites.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .reduce(valueReducer)
                .orElse(null);
    }

    private List<Integer> getTopGenres(Map<Integer, Integer> genreOccurrence) {
        return genreOccurrence.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

}
