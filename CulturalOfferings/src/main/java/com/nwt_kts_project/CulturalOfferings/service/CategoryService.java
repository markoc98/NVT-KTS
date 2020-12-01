package com.nwt_kts_project.CulturalOfferings.service;

import java.util.List;

import com.nwt_kts_project.CulturalOfferings.model.Category;
import com.nwt_kts_project.CulturalOfferings.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ServiceInterface<Category>{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category create(Category category) throws Exception {
		if(categoryRepository.findByName(category.getName()) != null){
            throw new Exception("Category with given name address already exists");
        }
        return categoryRepository.save(category);
	}

	@Override
	public Category update(Category category, Long id) throws Exception {
		Category existingCat =  categoryRepository.findById(id).orElse(null);
        if(existingCat == null){
            throw new Exception("Category with given id doesn't exist");
        }
        existingCat.setName(category.getName());
        return categoryRepository.save(existingCat);
	}

	@Override
	public void delete(Long id) throws Exception {
		Category existingCat = categoryRepository.findById(id).orElse(null);
        if(existingCat == null){
            throw new Exception("Category with given id doesn't exist");
        }
        categoryRepository.delete(existingCat);
	}

}
