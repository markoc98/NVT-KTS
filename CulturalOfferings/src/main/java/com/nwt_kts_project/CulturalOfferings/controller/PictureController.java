package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.repository.PictureRepository;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.service.NewsletterService;
import com.nwt_kts_project.CulturalOfferings.service.PictureService;
import com.nwt_kts_project.CulturalOfferings.service.ReviewService;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/picture", produces = MediaType.APPLICATION_JSON_VALUE)
public class PictureController {

    @Autowired
    private PictureRepository pictureRepo;

    @Autowired
    private NewsletterService newsletterService;

    @Autowired
    private CulturalOfferingService culturalOfferingService;

    @Autowired
    private ReviewService reviewService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = { "/newsletter/{id}" })
    public Picture getNewsletterPicture(@PathVariable Long id) throws IOException {

        final Picture retrievedImage = pictureRepo.findByNewsletterId(id).orElse(null);
                Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                PictureCompression.decompressBytes(retrievedImage.getPicByte()));
        return img;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = { "/review/{id}" })
    public ResponseEntity<Picture> getReviewPicture(@PathVariable Long id) throws IOException {

        final Picture retrievedImage = pictureRepo.findByReviewId(id).orElse(null);
        if(retrievedImage != null)
        {
            Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                    PictureCompression.decompressBytes(retrievedImage.getPicByte()));
            return new ResponseEntity<>(img, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = { "/culturalOffering/{id}" })
    public ResponseEntity<Picture> getCulturalOfferingPicture(@PathVariable Long id) throws IOException {
        final Picture retrievedImage = pictureRepo.findByCulturalOfferingId(id).orElse(null);
        if(retrievedImage != null)
        {
            Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                    PictureCompression.decompressBytes(retrievedImage.getPicByte()));
            return new ResponseEntity<>(img,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = { "/upload/culturalOffering/{id}" })
    public ResponseEntity<Void> uploadCulturalOfferingPicture(@RequestParam("imageFile") MultipartFile file, @PathVariable Long id) throws IOException {
        try{
            Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                    PictureCompression.compressBytes(file.getBytes()));
            CulturalOffering co = culturalOfferingService.findOne(id);
            img.setCulturalOffering(co);
            pictureRepo.save(img);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = { "/upload/newsletter/{id}" })
    public ResponseEntity<Void> uploadCReviewPicture(@RequestParam("imageFile") MultipartFile file, @PathVariable Long id) throws IOException {
        try{
            Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                    PictureCompression.compressBytes(file.getBytes()));
            Newsletter news = newsletterService.findOne(id);
            img.setNewsletter(news);
            pictureRepo.save(img);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = { "/upload/review/{id}" })
    public ResponseEntity<Void> uploadNewsletterPicture(@RequestParam("imageFile") MultipartFile file, @PathVariable Long id) throws IOException {
        try{
            Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                    PictureCompression.compressBytes(file.getBytes()));

            Review review = reviewService.findOne(id);
            img.setReview(review);
            pictureRepo.save(img);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
