package com.debugdemons.bimdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "preferred_original_language")
    private String preferredOriginalLanguage;

    @Column(name = "use_date_filters")
    private Boolean useDateFilter;

    @Column(name = "use_rating_filter")
    private Boolean useRatingFilter;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getPreferredOriginalLanguage() {
        return preferredOriginalLanguage;
    }

    public void setPreferredOriginalLanguage(String preferredOriginalLanguage) {
        this.preferredOriginalLanguage = preferredOriginalLanguage;
    }

    public Boolean getUseDateFilter() {
        return useDateFilter;
    }

    public void setUseDateFilter(Boolean useDateFilters) {
        this.useDateFilter = useDateFilters;
    }

    public Boolean getUseRatingFilter() {
        return useRatingFilter;
    }

    public void setUseRatingFilter(Boolean useRatingfilter) {
        this.useRatingFilter = useRatingfilter;
    }
}