package com.pixeltrice.springbootimagegalleryapp.service;

import com.pixeltrice.springbootimagegalleryapp.model.Admin;

public interface AdminService {

    boolean hasAccount(Admin admin);

    boolean isCorrect(Admin admin);
}
