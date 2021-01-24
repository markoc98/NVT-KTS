package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.CulturalOfferingDTO;
import com.nwt_kts_project.CulturalOfferings.model.*;
import com.nwt_kts_project.CulturalOfferings.repository.CategoryRepository;
import com.nwt_kts_project.CulturalOfferings.repository.CategoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CulturalOfferingMapper implements MapperInterface<CulturalOffering, CulturalOfferingDTO>{

    @Override
    public CulturalOffering toEntity(CulturalOfferingDTO dto) {
        return new CulturalOffering(dto.getId(), dto.getLocation(), dto.getName(), dto.getDescription());
    }

    @Override
    public CulturalOfferingDTO toDto(CulturalOffering entity) {
        return new CulturalOfferingDTO(entity.getId(), entity.getLocation(), entity.getName(), entity.getDescription(),entity.getCategory());
    }
}
