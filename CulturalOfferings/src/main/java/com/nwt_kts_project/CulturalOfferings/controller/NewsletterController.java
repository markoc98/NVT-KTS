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
import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;
import com.nwt_kts_project.CulturalOfferings.service.NewsletterService;
import com.nwt_kts_project.CulturalOfferings.utility.NewsletterMapper;

@RestController
@RequestMapping(value = "/api/newsletters", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsletterController {

    @Autowired
    NewsletterRepository newsletterRepo;

    @Autowired
    private NewsletterService newsletterService;
    
    private NewsletterMapper newsletterMapper;
    
    public NewsletterController() {
    	this.newsletterMapper = new NewsletterMapper();
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NewsletterDTO> createNewsletter(@RequestBody @Valid NewsletterDTO newsletterDTO){

    	Newsletter newsL;
    	try {
    		newsL = newsletterService.create(newsletterMapper.toEntity(newsletterDTO));
    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<>(newsletterMapper.toDto(newsL),HttpStatus.CREATED);
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNewsletter(@PathVariable Long id){
    	
    	try {
    		newsletterService.delete(id);
    		
    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{newsletterId}", method = RequestMethod.GET)
    public ResponseEntity<NewsletterDTO> getNewsletters(@PathVariable Long newsletterId){
		
		Newsletter n = newsletterService.findOne(newsletterId);
		if(n == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(newsletterMapper.toDto(n),HttpStatus.OK);
  
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NewsletterDTO>> getAllNewsletters(){
		List<Newsletter> newsletters = newsletterService.findAll();
    	return new ResponseEntity<>(toNewsletterDTOList(newsletters),HttpStatus.OK);
    }
	
	private List<NewsletterDTO> toNewsletterDTOList(List<Newsletter> newsList){
		List<NewsletterDTO> newsDTO = new ArrayList<>(); 
		for(Newsletter n : newsList)
		{
			newsDTO.add(newsletterMapper.toDto(n));
		}
		return newsDTO;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsletterDTO> updateNewsletter(@RequestBody NewsletterDTO newsletterDTO, @PathVariable Long id){
    	Newsletter nl;
    	
        try {
            nl = newsletterService.update(newsletterMapper.toEntity(newsletterDTO), id);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newsletterMapper.toDto(nl), HttpStatus.OK);
    }
    
}
