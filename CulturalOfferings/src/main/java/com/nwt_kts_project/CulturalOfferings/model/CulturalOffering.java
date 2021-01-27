package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cultural_offerings")
public class CulturalOffering {

    @Id
    @Column(name="cultural_offering_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
    
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    @Column(nullable = false)
    private double rating;
    
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;

    @OneToMany(mappedBy = "culturalOffering",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Picture> pictures = new HashSet<>();

    @ManyToMany(mappedBy = "subscribedTo",fetch = FetchType.EAGER)
    private transient  Set<User> subscribedUsers ;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Review>reviews = new HashSet<>();

    @OneToMany(mappedBy = "CulturalOffering",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private transient Set<Newsletter> newsletter = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CategoryType categoryType;

    public CulturalOffering() {
    }

    public CulturalOffering(Long id, String location, String name, String description) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
    }

    public CulturalOffering(String location, String name, String description, double rating) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public CulturalOffering(String name, String location, String description, Category categoryType)
    {
        this.name = name;
        this.location = location;
        this.description = description;
        this.category = categoryType;
    }
    
 
	public CulturalOffering(Long id, String location, String name, String description, double rating,
                            Set<Picture> pictures, Set<User> subscribedUsers, Set<Review> reviews, Set<Newsletter> newsletter,
                            Category category, CategoryType categoryType) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.pictures = pictures;
        this.subscribedUsers = subscribedUsers;
        this.reviews = reviews;
        this.newsletter = newsletter;
        this.category = category;
        this.categoryType = categoryType;
    }
    
    public CulturalOffering(String location, String name, String description) {
		super();
		this.location = location;
		this.name = name;
		this.description = description;
	}

	public CulturalOffering(String location, String name, String description, double rating, Category category,
			CategoryType categoryType) {
		super();
		this.location = location;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.category = category;
		this.categoryType = categoryType;
	}

	public CulturalOffering(String name, String description, double rating, CategoryType categoryType) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.categoryType = categoryType;
	}
	

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Set<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(Set<User> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Newsletter> getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Set<Newsletter> newsletter) {
        this.newsletter = newsletter;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Picture> getPictures() {
        return pictures;
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
