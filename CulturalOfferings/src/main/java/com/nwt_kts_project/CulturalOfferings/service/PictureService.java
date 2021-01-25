package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Picture;
import com.nwt_kts_project.CulturalOfferings.model.Review;
import com.nwt_kts_project.CulturalOfferings.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PictureService implements ServiceInterface<Picture>{
    @Autowired
    private PictureRepository pictureRepo ;

    @Override
    public List<Picture> findAll() {
        return pictureRepo.findAll();
    }

    @Override
    public Picture findOne(Long id) {
        return pictureRepo.findById(id).orElse(null);
    }

    @Override
    public Picture create(Picture entity) throws Exception {
        return pictureRepo.save(entity);
    }

    @Override
    public Picture update(Picture entity, Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        Picture p = pictureRepo.findById(id).orElse(null);
        pictureRepo.delete(p);
    }

}

