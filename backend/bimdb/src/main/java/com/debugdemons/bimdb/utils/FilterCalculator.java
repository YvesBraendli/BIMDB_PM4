package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilterCalculator<T extends Filterable> {

    public Filter getFilter(Set<T> favorites) {
        return getFilter(favorites, Collections.emptySet());
    }

    public Filter getFilter(Set<T> favorites, Set<Long> favoriteActors) {
        Filter filter = new Filter();
        Map<Integer, Integer> genreOccurrence = calculateGenreOccurrences(favorites);
        Integer releaseYearFrom = calculateReleaseYear(favorites,
                filterableObject -> getYearFromDate(filterableObject.getReleaseDate()),
                Integer::min);
        Integer releaseYearTo = calculateReleaseYear(favorites,
                filterableObject -> getYearFromDate(filterableObject.getReleaseDate()),
                Integer::max);

        List<Integer> topGenres = getTopGenres(genreOccurrence);
        if (!CollectionUtils.isEmpty(topGenres)) {
            filter.setGenresToInclude(topGenres.stream().limit(3).toList());
        }
        if (!CollectionUtils.isEmpty(favoriteActors)) {
            filter.setActors(favoriteActors.stream().limit(5).toList());
        }
        filter.setReleaseYearFrom(releaseYearFrom);
        filter.setReleaseYearTo(releaseYearTo);

        return filter;
    }

    private Map<Integer, Integer> calculateGenreOccurrences(Set<T> favorites) {
        return favorites.stream()
                .flatMap(movieDetails -> movieDetails.getGenres().stream())
                .collect(Collectors.toMap(Genre::getId, genre -> 1, Integer::sum));
    }

    private Integer calculateReleaseYear(Set<T> favorites, Function<T, Integer> yearExtractor, BinaryOperator<Integer> yearReducer) {
        return favorites.stream()
                .map(yearExtractor)
                .filter(Objects::nonNull)
                .reduce(yearReducer)
                .orElse(null);
    }

    private List<Integer> getTopGenres(Map<Integer, Integer> genreOccurrence) {
        return genreOccurrence.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

    private Integer getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
