package com.nwt_kts_project.CulturalOfferings.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nwt_kts_project.CulturalOfferings.model.Review;

import static com.nwt_kts_project.CulturalOfferings.constants.ReviewConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ReviewServiceIntegrationTest {
	
	@Autowired
	private ReviewService reviewService;
	
	@BeforeAll
	@Test
    public void beforeFunction() throws Exception{
		Review review = new Review(DB_NEW_COMMENT,DB_NEW_RATING);
		Review r2 = reviewService.create(review);
		DB_DEL_REVIEW_ID = r2.getId();
    }
	
	@Test
	public void testFindAllReviews() {
		List<Review> reviewList = reviewService.findAll();
		assertEquals(DB_REVIEWS_COUNT, reviewList.size()-1);
	}
	
	@Test
	public void testFindByIdReview() {
		Review review = reviewService.findOne(DB_REVIEW_ID);
		assertEquals(DB_REVIEW_ID, review.getId());
	}
	@Test
	public void testCreateReview() throws Exception {
		Review review = new Review(DB_NEW_COMMENT,DB_NEW_RATING);
		Review createdReview = reviewService.create(review);

		assertEquals(DB_NEW_COMMENT, createdReview.getComment());
	}
	
	@Test
    public void testDeleteReview() throws Exception {
		reviewService.delete(DB_DEL_REVIEW_ID);

        Review savedReview = new Review(DB_DEL_REVIEW_COMMENT,DB_DEL_REVIEW_RATING);
        savedReview.setId(DB_DEL_REVIEW_ID);
    }
	
	
    @Test
    public void testUpdateReview() throws Exception {
    	Review review = new Review(NEW_REVIEW_COMMENT2,NEW_REVIEW_RATING2);
		Review createdReview = reviewService.update(review,DB_REVIEW_ID);

        assertEquals(NEW_REVIEW_COMMENT2, createdReview.getComment());

    }
}
