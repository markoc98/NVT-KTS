package com.nwt_kts_project.CulturalOfferings.dto;

import java.util.Set;
import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Picture;

public class ReviewDTO {
	
	private Long id;
	
	private String comment;
	private double rating;
	private CulturalOffering culturalOffering;
	private Set<Picture> pictures;
	private Long user;
	
	public ReviewDTO() {}

	public ReviewDTO(Long id, String comment, double rating, CulturalOffering culturalOffering, Set<Picture> pictures,
					 Long user) {
		super();
		this.id = id;
		this.comment = comment;
		this.rating = rating;
		this.culturalOffering = culturalOffering;
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

	public CulturalOffering getCulturalOffering() {
		return culturalOffering;
	}

	public void setCulturalOffering(CulturalOffering culturalOffering) {
		this.culturalOffering = culturalOffering;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
}
