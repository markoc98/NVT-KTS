package com.nwt_kts_project.CulturalOfferings.repository;


import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CulturalOfferingRepository extends JpaRepository<CulturalOffering, Long> {

    CulturalOffering findByName(String name);
    Page<CulturalOffering> findByNameContainingIgnoreCase(String name,Pageable pageable);

}

