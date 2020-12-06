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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String role;


    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<CulturalOffering> subscribedTo = new HashSet<>();

    public User() {
    }

    public User(String email, String password, String username, String role,
                Set<Review> reviews, Set<CulturalOffering> subscribedTo) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
        this.reviews = reviews;
        this.subscribedTo = subscribedTo;
    }

    public User(String email, String username) {
        this.email = email;

        this.username = username;
        this.role = "CUSTOMER";

    }


    public User(Long userId) {
		// TODO Auto-generated constructor stub
    	this.id = userId;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", reviews=" + reviews +
                ", subscribedTo=" + subscribedTo +
                '}';
    }
}

