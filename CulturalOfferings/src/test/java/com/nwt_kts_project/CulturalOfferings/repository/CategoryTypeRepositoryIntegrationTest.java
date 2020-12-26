package com.nwt_kts_project.CulturalOfferings.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.nwt_kts_project.CulturalOfferings.constants.CategoryTypeConstants.*;
import static com.nwt_kts_project.CulturalOfferings.constants.CategoryConstants.DB_CATEGORY_ID;


import com.nwt_kts_project.CulturalOfferings.model.CategoryType;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class CategoryTypeRepositoryIntegrationTest {
	
	@Autowired
	private CategoryTypeRepository categoryTypeRepository;
	
	@Test
	public void testFindByName() {
		CategoryType found = categoryTypeRepository.findByName(DB_CATEGORY_TYPE);
		
		assertEquals(DB_CATEGORY_TYPE, found.getName());
	}
	
	@Test
    public void testFindByNameAndIdNot() {
        CategoryType found = categoryTypeRepository.findByNameAndIdNot(DB_CATEGORY_TYPE,DB_CATEGORY_TYPE_ID);
        assertNull(found);
    }

    @Test
    public void testFindByCategoryId() {
        List<CategoryType> found = categoryTypeRepository.findByCategoryId(DB_CATEGORY_ID);
        assertEquals(DB_CATEGORY_SIZE, found.size());
    }

}
