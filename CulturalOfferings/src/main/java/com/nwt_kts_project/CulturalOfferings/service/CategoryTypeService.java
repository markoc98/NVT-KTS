package com.nwt_kts_project.CulturalOfferings.service;

import java.util.List;

import com.nwt_kts_project.CulturalOfferings.model.Category;
import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import com.nwt_kts_project.CulturalOfferings.repository.CategoryTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryTypeService{
	
	@Autowired
    private CategoryTypeRepository categoryTypeRepository;

    @Autowired
    private CategoryService categoryService;

	public List<CategoryType> findAll() {
		return categoryTypeRepository.findAll();
	}
	
	public Page<CategoryType> findAll(Pageable pageable) {
		return categoryTypeRepository.findAll(pageable);
	}

	public CategoryType findOne(Long id) {
		return categoryTypeRepository.findById(id).orElse(null);
	}

	public CategoryType create(CategoryType entity, Long categoryId) throws Exception {
		if(categoryTypeRepository.findByName(entity.getName()) != null){
            throw new Exception("Category type with given name already exists.");
        }
        Category category = categoryService.findOne(categoryId);
        if(category == null) {
            throw new Exception("Chosen category doesn't exist.");
        }
        entity.setCategory(category);
        return categoryTypeRepository.save(entity);
	}

	public CategoryType update(CategoryType entity, Long id, Long categoryId) throws Exception {
		CategoryType existingCategoryType =  categoryTypeRepository.findByCategoryIdAndId(categoryId, id);
        if(existingCategoryType == null){
            throw new Exception("Category type with given id doesn't exist");
        }
        existingCategoryType.setName(entity.getName());
        if(categoryTypeRepository.findByNameAndIdNot(existingCategoryType.getName(), id) != null){
            throw new Exception("Category type with given name already exists");
        }
        return categoryTypeRepository.save(existingCategoryType);
	}

	public void delete(Long id) throws Exception {
		CategoryType existingCategoryType = categoryTypeRepository.findById(id).orElse(null);
        if(existingCategoryType == null){
            throw new Exception("Category type with given id doesn't exist");
        }
        categoryTypeRepository.delete(existingCategoryType);
	}

}
