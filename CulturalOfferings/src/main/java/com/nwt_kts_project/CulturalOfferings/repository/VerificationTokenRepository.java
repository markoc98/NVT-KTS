package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
