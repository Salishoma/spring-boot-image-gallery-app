package com.pixeltrice.springbootimagegalleryapp.service.impl;

import java.util.List;
import java.util.Optional;

import com.pixeltrice.springbootimagegalleryapp.service.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixeltrice.springbootimagegalleryapp.model.ImageGallery;
import com.pixeltrice.springbootimagegalleryapp.repository.ImageGalleryRepository;



@Service
public class ImageGalleryServiceImpl implements ImageGalleryService {

	@Autowired
	private ImageGalleryRepository imageGalleryRepository;
	
	public void saveImage(ImageGallery imageGallery) {
		imageGalleryRepository.save(imageGallery);	
	}

	public List<ImageGallery> getAllActiveImages() {
		return imageGalleryRepository.findAll();
	}

	public Optional<ImageGallery> getImageById(Long id) {
		return imageGalleryRepository.findById(id);
	}

	@Override
	public void deleteImage(long id) {
		imageGalleryRepository.deleteById(id);
	}
}

