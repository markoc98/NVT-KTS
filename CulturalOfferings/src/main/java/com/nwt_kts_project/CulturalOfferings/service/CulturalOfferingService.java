package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.repository.CulturalOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CulturalOfferingService implements ServiceInterface<CulturalOffering> {

    @Autowired
    private CulturalOfferingRepository culturalOfferingRepository;


    @Override
    public List<CulturalOffering> findAll() {
        return culturalOfferingRepository.findAll();
    }
    
    public Page<CulturalOffering> findAll(Pageable pageable) {
        return culturalOfferingRepository.findAll(pageable);
    }

    @Override
    public CulturalOffering findOne(Long id) {
        return culturalOfferingRepository.findById(id).orElse(null);
    }

    @Override
    public CulturalOffering create(CulturalOffering entity) throws Exception {
        if(culturalOfferingRepository.findByName(entity.getName()) != null)
        {
            throw new Exception("Cultural offering with such name already exists");
        }
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public CulturalOffering update(CulturalOffering entity, Long id) throws Exception {
        CulturalOffering cultOff = culturalOfferingRepository.findById(id).orElse(null);

        if(cultOff == null)
        {
            throw new Exception("Cultural offering with such id doesn't exist");
        }

        if(entity.getName() != null)
        {
            cultOff.setName(entity.getName());
        }

        if(entity.getDescription() != null)
        {
            cultOff.setDescription(entity.getDescription());
        }

        if(entity.getLocation() != null)
        {
            cultOff.setLocation(entity.getLocation());
        }



        return culturalOfferingRepository.save(cultOff);
    }

    @Override
    public void delete(Long id) throws Exception {
        CulturalOffering dbCultOff = culturalOfferingRepository.findById(id).orElse(null);
        if(dbCultOff == null){
            throw new Exception("Cultural offering with such id doesn't exist");
        }
        culturalOfferingRepository.delete(dbCultOff);
    }

	public CulturalOffering findByName(String name) {
		// TODO Auto-generated method stub
		CulturalOffering cultOff = culturalOfferingRepository.findByNameContainingIgnoreCase(name);
	
		return cultOff;
	}
}
