package com.debugdemons.bimdb.service;

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
        UserPreferences userPreferences = preferencesRepository.findByUsername(username);
        if (userPreferences == null) {
            UserPreferences newUserPreferences = new UserPreferences();
            newUserPreferences.setUsername(username);
            preferencesRepository.saveAndFlush(newUserPreferences);
            userPreferences = preferencesRepository.findByUsername(username);
        }
        Preferences preferences = new Preferences();
        preferences.setId(userPreferences.getId());
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
        List<Genre> allTvGenres = genreService.getAllTvGenres();
        List<Genre> allMovieGenres = genreService.getAllMovieGenres();

        if (!CollectionUtils.isEmpty(userPreferences.getFavoriteTvGenres())) {
            preferences.setFavoriteTvGenres(getGenreList(userPreferences.getFavoriteTvGenres(), allTvGenres));
        }
        if (!CollectionUtils.isEmpty(userPreferences.getTvGenresToExclude())) {
            preferences.setTvGenresToExclude(getGenreList(userPreferences.getTvGenresToExclude(), allTvGenres));
        }
        if (!CollectionUtils.isEmpty(userPreferences.getFavoriteMovieGenres())) {
            preferences.setFavoriteMovieGenres(getGenreList(userPreferences.getFavoriteMovieGenres(), allMovieGenres));
        }
        if (!CollectionUtils.isEmpty(userPreferences.getMovieGenresToExclude())) {
            preferences.setMovieGenresToExclude(getGenreList(userPreferences.getMovieGenresToExclude(), allMovieGenres));
        }

        return preferences;
    }

    public Preferences updatePreferences(String username, Preferences preferences) {
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setId(preferences.getId());
        userPreferences.setUsername(preferences.getUsername());
        userPreferences.setReleaseYearTo(preferences.getReleaseYearTo());
        userPreferences.setReleaseYearFrom(preferences.getReleaseYearFrom());
        userPreferences.setRatingThreshold(preferences.getRatingThreshold());
        if (!CollectionUtils.isEmpty(preferences.getFavoriteActors())) {
            List<Long> actorIds = new ArrayList<>();
            for (Person person : preferences.getFavoriteActors()) {
                actorIds.add(person.getId());
            }
            userPreferences.setFavoriteActors(actorIds);
        }
        if (!CollectionUtils.isEmpty(preferences.getFavoriteTvGenres())) {
            userPreferences.setFavoriteTvGenres(getGenreIds(preferences.getFavoriteTvGenres()));
        }
        if (!CollectionUtils.isEmpty(preferences.getFavoriteMovieGenres())) {
            userPreferences.setFavoriteMovieGenres(getGenreIds(preferences.getFavoriteMovieGenres()));
        }
        if (!CollectionUtils.isEmpty(preferences.getTvGenresToExclude())) {
            userPreferences.setTvGenresToExclude(getGenreIds(preferences.getTvGenresToExclude()));
        }
        if (!CollectionUtils.isEmpty(preferences.getMovieGenresToExclude())) {
            userPreferences.setMovieGenresToExclude(getGenreIds(preferences.getMovieGenresToExclude()));
        }

        preferencesRepository.saveAndFlush(userPreferences);
        return getPreferences(username);
    }

    private List<Genre> getGenreList(List<Long> genreIds, List<Genre> allGenres) {
        List<Genre> favoriteGenres = new ArrayList<>();
        for(Genre genre : allGenres) {
            if (genreIds.contains((long) genre.getId())) {
                favoriteGenres.add(genre);
            }
        }
        return favoriteGenres;
    }

    private List<Long> getGenreIds(List<Genre> genres) {
        List<Long> genreIds = new ArrayList<>();
        for(Genre genre : genres) {
            genreIds.add((long) genre.getId());
        }
        return genreIds;
    }
}
