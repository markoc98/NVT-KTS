package com.nwt_kts_project.CulturalOfferings.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.nwt_kts_project.CulturalOfferings.constants.NewsletterConstants.*;

import com.nwt_kts_project.CulturalOfferings.model.Newsletter;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class NewsletterRepositoryIntegrationTest {
	
	@Autowired
	private NewsletterRepository newsRepo;
	
	@Test
	public void testFindByTitle() {
		Newsletter newsFound = newsRepo.findByTitle(NEW_NEWS_TITLE);
		
		assertEquals(NEW_NEWS_TITLE, newsFound.getTitle());
	}
}
