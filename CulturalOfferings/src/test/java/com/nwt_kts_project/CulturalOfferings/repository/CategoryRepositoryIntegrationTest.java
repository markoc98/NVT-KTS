package com.nwt_kts_project.CulturalOfferings.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nwt_kts_project.CulturalOfferings.model.Category;
import static com.nwt_kts_project.CulturalOfferings.constants.CategoryConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class CategoryRepositoryIntegrationTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindByName() {
		Category found = categoryRepository.findByName(DB_CATEGORY);
		
		assertEquals(DB_CATEGORY, found.getName());
	}
	
}
