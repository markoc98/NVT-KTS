package com.nwt_kts_project.CulturalOfferings.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "category_type_table")
public class CategoryType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<CulturalOffering> culturalOfferings = new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Category category;
    

	public CategoryType() {
	}

	public CategoryType(Long id, String name, Set<CulturalOffering> culturalOfferings, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.culturalOfferings = culturalOfferings;
		this.category = category;
	}
	
	public CategoryType(String name) {
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

	public Set<CulturalOffering> getCulturalOfferings() {
		return culturalOfferings;
	}

	public void setCulturalOfferings(Set<CulturalOffering> culturalOfferings) {
		this.culturalOfferings = culturalOfferings;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    

}
