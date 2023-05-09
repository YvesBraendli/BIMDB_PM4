package com.debugdemons.bimdb.domain;

import com.debugdemons.bimdb.deserializers.WatchProviderResultDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(using = WatchProviderResultDeserializer.class)
public class WatchProvidersResult implements Serializable {

    @JsonProperty("watchProviders")
    private List<WatchProviders> watchProviders;

    public List<WatchProviders> getWatchProviders() {
        return watchProviders;
    }

    public void setWatchProviders(List<WatchProviders> watchProviderPerCountries) {
        this.watchProviders = watchProviderPerCountries;
    }
}
