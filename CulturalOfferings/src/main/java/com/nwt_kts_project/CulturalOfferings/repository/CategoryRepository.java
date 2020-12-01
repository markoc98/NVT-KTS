package com.nwt_kts_project.CulturalOfferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nwt_kts_project.CulturalOfferings.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name);

}
