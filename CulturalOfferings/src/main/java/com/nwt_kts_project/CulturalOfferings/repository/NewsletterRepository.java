package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {


}