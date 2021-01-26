package com.nwt_kts_project.CulturalOfferings.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.nwt_kts_project.CulturalOfferings.model.Picture;
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

import com.nwt_kts_project.CulturalOfferings.dto.CategoryDTO;
import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.model.Category;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;
import com.nwt_kts_project.CulturalOfferings.service.NewsletterService;
import com.nwt_kts_project.CulturalOfferings.utility.NewsletterMapper;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/newsletters", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsletterController {

    @Autowired
    NewsletterRepository newsletterRepo;

    @Autowired
	private PictureService pictureService;

    @Autowired
    private NewsletterService newsletterService;
    
    private NewsletterMapper newsletterMapper;
    
    public NewsletterController() {
    	this.newsletterMapper = new NewsletterMapper();
    }
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NewsletterDTO> createNewsletter(@RequestBody @Valid NewsletterDTO newsletterDTO, @RequestBody MultipartFile file) throws Exception {

		if(file != null)
		{
			Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
					PictureCompression.compressBytes(file.getBytes()), newsletterMapper.toEntity(newsletterDTO));
			pictureService.create(img);
		}
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
    public ResponseEntity<Page<NewsletterDTO>> getAllNewsletters(Pageable pageable){
		//List<Newsletter> newsletters = newsletterService.findAll();
		
		Page<Newsletter> page = newsletterService.findAll(pageable);
    	List<NewsletterDTO> newsletterDTOS = toNewsletterDTOList(page.toList());
        Page<NewsletterDTO> pageNewsletterDTOS = new PageImpl<>(newsletterDTOS,page.getPageable(),page.getTotalElements());

    	return new ResponseEntity<>(pageNewsletterDTOS,HttpStatus.OK);
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
