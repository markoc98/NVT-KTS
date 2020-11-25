package com.nwt_kts_project.CulturalOfferings.model;

import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ArrayList<CulturalOffering>culturalOfferings;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ArrayList<CategoryType>categoryTypes;
    

	public Category() {
	}

	public Category(Long id, String name, ArrayList<CulturalOffering> culturalOfferings,
			ArrayList<CategoryType> categoryTypes) {
		super();
		this.id = id;
		this.name = name;
		this.culturalOfferings = culturalOfferings;
		this.categoryTypes = categoryTypes;
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

	public ArrayList<CulturalOffering> getCulturalOfferings() {
		return culturalOfferings;
	}

	public void setCulturalOfferings(ArrayList<CulturalOffering> culturalOfferings) {
		this.culturalOfferings = culturalOfferings;
	}

	public ArrayList<CategoryType> getCategoryTypes() {
		return categoryTypes;
	}

	public void setCategoryTypes(ArrayList<CategoryType> categoryTypes) {
		this.categoryTypes = categoryTypes;
	}
    
    

}
