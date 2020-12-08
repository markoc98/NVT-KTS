package com.nwt_kts_project.CulturalOfferings.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Newsletter> createNewsletter(@RequestBody @Valid NewsletterDTO newsletterDTO){
    	//fix
    	//umesto liste objekat
    	try {
    		Newsletter nl = new Newsletter(newsletterDTO.getId(),newsletterDTO.getTitle(),newsletterDTO.getContent(),newsletterDTO.getDate(),newsletterDTO.getPictures(),newsletterDTO.getCulturalOffering());
    		newsletterService.create(nl);
    		//newsletterRepo.save(nl);
    		System.out.println("EVO MI ID: " + nl.getId());
    		System.out.println(nl.toString());
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    	
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNewsletter(@PathVariable Long id){
    	
    	try {
    		newsletterService.delete(id);
    		
    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{newsletterId}", method = RequestMethod.GET)
    public ResponseEntity<Newsletter> getNewsletters(@PathVariable Long newsletterId){
    	
    	try {
    		Newsletter nl = newsletterService.findOne(newsletterId);
    		return new ResponseEntity<>(nl,HttpStatus.OK);
    	}catch (Exception e) {
    		//System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Newsletter>> getNewsletters(){
    	
    	try {
    		
    		List<Newsletter> newsletters = newsletterService.findAll();
    		System.out.println(newsletters.size());
    		return new ResponseEntity<>(newsletters, HttpStatus.OK);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsletterDTO> updateNewsletter(@RequestBody @Valid NewsletterDTO newsletterDTO, @PathVariable Long id){
    	Newsletter nl;
        try {
        	
            nl = newsletterService.update(newsletterMapper.toEntity(newsletterDTO), id);
            System.out.println(nl.toString());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newsletterMapper.toDto(nl), HttpStatus.OK);
    }
    
}
