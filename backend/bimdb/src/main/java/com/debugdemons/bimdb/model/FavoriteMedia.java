package com.debugdemons.bimdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class FavoriteMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type", updatable = false)
    private String type;

    @Column(name = "api_id", updatable = false)
    private Long apiId;

    public FavoriteMedia(User user, String type, Long apiId) {
        this.user = user;
        this.type = type;
        this.apiId = apiId;
    }

    public FavoriteMedia() {
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public Long getApiId() {
        return apiId;
    }

}
