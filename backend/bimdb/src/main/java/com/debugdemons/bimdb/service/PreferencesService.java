package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.Person;
import com.debugdemons.bimdb.domain.Preferences;
import com.debugdemons.bimdb.model.UserPreferences;
import com.debugdemons.bimdb.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferencesService {

    private PreferencesRepository preferencesRepository;

    private PersonService personService;

    private GenreService genreService;

    @Autowired
    public PreferencesService(PreferencesRepository preferencesRepository, PersonService personService, GenreService genreService) {
        this.preferencesRepository = preferencesRepository;
        this.personService = personService;
        this.genreService = genreService;
    }

    public Preferences getPreferences(String username) {
        UserPreferences userPreferences = preferencesRepository.findByUsernameAndSource(username, MovieDBApiConfig.NAME);
        if (userPreferences == null) {
            return new Preferences();
        }
        Preferences preferences = new Preferences();

        preferences.setUsername(userPreferences.getUsername());
        preferences.setRatingThreshold(userPreferences.getRatingThreshold());
        preferences.setReleaseYearFrom(userPreferences.getReleaseYearFrom());
        preferences.setReleaseYearTo(userPreferences.getReleaseYearTo());
        if (!CollectionUtils.isEmpty(userPreferences.getFavoriteActors())) {
            List<Person> favoriteActors = new ArrayList<>();
            for (Long id : userPreferences.getFavoriteActors()) {
                favoriteActors.add(personService.getPerson(id));
            }
            preferences.setFavoriteActors(favoriteActors);
        }
        if (!CollectionUtils.isEmpty(userPreferences.getFavoriteTvGenres())) {
            preferences.setFavoriteTvGenres(getGenreList(userPreferences.getFavoriteTvGenres(), genreService.getAllTvGenres()));
        }
        if (!CollectionUtils.isEmpty(userPreferences.getFavoriteMovieGenres())) {
            preferences.setFavoriteMovieGenres(getGenreList(userPreferences.getFavoriteMovieGenres(), genreService.getAllMovieGenres()));
        }

        return preferences;
    }

    private List<Genre> getGenreList(List<Integer> genreIds, List<Genre> allGenres) {
        List<Genre> favoriteGenres = new ArrayList<>();
        for(Genre genre : allGenres) {
            if (genreIds.contains(genre.getId())) {
                favoriteGenres.add(genre);
            }
        }
        return favoriteGenres;
    }
}
