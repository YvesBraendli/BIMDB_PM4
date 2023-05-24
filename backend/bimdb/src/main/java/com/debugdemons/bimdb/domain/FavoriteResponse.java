package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteResponse {

    @JsonProperty("isFavorite")
    private boolean isFavorite;

    public FavoriteResponse(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
