package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.NewsletterRepository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService implements ServiceInterface<Newsletter> {
	
	@Autowired
	private NewsletterRepository newsRepo;

    @Autowired
    private EmailSenderService emailSenderService;

	@Autowired
    private CulturalOfferingService culturalOfferingService;

    @Override
    public List<Newsletter> findAll() {
        return newsRepo.findAll();
    }
    
    public Page<Newsletter> findAll(Pageable pageable) {
        return newsRepo.findAll(pageable);
    }
    
    @Override
    public Newsletter findOne(Long id) {
    	 return newsRepo.findById(id).orElse(null);
    }

    @Override
    public Newsletter create(Newsletter n) throws Exception {
//        if(n.getId() != null) {
//        	throw new Exception("Newsletter already exists.");
//        }
<<<<<<< HEAD
//       CulturalOffering culturalOffering = culturalOfferingService.findOne(n.getCulturalOffering().getId());
//
//       sendMails(culturalOffering, n);
=======
       // CulturalOffering culturalOffering = culturalOfferingService.findOne(n.getCulturalOffering().getId());

       // sendMails(culturalOffering, n);
>>>>>>> 923d46dc3426493e4ab9c92c81772e14adb2588c

        return newsRepo.save(n);
    }

    private void sendMails(CulturalOffering culturalOffering, Newsletter n) {
        Set<User> subscribers = culturalOffering.getSubscribedUsers();

        for(User subscriber : subscribers) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(subscriber.getEmail());
            mailMessage.setSubject(n.getTitle());
            mailMessage.setFrom("culturalofferings@gmail.com");
            mailMessage.setText(n.getContent());

            emailSenderService.sendMail(mailMessage);
        }
    }

    @Override
    public Newsletter update(Newsletter n, Long id) throws Exception {
    	Newsletter n2 = newsRepo.findById(id).orElse(null);
    	if(n2 == null) {
    		throw new Exception("Newsletter doesn't exists.");
    	}
    	n2.setTitle(n.getTitle());
    	n2.setContent(n.getContent());
    	
    	return newsRepo.save(n2);
    	 
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
