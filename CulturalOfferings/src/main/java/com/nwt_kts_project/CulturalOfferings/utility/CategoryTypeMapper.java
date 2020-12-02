package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryTypeDTO;
import com.nwt_kts_project.CulturalOfferings.model.CategoryType;

public class CategoryTypeMapper implements MapperInterface<CategoryType, CategoryTypeDTO> {
	
	@Override
    public CategoryType toEntity(CategoryTypeDTO dto) {
        return new CategoryType(dto.getName());
    }

    @Override
    public CategoryTypeDTO toDto(CategoryType entity) {
        return new CategoryTypeDTO(entity.getId(), entity.getName());
    }

}
