package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService implements ServiceInterface<Newsletter> {
	
	@Autowired
	private NewsletterRepository newsRepo;
    @Override
    public List<Newsletter> findAll() {
        return null;
    }
    @Override
    public Newsletter findOne(Long id) {
    	 return null;
    }

    @Override
    public Newsletter create(Newsletter n) throws Exception {
        
    	 return null;
    }

    @Override
    public Newsletter update(Newsletter n, Long id) throws Exception {
    	 return null;
    }

    @Override
    public void delete(Long id) throws Exception {
    	Newsletter n = newsRepo.findById(id).orElse(null);
    	if(n == null) {
    		throw new Exception("Newsletter doesn't exists.");
    	}
    	newsRepo.delete(n);
    	
    }
}
