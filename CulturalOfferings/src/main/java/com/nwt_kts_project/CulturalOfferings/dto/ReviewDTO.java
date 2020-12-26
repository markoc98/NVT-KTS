package com.nwt_kts_project.CulturalOfferings.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Picture;

public class ReviewDTO {
	
	private Long id;
	
	
	@NotBlank(message = "Rating cannot be empty.")
	private double rating;

	private String comment;
	private CulturalOffering culturalOffering;

	private Set<Picture> pictures;
	private Long user;
	
	public ReviewDTO() {}


	public ReviewDTO(Long id, String comment,@NotBlank(message = "Rating cannot be empty.") double rating, CulturalOffering culutralOffering, Set<Picture> pictures,
			Long user) {
		super();
		this.id = id;
		this.comment = comment;
		this.rating = rating;
		this.culturalOffering = culturalOffering;
		this.pictures = pictures;
		this.user = user;
	}
	
	public ReviewDTO(String comment,@NotBlank(message = "Rating cannot be empty.") double rating) {
		super();
		this.comment = comment;
		this.rating = rating;
	}

	public ReviewDTO(Long id2, String comment2, double rating2) {
		this.id = id2;
		this.comment = comment2;
		this.rating = rating2;
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
