package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;

public class NewsletterMapper implements MapperInterface<Newsletter, NewsletterDTO>{

	@Override
	public Newsletter toEntity(NewsletterDTO dto) {
		return new Newsletter(dto.getId(),dto.getTitle(),dto.getContent(),dto.getDate(),dto.getPictures(),dto.getCulturalOffering());
	}

	@Override
	public NewsletterDTO toDto(Newsletter entity) {
		return new NewsletterDTO(entity.getId(),entity.getTitle(),entity.getContent(),entity.getDate(),entity.getPictures(),entity.getCulturalOffering());
	}
	
	

}
