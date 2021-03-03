package com.pixeltrice.springbootimagegalleryapp.service;

import com.pixeltrice.springbootimagegalleryapp.model.ImageGallery;

import java.util.List;
import java.util.Optional;

public interface ImageGalleryService {


    void saveImage(ImageGallery imageGallery);

    List<ImageGallery> getAllActiveImages();

    Optional<ImageGallery> getImageById(Long id);

    void deleteImage(long id);
}
