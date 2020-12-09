package com.nwt_kts_project.CulturalOfferings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryDTO;
import com.nwt_kts_project.CulturalOfferings.model.Category;
import com.nwt_kts_project.CulturalOfferings.service.CategoryService;
import com.nwt_kts_project.CulturalOfferings.utility.CategoryMapper;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	private CategoryMapper catMapper;
	
	public CategoryController() {
		catMapper = new CategoryMapper();
	}
	
	//GET ZAHTEV ZA DOBAVLJANJE JEDNE KATEGORIJE PO ID-u
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id){

        Category cat = catService.findOne(id);

        if(cat == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(catMapper.toDto(cat), HttpStatus.OK);
    }
    
    //GET ZAHTEV ZA DOBAVLJANJE SVIH KATEGORIJA
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = catService.findAll();

        return new ResponseEntity<>(toCategoryDTOList(categories), HttpStatus.OK);
    }

    private List<CategoryDTO> toCategoryDTOList(List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category cat: categories) {
            categoryDTOS.add(catMapper.toDto(cat));
        }
        return categoryDTOS;
    }
    
    //POST ZAHTEV KREIRANJE NOVE KATEGORIJE
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category cat;
        try {
            cat = catService.create(catMapper.toEntity(categoryDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(catMapper.toDto(cat), HttpStatus.CREATED);
    }
    
    ///PUT ZAHTEV UPDATE POSTOJECE KATEGORIJE
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        Category cat;
        try {
            cat = catService.update(catMapper.toEntity(categoryDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(catMapper.toDto(cat), HttpStatus.OK);
    }
    
    //DELETE ZAHTEV BRISANJE POSTOJECE KATEGORIJE
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        try {
            catService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
