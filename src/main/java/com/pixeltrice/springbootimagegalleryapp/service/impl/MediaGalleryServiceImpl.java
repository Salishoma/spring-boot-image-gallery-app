package com.pixeltrice.springbootimagegalleryapp.service.impl;

import java.util.List;
import java.util.Optional;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;
import com.pixeltrice.springbootimagegalleryapp.service.MediaGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixeltrice.springbootimagegalleryapp.repository.MediaGalleryRepository;



@Service
public class MediaGalleryServiceImpl implements MediaGalleryService {

	private MediaGalleryRepository mediaGalleryRepository;

	@Autowired
	public MediaGalleryServiceImpl(MediaGalleryRepository mediaGalleryRepository) {
		this.mediaGalleryRepository = mediaGalleryRepository;
	}

	public void saveImage(MediaGallery mediaGallery) {
		mediaGalleryRepository.save(mediaGallery);
	}

	@Override
	public List<MediaGallery> getAllActiveMedia() {
		return mediaGalleryRepository.findAll();
	}

	@Override
	public Optional<MediaGallery> getMediaById(Long id) {
		return mediaGalleryRepository.findById(id);
	}

	@Override
	public void deleteMedia(long id) {
		mediaGalleryRepository.deleteById(id);
	}
}

