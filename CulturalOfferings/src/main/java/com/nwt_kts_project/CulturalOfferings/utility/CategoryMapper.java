package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryDTO;
import com.nwt_kts_project.CulturalOfferings.model.Category;

public class CategoryMapper implements MapperInterface <Category, CategoryDTO>{

	@Override
	public Category toEntity(CategoryDTO dto) {
		return new Category(dto.getName());
	}

	@Override
	public CategoryDTO toDto(Category entity) {
		return new CategoryDTO(entity.getId(), entity.getName());
	}

}
