package com.nwt_kts_project.CulturalOfferings.model;

import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "category_types")
public class CategoryType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ArrayList<CulturalOffering>culturalOfferings;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Category category;
    

	public CategoryType() {
	}

	public CategoryType(Long id, String name, ArrayList<CulturalOffering> culturalOfferings, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.culturalOfferings = culturalOfferings;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    

}
