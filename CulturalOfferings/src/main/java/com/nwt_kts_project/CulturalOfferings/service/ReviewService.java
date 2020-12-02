package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService implements ServiceInterface<Review> {
	
	@Autowired
	private ReviewRepository reviewRepo;
    @Override
    public List<Review> findAll() {
        return null;
    }
    @Override
    public Review findOne(Long id) {
        return null;
    }

    @Override
    public Review create(Review r) throws Exception {
        return null;
    }

    @Override
    public Review update(Review r, Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
    	Review r = reviewRepo.findById(id).orElse(null);
    	if(r == null) {
    		throw new Exception("Review doesn't exists.");
    	}
    	reviewRepo.delete(r);
    }
}
