package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.repository.PictureRepository;
import com.nwt_kts_project.CulturalOfferings.service.PictureService;
import com.nwt_kts_project.CulturalOfferings.utility.PictureCompression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/picture", produces = MediaType.APPLICATION_JSON_VALUE)
public class PictureController {

    @Autowired
    private PictureRepository pictureRepo;

    @GetMapping(path = { "/newsletter/{id}" })
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
    public Picture getCulturalOfferingPicture(@PathVariable Long id) throws IOException {

        final Picture retrievedImage = pictureRepo.findByCulturalOfferingId(id).orElse(null);
        Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                PictureCompression.decompressBytes(retrievedImage.getPicByte()));
        return img;
    }
}
