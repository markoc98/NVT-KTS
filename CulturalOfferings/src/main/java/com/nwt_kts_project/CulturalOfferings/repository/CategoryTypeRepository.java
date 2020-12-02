package com.nwt_kts_project.CulturalOfferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long>  {

}
