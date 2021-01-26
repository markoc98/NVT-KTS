package com.nwt_kts_project.CulturalOfferings.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.service.PictureService;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import com.nwt_kts_project.CulturalOfferings.repository.CulturalOfferingRepository;
import com.nwt_kts_project.CulturalOfferings.repository.ReviewRepository;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepository;
import com.nwt_kts_project.CulturalOfferings.service.ReviewService;
import com.nwt_kts_project.CulturalOfferings.service.UserService;
import com.nwt_kts_project.CulturalOfferings.utility.ReviewMapper;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {


    @Autowired
	private PictureService pictureService;
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private CulturalOfferingService cultService;
    
    private ReviewMapper reviewMapper;
    
    @Autowired
    private CulturalOfferingService offerService;
    
    @Autowired
    private UserService userService;
    
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

    // OVDE JE DODATO UBACIVANJE SLIKE
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody @Valid ReviewDTO reviewDTO, @RequestBody MultipartFile file) throws Exception {

    	if(file != null)
		{
			Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
					PictureCompression.compressBytes(file.getBytes()), reviewMapper.toEntity(reviewDTO));
			pictureService.create(img);
		}

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
    public ResponseEntity<Page<ReviewDTO>> getAllReviews(Pageable pageable){
		//List<Review> reviews = reviewService.findAll();
		
		Page<Review> page = reviewService.findAll(pageable);
    	List<ReviewDTO> reviewDTOS = toReviewDTOList(page.toList());
        Page<ReviewDTO> pageReviewDTOS = new PageImpl<>(reviewDTOS,page.getPageable(),page.getTotalElements());


		return new ResponseEntity<>(pageReviewDTOS, HttpStatus.OK);
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
    
	@PreAuthorize("haseRole('ROLE_USER')")
	@RequestMapping(value="/getbycultoff/{cultOfferingId}",method = RequestMethod.GET)
	public ResponseEntity<List<ReviewDTO>> getReviewByCultOffID(@PathVariable Long cultOfferingId) {
		
		List<Review> reviewList = reviewService.findAll();
		List<Review> found = new ArrayList<Review>();
		
		for(Review r : reviewList) {
			if(r.getCulturalOffering().getId() == cultOfferingId) {
				found.add(r);
			}
		}
		
		List<ReviewDTO> dtoList = toReviewDTOList(found);
		
		return new ResponseEntity<>(dtoList,HttpStatus.FOUND);		
		
	}
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/setRating/{userId}/{cultOffId}",method = RequestMethod.POST) 
	public ResponseEntity<?> setRatingForOffer(@RequestBody ReviewDTO reviewDTO,@PathVariable Long userId,@PathVariable Long cultOffId) throws Exception{
		
		User user = userService.findOne(userId);
		CulturalOffering cultOff = cultService.findOne(cultOffId);
		
		Review review = reviewMapper.toEntity(reviewDTO);
		List<Review> reviews = reviewService.findAll();
		
		review.setCulturalOffering(cultOff);
		review.setUser(user);
		
		double oldRating = cultOff.getRating();
		double newRating = 0;
		
		int count = 0;
		
		for(Review r : reviews) {
			if(r.getCulturalOffering().getId() == cultOffId ) {
				count++;
			}
		}
		
		newRating = (oldRating*count + review.getRating())/(count+1);
		
		
		cultOff.setRating(newRating);
		cultService.update(cultOff, cultOffId);
		reviewService.create(review);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
		
	}

}
