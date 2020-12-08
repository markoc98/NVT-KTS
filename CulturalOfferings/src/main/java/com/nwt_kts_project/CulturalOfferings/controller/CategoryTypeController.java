package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryTypeDTO;
import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import com.nwt_kts_project.CulturalOfferings.service.CategoryTypeService;
import com.nwt_kts_project.CulturalOfferings.utility.CategoryTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categoryTypes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryTypeController {
	
	@Autowired
	private CategoryTypeService categoryTypeService;
	
	private CategoryTypeMapper categoryTypeMapper;
	
	public CategoryTypeController() {
		categoryTypeMapper = new CategoryTypeMapper();
	}
	
	//GET ZAHTEV ZA DOBAVLJANJE JEDNOG TIPA KATEGORIJE PO ID-u
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CategoryTypeDTO> getCategoryType(@PathVariable Long id){

        CategoryType categoryType = categoryTypeService.findOne(id);

        if(categoryType == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryTypeMapper.toDto(categoryType), HttpStatus.OK);
    }
	
    //GET ZAHTEV ZA DOBAVLJANJE SVIH TIPOVA KATEGORIJA
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryTypeDTO>> getAllCategoryTypes() {
        List<CategoryType> categoryTypes = categoryTypeService.findAll();

        return new ResponseEntity<>(toCategoryTypeDTOList(categoryTypes), HttpStatus.OK);
    }
    
    private List<CategoryTypeDTO> toCategoryTypeDTOList(List<CategoryType> categoryTypes){
        List<CategoryTypeDTO> categoryTypeDTOS = new ArrayList<>();
        for (CategoryType categoryType: categoryTypes) {
            categoryTypeDTOS.add(categoryTypeMapper.toDto(categoryType));
        }
        return categoryTypeDTOS;
    }
    
    //POST ZAHTEV KREIRANJE NOVOG TIPA KATEGORIJE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/catId/{categoryId}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryTypeDTO> createCategoryType(@RequestBody CategoryTypeDTO categoryTypeDTO, @PathVariable Long categoryId){
        CategoryType categoryType;
        try {
            categoryType = categoryTypeService.create(categoryTypeMapper.toEntity(categoryTypeDTO), categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoryTypeMapper.toDto(categoryType), HttpStatus.CREATED);
    }
    
    //PUT ZAHTEV UPDATE POSTOJECEG TIPA KATEGORIJE
    @RequestMapping(value="/catId/{categoryId}/id/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryTypeDTO> updateCategoryType(
            @RequestBody CategoryTypeDTO categoryTypeDTO,@PathVariable Long categoryId, @PathVariable Long id){
        CategoryType categoryType;
        try {
            categoryType = categoryTypeService.update(categoryTypeMapper.toEntity(categoryTypeDTO), id, categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoryTypeMapper.toDto(categoryType), HttpStatus.OK);
    }
    
    //DELETE ZAHTEV BRISANJE POSTOJECEG TIPA KATEGORIJE
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategoryType(@PathVariable Long id){
        try {
            categoryTypeService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
