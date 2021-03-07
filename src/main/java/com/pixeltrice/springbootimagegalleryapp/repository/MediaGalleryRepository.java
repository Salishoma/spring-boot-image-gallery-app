package com.pixeltrice.springbootimagegalleryapp.repository;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediaGalleryRepository extends JpaRepository<MediaGallery, Long>{

}

