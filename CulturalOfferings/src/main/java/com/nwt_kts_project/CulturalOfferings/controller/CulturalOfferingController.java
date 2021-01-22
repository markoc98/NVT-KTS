package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.CulturalOfferingDTO;
import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.utility.CulturalOfferingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cultural", produces = MediaType.APPLICATION_JSON_VALUE)
public class CulturalOfferingController {

    @Autowired
    private CulturalOfferingService cultOffService;

    private CulturalOfferingMapper cultOffMapper;

    public CulturalOfferingController(){
        cultOffMapper = new CulturalOfferingMapper();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CulturalOfferingDTO> getCulturalOffering(@PathVariable Long id){

        CulturalOffering cultOff = cultOffService.findOne(id);


        if(cultOff == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferingDTO>> getAllCulturalOfferings() {
        List<CulturalOffering> cultOffs = cultOffService.findAll();

        return new ResponseEntity<>(toCultOffDTOList(cultOffs), HttpStatus.OK);
    }

    private List<CulturalOfferingDTO> toCultOffDTOList(List<CulturalOffering> cultOffs){
        List<CulturalOfferingDTO> cultOffDTOS = new ArrayList<>();
        for (CulturalOffering cultOff: cultOffs) {
            cultOffDTOS.add(cultOffMapper.toDto(cultOff));
        }
        return cultOffDTOS;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferingDTO> createCulturalOffering(@RequestBody CulturalOfferingDTO cultOffDTO){
        CulturalOffering cultOff;
        try {
            cultOff = cultOffService.create(cultOffMapper.toEntity(cultOffDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferingDTO> updateCulturalOffering(@RequestBody CulturalOfferingDTO cultOffDTO, @PathVariable Long id){
        CulturalOffering cultOff;
        try {
            cultOff = cultOffService.update(cultOffMapper.toEntity(cultOffDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOffering(@PathVariable Long id){
        try {
            cultOffService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/getDataForMap/{location}", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CulturalOfferingDTO>> getDataForMap(@PathVariable String location){

    	List<CulturalOffering> cultOffs = cultOffService.findAll();
    	
    	List<CulturalOffering> found = new ArrayList<CulturalOffering>();
    	
    	for (CulturalOffering co : cultOffs) {
    		if (co.getLocation().equals(location)) {
    			found.add(co);
    		}
    	}
    	
    	List<CulturalOfferingDTO> foundDTO = toCultOffDTOList(found);
    	
    	return new ResponseEntity<>(foundDTO, HttpStatus.OK);
    	
    }
    
    @RequestMapping(value = "/search", method= RequestMethod.GET)
    ResponseEntity<Page<CulturalOfferingDTO>> getCulturalOfferingByName(@RequestParam(value="name") String name,Pageable pageable){
    	
    	Page<CulturalOffering> page =cultOffService.findByName(name,pageable);
    	
    	if(page.isEmpty()) {
    		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    	}
    	List<CulturalOfferingDTO> cultOffDTOList = toCultOffDTOList(page.toList());
    	Page<CulturalOfferingDTO> cultOffDTOPage = new PageImpl<>(cultOffDTOList,page.getPageable(),page.getTotalElements());
    	
    	System.out.println("Search result: " + cultOffDTOPage.getNumberOfElements() + "" + page.getTotalElements());
    	
    	return new ResponseEntity<>(cultOffDTOPage,HttpStatus.OK);
    	
    }

}
