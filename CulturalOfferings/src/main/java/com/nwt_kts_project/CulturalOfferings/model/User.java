package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)

    private String password;

    private String name;

    private String last_name;

    private String username;

    private String role;

    private String location;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<CulturalOffering> subscribedTo = new HashSet<>();

    public User() {
    }

    public User(String email, String password, String name, String last_name, String username, String role, String location, Set<Review> reviews, Set<CulturalOffering> subscribedTo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.username = username;
        this.role = role;
        this.location = location;
        this.reviews = reviews;
        this.subscribedTo = subscribedTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<CulturalOffering> getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(Set<CulturalOffering> subscribedTo) {
        this.subscribedTo = subscribedTo;
    }
}

