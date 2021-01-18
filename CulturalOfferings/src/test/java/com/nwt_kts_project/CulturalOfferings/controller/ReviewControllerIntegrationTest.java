package com.nwt_kts_project.CulturalOfferings.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static com.nwt_kts_project.CulturalOfferings.constants.ReviewConstants.*;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;
import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.service.ReviewService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ReviewControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ReviewService reviewService;
	
	private String accessToken;
	
	public void login(String username, String password) {

		ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
				new UserLoginDTO(username, password), UserTokenStateDTO.class);
	    accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

	    }
	
	@Test
	public void testGetReview() {
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", accessToken);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

		ResponseEntity<ReviewDTO> responseEntity = 
				restTemplate.exchange("/api/reviews/1", HttpMethod.GET,httpEntity,ReviewDTO.class);
		
		ReviewDTO review = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(review);
		assertEquals(review.getComment(),DB_NEW_COMMENT);
		
	}
	
	@Test
	public void testGetAllReviews() {
		
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", accessToken);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		
		ResponseEntity<ReviewDTO[]> responseEntity = 
				restTemplate.exchange("/api/reviews/", HttpMethod.GET,httpEntity,ReviewDTO[].class);
		
		ReviewDTO[] reviews = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(DB_REVIEWS_COUNT+1,reviews.length);
		
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testCreateReview() {
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", accessToken);
	    
	    HttpEntity<Object> httpEntity = new HttpEntity<Object>(new ReviewDTO(NEW_COMMENT, NEW_RATING), headers);

        ResponseEntity<ReviewDTO> responseEntity =
                restTemplate.exchange("/api/reviews",
                        HttpMethod.POST, httpEntity,
                        ReviewDTO.class);


        ReviewDTO review = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(review);
        assertEquals(NEW_COMMENT,review.getComment());
	        
	}
	
	@Test
    @Transactional
    @Rollback(true)
    public void testUpdateReview() throws Exception {
		
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new ReviewDTO(DB_NEW_COMMENT, DB_RATING), headers);
        ResponseEntity<ReviewDTO> responseEntity =
                restTemplate.exchange("/api/reviews/1",
                        HttpMethod.PUT, httpEntity,
                        ReviewDTO.class);


        ReviewDTO review = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(review);

        assertEquals(DB_REVIEW_ID, review.getId());
        assertEquals(DB_NEW_COMMENT, review.getComment());

        Review review2 = reviewService.findOne(DB_REVIEW_ID);

        assertEquals(DB_REVIEW_ID, review2.getId());
        assertEquals(DB_NEW_COMMENT, review2.getComment());

        review2.setComment(DB_REVIEW_COMMENT);
        reviewService.update(review2, review2.getId());
    }
	
	 @Test
	 public void testDeleteReview() throws Exception {
		 
		 login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
      
		 Review review = reviewService.create(new Review(NEW_REVIEW_COMMENT2,NEW_REVIEW_RATING2));
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", accessToken);
		 
		 HttpEntity<Object> httpEntity = new HttpEntity<Object>(null,headers);
		 List<Review> reviews = reviewService.findAll();
		 int size = reviewService.findAll().size();

		 String url = "/api/reviews/" + review.getId();
		 ResponseEntity<Void> responseEntity =
				 restTemplate.exchange(url,
						 HttpMethod.DELETE, httpEntity,  Void.class);
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 assertEquals(size - 1, reviewService.findAll().size());
	    }
}
