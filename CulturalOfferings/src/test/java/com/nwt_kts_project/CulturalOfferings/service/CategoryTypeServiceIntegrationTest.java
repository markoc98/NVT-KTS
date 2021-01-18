package com.nwt_kts_project.CulturalOfferings.service;

import static com.nwt_kts_project.CulturalOfferings.constants.CategoryTypeConstants.*;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nwt_kts_project.CulturalOfferings.model.CategoryType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CategoryTypeServiceIntegrationTest {
	
	@Autowired
	private CategoryTypeService categoryTypeService;
	
	@Test
	public void testFindAllCategoryTypes() {
		List<CategoryType> found = categoryTypeService.findAll();
		assertEquals(DB_CATEGORY_TYPES_COUNT, found.size());
	}
	
	@Test
    public void testFindByIdCategoryType() {
        CategoryType found = categoryTypeService.findOne(DB_CATEGORY_TYPE_ID);
        assertEquals(DB_CATEGORY_TYPE_ID, found.getId());
    }
	
	@Test
    public void testCreateCategoryType() throws Exception {
        CategoryType categoryType = new CategoryType(NEW_CATEGORY_TYPE2);
        CategoryType created = categoryTypeService.create(categoryType, 2L);

        assertEquals(NEW_CATEGORY_TYPE2, created.getName());
    }
	
	@Test
    public void testDeleteCategoryType() throws Exception {
        categoryTypeService.delete(DB_DEL_CATEGORY_TYPE_ID);

        CategoryType savedCategoryType = new CategoryType(DB_DEL_CATEGORY_TYPE);
        savedCategoryType.setId(DB_DEL_CATEGORY_TYPE_ID);
    }
	
	@Test
    public void testUpdateCategoryType() throws Exception {
        CategoryType categoryType = new CategoryType(NEW_CATEGORY_TYPE3);
        CategoryType created = categoryTypeService.update(categoryType,DB_CATEGORY_TYPE_ID2, 2L);

        assertEquals(NEW_CATEGORY_TYPE3, created.getName());
    }


}
