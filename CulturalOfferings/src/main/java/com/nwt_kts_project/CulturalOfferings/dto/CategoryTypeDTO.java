package com.nwt_kts_project.CulturalOfferings.dto;

import javax.validation.constraints.NotBlank;

public class CategoryTypeDTO {
	
	private Long id;
	
	@NotBlank(message = "Name cannot be empty.")
	private String name;

	public CategoryTypeDTO() {
	}

	public CategoryTypeDTO(Long id, @NotBlank(message = "Name cannot be empty.") String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
