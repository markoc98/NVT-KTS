package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryTypeDTO;
import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import com.nwt_kts_project.CulturalOfferings.service.CategoryTypeService;
import com.nwt_kts_project.CulturalOfferings.utility.CategoryTypeMapper;
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
@RequestMapping(value = "/api/categoryTypes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryTypeController {
	
	@Autowired
	private CategoryTypeService categoryTypeService;
	
	private CategoryTypeMapper categoryTypeMapper;
	
	public CategoryTypeController() {
		categoryTypeMapper = new CategoryTypeMapper();
	}
	
	//GET ZAHTEV ZA DOBAVLJANJE JEDNOG TIPA KATEGORIJE PO ID-u
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<CategoryTypeDTO> getCategoryType(@PathVariable Long id){

        CategoryType categoryType = categoryTypeService.findOne(id);

        if(categoryType == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryTypeMapper.toDto(categoryType), HttpStatus.OK);
    }
	
    //GET ZAHTEV ZA DOBAVLJANJE SVIH TIPOVA KATEGORIJA
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryTypeDTO>> getAllCategoryTypes(Pageable pageable) {
    	
    	Page<CategoryType> page = categoryTypeService.findAll(pageable);
        //List<CategoryType> categoryTypes = categoryTypeService.findAll();
    	List<CategoryTypeDTO> categoryTypeDTOS = toCategoryTypeDTOList(page.toList());
        Page<CategoryTypeDTO> pageCategoryTypeDTOS = new PageImpl<>(categoryTypeDTOS,page.getPageable(),page.getTotalElements());


        return new ResponseEntity<>(pageCategoryTypeDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="pageable",method = RequestMethod.GET)
    public ResponseEntity<List<CategoryTypeDTO>> getAllCategoryTypesPageable(Pageable pageable) {

        Page<CategoryType> page = categoryTypeService.findAll(pageable);
        //List<CategoryType> categoryTypes = categoryTypeService.findAll();
        List<CategoryTypeDTO> categoryTypeDTOS = toCategoryTypeDTOList(page.toList());
        //Page<CategoryTypeDTO> pageCategoryTypeDTOS = new PageImpl<>(categoryTypeDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(categoryTypeDTOS, HttpStatus.OK);
    }

    //GET ZAHTEV ZA DOBAVLJANJE SVIH TIPOVA KATEGORIJA
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/getbycategoryid/{catId}" ,method = RequestMethod.GET)
    public ResponseEntity<List<CategoryTypeDTO>> getAllCategoryTypes(@PathVariable Long catId, Pageable pageable) {

        Page<CategoryType> page = categoryTypeService.findAll(pageable);
        //List<CategoryType> categoryTypes = categoryTypeService.findAll();
        List<CategoryTypeDTO> categoryTypeDTOS = toCategoryTypeDTOList(page.toList());
        Page<CategoryTypeDTO> pageCategoryTypeDTOS = new PageImpl<>(categoryTypeDTOS,page.getPageable(),page.getTotalElements());

        List<CategoryType> novaLista = new ArrayList<>();
        for(CategoryType type: page)
        {
            if(type.getCategory().getId() == catId)
            {
                novaLista.add(type);
            }
        }
        List<CategoryTypeDTO> typesForBE = toCategoryTypeDTOList(novaLista);
        return new ResponseEntity<>(typesForBE, HttpStatus.OK);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategoryType(@PathVariable Long id){
        try {
            categoryTypeService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
