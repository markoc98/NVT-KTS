package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.CulturalOfferingDTO;
import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.utility.CulturalOfferingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CulturalOfferingDTO> getCulturalOffering(@PathVariable Long id){

        CulturalOffering cultOff = cultOffService.findOne(id);


        if(cultOff == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cultOffMapper.toDto(cultOff), HttpStatus.OK);
    }


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


    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOffering(@PathVariable Long id){
        try {
            cultOffService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
