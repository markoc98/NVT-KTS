package com.nwt_kts_project.CulturalOfferings.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.nwt_kts_project.CulturalOfferings.constants.CategoryConstants.*;
import static org.junit.Assert.assertEquals;

import com.nwt_kts_project.CulturalOfferings.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CategoryServiceIntegrationTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testFindAllCategories() {
		List<Category> found = categoryService.findAll();
		assertEquals(DB_CATEGORIES_COUNT, found.size());
	}
	
	@Test
    public void testFindByIdCategory() {
        Category found = categoryService.findOne(DB_CATEGORY_ID);
        assertEquals(DB_CATEGORY_ID, found.getId());
    }
	
	@Test
    public void testCreateCategory() throws Exception {
        Category category = new Category(NEW_CATEGORY2);
        Category created = categoryService.create(category);

        assertEquals(NEW_CATEGORY2, created.getName());
    }
	
	@Test
    public void testDeleteCategory() throws Exception {
        categoryService.delete(DB_DEL_CATEGORY_ID);

        Category savedCategory = new Category(DB_DEL_CATEGORY);
        savedCategory.setId(DB_DEL_CATEGORY_ID);
    }
	
	@Test
    public void testUpdateCategory() throws Exception {
        Category category = new Category(NEW_CATEGORY3);
        Category created = categoryService.update(category,DB_CATEGORY_ID);

        assertEquals(NEW_CATEGORY3, created.getName());
    }

}
