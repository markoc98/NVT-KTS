package com.nwt_kts_project.CulturalOfferings.controller;

import org.springframework.web.bind.annotation.RestController;
import com.nwt_kts_project.CulturalOfferings.repository.ReviewRepository;
import com.nwt_kts_project.CulturalOfferings.service.ReviewService;

@RestController
public class NewsletterController {

    @Autowired
    ReviewRepository reviewRepo;

    @Autowired
    private ReviewService reviewService;
}
