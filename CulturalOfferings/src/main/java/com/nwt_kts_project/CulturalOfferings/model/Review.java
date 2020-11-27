package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private double rating;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CulturalOffering CulturalOffering ;

    @OneToMany(mappedBy = "review",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Picture> pictures = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    public Review() {}

    public Review(Long id, String comment, double rating, CulturalOffering culturalOffering, Set<Picture> pictures, User user) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.CulturalOffering = culturalOffering;
        this.pictures = pictures;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }
}