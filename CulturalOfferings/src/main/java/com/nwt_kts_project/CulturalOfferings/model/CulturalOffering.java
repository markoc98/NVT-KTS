package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "cultural_offerings")
public class CulturalOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private String name;

    private String description;

    private double rating;

    private String pictures;

    //@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //private ArrayList<User> subscribedUsers = new ArrayList<>();

    //@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //private ArrayList<Review>reviews;


    //@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //private ArrayList<Newsletter> newsletter = new ArrayList<>();

    //@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //private Category category;

    //@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //private CategoryType categoryType;


    public CulturalOffering() {
    }

    public CulturalOffering(Long id, String location, String name, String description, double rating, String pictures) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.pictures = pictures;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
