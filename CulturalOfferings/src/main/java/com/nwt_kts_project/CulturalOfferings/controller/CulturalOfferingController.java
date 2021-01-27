package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.CulturalOfferingDTO;
import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.service.PictureService;
import com.nwt_kts_project.CulturalOfferings.utility.CulturalOfferingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cultural", produces = MediaType.APPLICATION_JSON_VALUE)
public class CulturalOfferingController {

    @Autowired
    private CulturalOfferingService cultOffService;

    @Autowired
    private PictureService pictureService;

    private CulturalOfferingMapper cultOffMapper;

    public CulturalOfferingController(){
        cultOffMapper = new CulturalOfferingMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')" + "|| hasRole('ROLE_USER')")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CulturalOfferingDTO> getCulturalOffering(@PathVariable Long id){

        CulturalOffering cultOff = cultOffService.findOne(id);


        if(cultOff == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')" + "|| hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CulturalOfferingDTO>> getAllCulturalOfferings(@PageableDefault(size = 100, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        //List<CulturalOffering> cultOffs = cultOffService.findAll();

        Page<CulturalOffering> page = cultOffService.findAll(pageable);
    	List<CulturalOfferingDTO> cultOffDTOS = toCultOffDTOList(page.toList());
        Page<CulturalOfferingDTO> pageCultOffDTOS = new PageImpl<>(cultOffDTOS,page.getPageable(),page.getTotalElements());



        return new ResponseEntity<>(pageCultOffDTOS, HttpStatus.OK);
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
    public ResponseEntity<CulturalOfferingDTO> createCulturalOffering(@RequestBody CulturalOfferingDTO cultOffDTO, @RequestBody MultipartFile file) throws Exception {

        if(file != null)
        {
            Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                    PictureCompression.compressBytes(file.getBytes()), cultOffMapper.toEntity(cultOffDTO));
            pictureService.create(img);
        }
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
        CulturalOffering cultOff = cultOffMapper.toEntity(cultOffDTO);
        try {
            cultOff = cultOffService.update(cultOff, id);
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
    
    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/getDataForMap/{location}", method=RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferingDTO>> getDataForMap(@PathVariable String location){

    	List<CulturalOffering> cultOffs = cultOffService.findAll();
    	
    	List<CulturalOffering> found = new ArrayList<CulturalOffering>();
    	
    	for (CulturalOffering co : cultOffs) {
    		if (co.getLocation().equalsIgnoreCase(location)) {
    			found.add(co);
    		}
    	}
    	
    	List<CulturalOfferingDTO> foundDTO = toCultOffDTOList(found);

    	System.out.println(foundDTO.size());
    	
    	return new ResponseEntity<>(foundDTO, HttpStatus.OK);
    	
    }
    
    @RequestMapping(value = "/search/{name}", method= RequestMethod.GET)
    ResponseEntity<CulturalOfferingDTO> getCulturalOfferingByName(@PathVariable String name){
    	
    	CulturalOffering cultOff = cultOffService.findByName(name);
    	
    	if(cultOff == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	
    	return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.OK);
    	
    }

}
