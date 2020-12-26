package com.nwt_kts_project.CulturalOfferings.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.ReviewRepository;
import com.nwt_kts_project.CulturalOfferings.service.ReviewService;
import com.nwt_kts_project.CulturalOfferings.utility.ReviewMapper;

@RestController
@RequestMapping(value = "/api/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepo;

    @Autowired
    private ReviewService reviewService;
    
    private ReviewMapper reviewMapper;
    
    public ReviewController() {
    	this.reviewMapper = new ReviewMapper();
    }

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
    	
    	try {
    		reviewService.delete(id);
    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody @Valid ReviewDTO reviewDTO){
    	Review r;
    	try {	
    		r = reviewService.create(reviewMapper.toEntity(reviewDTO));

    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<> (reviewMapper.toDto(r), HttpStatus.CREATED);
    }

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDTO>> getAllReviews(){
		List<Review> reviews = reviewService.findAll();

		return new ResponseEntity<>(toReviewDTOList(reviews), HttpStatus.OK);
    }
	
	private List<ReviewDTO> toReviewDTOList(List<Review> reviewList){
		List<ReviewDTO> reviewsDTO = new ArrayList<>(); 
		for(Review r : reviewList)
		{
			reviewsDTO.add(reviewMapper.toDto(r));
		}
		return reviewsDTO;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<ReviewDTO> getReviews(@PathVariable Long reviewId){
  
    	Review review = reviewService.findOne(reviewId);
    		
    	if(review == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(reviewMapper.toDto(review),HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO, @PathVariable Long id){
        Review review;
        try {
        	
            review = reviewService.update(reviewMapper.toEntity(reviewDTO), id);
            System.out.println(review.toString());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reviewMapper.toDto(review), HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/overall-rating", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> updateReview(){
        Review review;
        try {
        	double a = 4.4;
        	return new ResponseEntity<Double>(a, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
