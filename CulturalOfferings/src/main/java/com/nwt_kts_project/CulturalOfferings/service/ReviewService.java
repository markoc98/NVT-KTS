package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ServiceInterface<Review> {
	
	@Autowired
	private ReviewRepository reviewRepo;
    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }
    
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepo.findAll(pageable);
    }
    
    @Override
    public Review findOne(Long id) {
        return reviewRepo.findById(id).orElse(null);
    }

    @Override
    public Review create(Review r) throws Exception {
       if(r.getId() != null) {
    	   throw new Exception("Review already exists.");
       }
       return reviewRepo.save(r);
    }

    @Override
    public Review update(Review r, Long id) throws Exception {
        Review r2 = reviewRepo.findById(id).orElse(null);
        if(r2 == null) {
        	throw new Exception("Review doesn't exists.");
        }
        r2.setComment(r.getComment());
        r2.setRating(r.getRating());
        
        return reviewRepo.saveAndFlush(r2);
    }

    @Override
    public void delete(Long id) throws Exception {
    	System.out.println(id);
    	Review r = reviewRepo.findById(id).orElse(null);
    	if(r == null) {
    		throw new Exception("Review doesn't exists.");
    	}
    	reviewRepo.delete(r);
    }
}
