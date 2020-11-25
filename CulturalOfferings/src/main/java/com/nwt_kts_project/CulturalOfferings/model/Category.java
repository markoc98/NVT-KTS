package com.nwt_kts_project.CulturalOfferings.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<CulturalOffering>culturalOfferings = new HashSet<>() ;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<CategoryType> categoryTypes = new HashSet<>();
    

	public Category() {
	}

	public Category(Long id, String name, Set<CulturalOffering> culturalOfferings,
			Set<CategoryType> categoryTypes) {
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

	public Set<CulturalOffering> getCulturalOfferings() {
		return culturalOfferings;
	}

	public void setCulturalOfferings(Set<CulturalOffering> culturalOfferings) {
		this.culturalOfferings = culturalOfferings;
	}

	public Set<CategoryType> getCategoryTypes() {
		return categoryTypes;
	}

	public void setCategoryTypes(Set<CategoryType> categoryTypes) {
		this.categoryTypes = categoryTypes;
	}
    
    

}
