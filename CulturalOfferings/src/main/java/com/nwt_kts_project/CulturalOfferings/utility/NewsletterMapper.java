package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;

import java.text.SimpleDateFormat;

public class NewsletterMapper implements MapperInterface<Newsletter, NewsletterDTO>{


	@Override
	public Newsletter toEntity(NewsletterDTO dto)  throws  Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return new Newsletter(dto.getTitle(),dto.getContent(),dto.getDate());
	}

	@Override
	public NewsletterDTO toDto(Newsletter entity) {

		return new NewsletterDTO(entity.getId(),entity.getTitle(),entity.getContent(),entity.getDate(),entity.getCulturalOffering());
	}																				//to revert these changes just delete sdf.formtat( )


	public NewsletterDTO toDto1(Newsletter entity) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return new NewsletterDTO(entity.getId(),entity.getTitle(),entity.getContent(), sdf.format(entity.getDate()),entity.getCulturalOffering());
	}

}
