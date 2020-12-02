package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NewsletterService implements ServiceInterface<Newsletter> {
	
	@Autowired
	NewsletterRepository newsletterRepo;
	
    @Override
    public List<Newsletter> findAll() {
        return newsletterRepo.findAll();
    }
    @Override
    public Newsletter findOne(Long id) {
        return newsletterRepo.findById(id).orElse(null);
    }

    @Override
    public Newsletter create(Newsletter n) throws Exception {
        
    	if(newsletterRepo.findById(n.getId()) != null) {
    		throw new Exception("Newsletter already exists.");
    	}
    	return newsletterRepo.save(n);
    }

    @Override
    public Newsletter update(Newsletter n, Long id) throws Exception {
    	Newsletter n2 = newsletterRepo.findById(id).orElse(null);
    	if(n2 == null) {
    		throw new Exception("Newsletter with given ID doesn't exists.");
    	}
    	n2.setContent(n.getContent());
    	n2.setTitle(n.getTitle());
    	n2.setPictures(n2.getPictures());
    	n2.setDate(n.getDate());
    	
    	return newsletterRepo.save(n2);
    }

    @Override
    public void delete(Long id) throws Exception {
    	Newsletter n2 = newsletterRepo.findById(id).orElse(null);
    	if(n2 == null) {
    		throw new Exception("Newsletter with given ID doesn't exists.");
    	}
    	newsletterRepo.delete(n2);
    	
    }
}
