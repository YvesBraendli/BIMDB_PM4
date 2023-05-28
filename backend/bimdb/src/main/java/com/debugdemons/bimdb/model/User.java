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
}