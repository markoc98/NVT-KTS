package com.nwt_kts_project.CulturalOfferings.repository;


import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CulturalOfferingRepository extends JpaRepository<CulturalOffering, Long> {


}

