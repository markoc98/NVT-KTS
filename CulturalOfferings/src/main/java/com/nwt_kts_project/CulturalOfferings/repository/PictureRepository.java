package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findByName(String name);

    Optional<Picture> findByReviewId(Long reviewID);

    Optional<Picture> findByNewsletterId(Long newsletterID);

    Optional<Picture> findByCulturalOfferingId(Long culturalOfferingID);
}
