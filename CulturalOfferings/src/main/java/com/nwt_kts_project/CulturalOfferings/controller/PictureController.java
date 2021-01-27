package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.repository.PictureRepository;
import com.nwt_kts_project.CulturalOfferings.service.PictureService;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/picture", produces = MediaType.APPLICATION_JSON_VALUE)
public class PictureController {

    @Autowired
    private PictureRepository pictureRepo;

    @PostMapping(path = { "/newsletter/{id}" })
    public Picture getNewsletterPicture(@PathVariable Long id) throws IOException {

        final Picture retrievedImage = pictureRepo.findByNewsletterId(id).orElse(null);
                Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                PictureCompression.decompressBytes(retrievedImage.getPicByte()));
        return img;
    }
    @GetMapping(path = { "/review/{id}" })
    public Picture getReviewPicture(@PathVariable Long id) throws IOException {

        final Picture retrievedImage = pictureRepo.findByReviewId(id).orElse(null);
        Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                PictureCompression.decompressBytes(retrievedImage.getPicByte()));
        return img;
    }
    @GetMapping(path = { "/culturalOffering/{id}" })
    public ResponseEntity<Picture> getCulturalOfferingPicture(@PathVariable Long id) throws IOException {
        try{
            final Picture retrievedImage = pictureRepo.findByCulturalOfferingId(id).orElse(null);
            Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                    PictureCompression.decompressBytes(retrievedImage.getPicByte()));
            return new ResponseEntity<>(img,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @PostMapping(path = { "/upload/culturalOffering/{id}" })
    public ResponseEntity.BodyBuilder uploadCulturalOfferingPicture(@RequestParam("imageFile") MultipartFile file) throws IOException {
        try{
            Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                    PictureCompression.compressBytes(file.getBytes()));
            pictureRepo.save(img);
            //return ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
