package com.pixeltrice.springbootimagegalleryapp.service;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;
import com.pixeltrice.springbootimagegalleryapp.model.User;

import java.util.List;

public interface UserService {
    boolean hasAccount(User user);

    User createUser(User user);

    User userInDB(User user);

    User findUser(Long id);

    List<MediaGallery> getUserMedia(User user);

//    void saveMedia(Long id, MediaGallery mediaGallery);
}
