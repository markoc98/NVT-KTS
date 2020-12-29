package com.nwt_kts_project.CulturalOfferings.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import static com.nwt_kts_project.CulturalOfferings.constants.ReviewConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class ReviewRepositoryIntegrationTest {
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Test
	public void testFindByComment() {
		Review review = reviewRepo.findByComment(DB_NEW_COMMENT);
		
		assertEquals(DB_NEW_COMMENT,review.getComment());
	}

}
