package com.pixeltrice.springbootimagegalleryapp.service;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;

import java.util.List;
import java.util.Optional;

public interface MediaGalleryService {

    void saveImage(MediaGallery mediaGallery);

    List<MediaGallery> getAllActiveMedia();

    Optional<MediaGallery> getMediaById(Long id);

    void deleteMedia(long id);
}
