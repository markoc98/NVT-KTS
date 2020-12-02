package com.nwt_kts_project.CulturalOfferings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long>  {
	
	CategoryType findByName(String name);

    CategoryType findByNameAndIdNot(String name, Long id);

    List<CategoryType> findByCategoryId(Long categoryId);

    CategoryType findByCategoryIdAndId(Long categoryId, Long id);

}
