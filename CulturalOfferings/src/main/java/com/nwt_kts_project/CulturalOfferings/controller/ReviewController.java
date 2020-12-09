package com.nwt_kts_project.CulturalOfferings.controller;

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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
    	
    	try {
    		reviewService.delete(id);
    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<Review>> createReview(@RequestBody @Valid ReviewDTO reviewDTO){
    	
    	try {
    		User u = new User(reviewDTO.getUser());
    		
    		Review r = new Review(reviewDTO.getId(), reviewDTO.getComment(), reviewDTO.getRating(), reviewDTO.getCulturalOffering(), reviewDTO.getPictures(), u);
    		

    		
    		reviewService.create(r);
    		reviewRepo.save(r);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviews(){
    	
    	try {
    		
    		List<Review> reviews = reviewService.findAll();
    		System.out.println(reviews.size());
    		return new ResponseEntity<>(reviews, HttpStatus.OK);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<Review> getReviews(@PathVariable Long reviewId){
    	
    	try {
    		
    		Review review = reviewService.findOne(reviewId);
    		
    		return new ResponseEntity<>(review, HttpStatus.OK);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
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

}
