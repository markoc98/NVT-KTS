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
        return newsRepo.findAll();
    }
    @Override
    public Newsletter findOne(Long id) {
    	 return newsRepo.findById(id).orElse(null);
    }

    @Override
    public Newsletter create(Newsletter n) throws Exception {
        if(newsRepo.findByTitle(n.getTitle()) != null) {
        	throw new Exception("Newsletter already exists.");
        }
    	 return newsRepo.save(n);
    }

    @Override
    public Newsletter update(Newsletter n, Long id) throws Exception {
    	Newsletter n2 = newsRepo.findById(id).orElse(null);
    	if(n2 == null) {
    		throw new Exception("Newsletter doesn't exists.");
    	}
    	n2.setTitle(n.getTitle());
    	n2.setContent(n.getContent());
    	
    	return newsRepo.saveAndFlush(n2);
    	 
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
