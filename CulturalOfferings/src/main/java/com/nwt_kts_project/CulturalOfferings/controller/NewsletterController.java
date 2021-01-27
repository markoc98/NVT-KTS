package com.nwt_kts_project.CulturalOfferings.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.nwt_kts_project.CulturalOfferings.model.*;
import com.nwt_kts_project.CulturalOfferings.service.*;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryDTO;
import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;
import com.nwt_kts_project.CulturalOfferings.utility.NewsletterMapper;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/newsletters", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsletterController {

    @Autowired
    NewsletterRepository newsletterRepo;

	@Autowired
	private EmailSenderService emailSenderService;

    @Autowired
	private PictureService pictureService;

    @Autowired
    private NewsletterService newsletterService;

    @Autowired
	private CulturalOfferingService culturalOfferingService;

    @Autowired
	private UserService userService;

    private NewsletterMapper newsletterMapper;
    
    public NewsletterController() {
    	this.newsletterMapper = new NewsletterMapper();
    }

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/create/{cultoffid}", method = RequestMethod.POST)
    public ResponseEntity<NewsletterDTO> createNewsletter(@RequestBody @Valid NewsletterDTO newsletterDTO,@PathVariable Long cultoffid) throws Exception {

		/*if(file != null)
		{
			Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
					PictureCompression.compressBytes(file.getBytes()), newsletterMapper.toEntity(newsletterDTO));
			pictureService.create(img);
		}*/
    	Newsletter newsletter;
		Newsletter newNews;
    	CulturalOffering co = culturalOfferingService.findOne(cultoffid);
    	try {
			newsletter = newsletterMapper.toEntity(newsletterDTO);
			newsletter.setCulturalOffering(co);
			newNews = newsletterService.create(newsletter);

    	}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		sendMails(co, newNews.getTitle(), newNews.getContent());
    	return new ResponseEntity<>(newsletterMapper.toDto(newNews),HttpStatus.CREATED);
    }

	private void sendMails(CulturalOffering culturalOffering, String title, String content) {
		System.out.println("id coa je " + culturalOffering.getId());
		List<User> users =	userService.findAll();
		for(User user: users)
		{
			if(user.getSubscribedTo()==null){
				for(CulturalOffering co: user.getSubscribedTo()){
					if(co.getId().equals(culturalOffering.getId())){

						SimpleMailMessage mailMessage = new SimpleMailMessage();
						mailMessage.setTo(user.getEmail());
						mailMessage.setSubject(title);
						mailMessage.setFrom("culturalofferings@gmail.com");
						mailMessage.setText(content);
						System.out.println("Emails sent!");
						emailSenderService.sendMail(mailMessage);
					}
				}
			}

		}
//		if(culturalOffering.getSubscribedUsers() != null){
//			Set<User> subscribers = culturalOffering.getSubscribedUsers();
//
//			System.out.println("imamo subscrajbera:" + subscribers.size());
//			for(User subscriber : subscribers) {
//				SimpleMailMessage mailMessage = new SimpleMailMessage();
//				mailMessage.setTo(subscriber.getEmail());
//				mailMessage.setSubject(title);
//				mailMessage.setFrom("culturalofferings@gmail.com");
//				mailMessage.setText(content);
//
//				emailSenderService.sendMail(mailMessage);
//			}
//		}
//		else {
//			System.out.println("jeste null jbfg");
//		}

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

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/culturalOffer/{culturalOfferId}",method = RequestMethod.GET)
	public ResponseEntity<Page<NewsletterDTO>> getAllNewslettersByCulturalOfferId(@PathVariable Long culturalOfferId, Pageable pageable){
		//List<Newsletter> newsletters = newsletterService.findAll();

		Page<Newsletter> page = newsletterService.findAll(pageable);
		ArrayList<Newsletter> newsForCo = new ArrayList<>();
		for(Newsletter news: page)
		{
			if(news.getCulturalOffering().getId().equals(culturalOfferId)){
				newsForCo.add(news);
			}
		}

		List<NewsletterDTO> newsletterDTOS = toNewsletterDTOList(newsForCo);
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
	
	@PreAuthorize("haseRole('ROLE_USER')")
	@RequestMapping(value="/getbycultoff/{cultOfferingId}",method = RequestMethod.GET)
	public ResponseEntity<List<NewsletterDTO>> getNewsletterByCultOffID(@PathVariable Long cultOfferingId) {
		
		List<Newsletter> reviewList = newsletterRepo.findAll();
		List<Newsletter> found = new ArrayList<Newsletter>();
		
		for(Newsletter news : reviewList) {
			if(news.getCulturalOffering().getId() == cultOfferingId) {
				found.add(news);
			}
		}
		
		List<NewsletterDTO> dtoList = toNewsletterDTOList(found);
		
		return new ResponseEntity<>(dtoList,HttpStatus.FOUND);		
		
	}
	    
}
