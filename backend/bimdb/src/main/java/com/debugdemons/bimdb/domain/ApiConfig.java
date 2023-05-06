package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ApiConfig implements Serializable {
    @JsonProperty("images")
    private ApiImagesConfig images;

    @JsonProperty("change_keys")
    private List<String> changeKeys;

    public ApiImagesConfig getImages() {
        return images;
    }

    public void setImages(ApiImagesConfig images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
