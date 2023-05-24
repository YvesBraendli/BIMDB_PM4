package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("deathday")
    private Date deathday;

    @JsonProperty("name")
    private String name;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("placeOfBirth")
    @JsonAlias("place_of_birth")
    private String placeOfBirth;

    @JsonProperty("profilePath")
    @JsonAlias("profile_path")
    private String profilePath;

    @JsonProperty("combinedCredits")
    @JsonAlias("combined_credits")
    private CombinedCredits combinedCredits;

    @JsonProperty("mediaType")
    @JsonAlias("media_type")
    protected MediaType mediaType;

    public Person() {
        this.mediaType = MediaType.PERSON;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public CombinedCredits getCombinedCredits() {
        return combinedCredits;
    }

    public void setCombinedCredits(CombinedCredits combinedCredits) {
        this.combinedCredits = combinedCredits;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
