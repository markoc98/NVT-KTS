package com.nwt_kts_project.CulturalOfferings.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Picture;

public class NewsletterDTO {
	
	private Long id;
	@NotBlank(message = "Title cannot be empty.")
	private String title;
	@NotBlank(message = "Content cannot be empty.")
	private String content;
	@NotBlank(message = "Date cannot be empty.")
	private Date date;
	private Set<Picture> pictures = new HashSet<>();
	private CulturalOffering culturalOffering;

	private String dateString;
	//private Long cultOffid = this.culturalOffering.getId();
	
	public NewsletterDTO() {}

	public NewsletterDTO(Long id,@NotBlank(message = "Title cannot be empty.") String title,@NotBlank(message = "Content cannot be empty.") String content,@NotBlank(message = "Date cannot be empty.") Date date, Set<Picture> pictures,
			CulturalOffering culturalOffering) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.pictures = pictures;
		this.culturalOffering = culturalOffering;
	}

	public NewsletterDTO(Long id2, String title2, String content2, Date date2) {
		this.id = id2;
		this.title = title2;
		this.content = content2;
		this.date = date2;
	}


	public NewsletterDTO(@NotBlank(message = "Title cannot be empty.")String title,@NotBlank(message = "Content cannot be empty.") String content, @NotBlank(message = "Date cannot be empty.")Date date) {
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public NewsletterDTO(Long id2, String title2, String content2, Date date2, CulturalOffering culturalOffering2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.title = title2;
		this.content = content2;
		this.date = date2;
		this.culturalOffering = culturalOffering2;
	}


	public NewsletterDTO(Long id2, @NotBlank(message = "Title cannot be empty.") String title, @NotBlank(message = "Content cannot be empty.") String content, String dateString, CulturalOffering culturalOffering) {
		this.id = id2;
		this.title = title;
		this.content = content;
		this.culturalOffering = culturalOffering;
		this.dateString = dateString;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public CulturalOffering getCulturalOffering() {
		return culturalOffering;
	}

	public void setCulturalOffering(CulturalOffering culturalOffering) {
		this.culturalOffering = culturalOffering;
	}
	
	
}
