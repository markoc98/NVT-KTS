package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.model.Review;

public class ReviewMapper implements MapperInterface <Review, ReviewDTO>{


    @Override
    public Review toEntity(ReviewDTO dto) {
        return new Review(dto.getId(), dto.getComment(), dto.getRating(), dto.getCulturalOffering(), dto.getPictures(), dto.getUser());
    }

    @Override
    public ReviewDTO toDto(Review entity) {
        return new ReviewDTO(entity.getId(), entity.getComment(), entity.getRating(), entity.getCulutralOffering(), entity.getPictures(), entity.getUser().getId());
    }
}
