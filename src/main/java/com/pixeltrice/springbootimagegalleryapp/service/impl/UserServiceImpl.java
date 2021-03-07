package com.pixeltrice.springbootimagegalleryapp.service.impl;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;
import com.pixeltrice.springbootimagegalleryapp.model.User;
import com.pixeltrice.springbootimagegalleryapp.repository.UserRepository;
import com.pixeltrice.springbootimagegalleryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean hasAccount(User user) {
        User getUser = userRepository.findByEmail(user.getEmail());
        return !(getUser == null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User userInDB(User user) {
        User UserInDB = userRepository.findByEmail(user.getEmail());
        return UserInDB;
    }

    @Override
    public User findUser(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<MediaGallery> getUserMedia(User user) {
        return user.getMediaGalleries();
    }

//    @Override
//    public void saveMedia(Long id, MediaGallery mediaGallery) {
//        User user = userRepository.findById(id).get();
//        List <MediaGallery> userMedia = user.getMediaGalleries();
//        userMedia.add(mediaGallery);
//        user.setMediaGalleries(userMedia);
//        userRepository.save(user);
//    }
}
