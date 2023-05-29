package com.debugdemons.bimdb.utils;

import com.debugdemons.bimdb.domain.Genre;

import java.util.Date;
import java.util.List;

public interface Filterable {

    List<Genre> getGenres();

    Date getReleaseDate();

    float getVoteAverage();
}
