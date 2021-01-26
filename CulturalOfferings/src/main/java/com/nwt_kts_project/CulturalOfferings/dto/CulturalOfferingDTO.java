package com.nwt_kts_project.CulturalOfferings.dto;

import com.nwt_kts_project.CulturalOfferings.model.*;

public class CulturalOfferingDTO {

    private Long id;
    private String location;
    private String name;
    private String description;
    private double rating;
    private Category categoryType;  
    private double latitude;
    private double longitude;

    public CulturalOfferingDTO() {
    }

    public CulturalOfferingDTO(Long id, String location, String name, String description) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
    }
    
    public CulturalOfferingDTO(Long id2, String location2, String name2, String description2, Category category) {
		super();
    	this.id = id2;
		this.location =location2;
		this.name = name2;
		this.description = description2;
		this.categoryType = category;
	}
    
	public CulturalOfferingDTO(Long id, String location, String name, String description, double rating,
			Category categoryType, double latitude, double longitude) {
		super();
		this.id = id;
		this.location = location;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.categoryType = categoryType;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public CulturalOfferingDTO(Long id, String location, String name, String description, Category categoryType,double rating,
			double latitude, double longitude) {
		super();
		this.id = id;
		this.location = location;
		this.name = name;
		this.description = description;
		this.categoryType = categoryType;
		this.rating = rating;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public Category getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Category categoryType) {
		this.categoryType = categoryType;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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

    @Override
    public String toString() {
        return "CulturalOfferingDTO{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
