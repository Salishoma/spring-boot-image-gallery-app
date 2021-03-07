package com.pixeltrice.springbootimagegalleryapp.service.impl;

import com.pixeltrice.springbootimagegalleryapp.model.Admin;
import com.pixeltrice.springbootimagegalleryapp.repository.AdminRepository;
import com.pixeltrice.springbootimagegalleryapp.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean hasAccount(Admin admin) {
        Admin getAdmin = adminRepository.findByEmail(admin.getEmail());
        if(getAdmin == null) return false;
        System.out.println("=============" +  getAdmin);
        return true;
    }

    @Override
    public boolean isCorrect(Admin admin) {
        Admin request = adminRepository.findByEmail(admin.getEmail());
        System.out.println("password is " + request.getPassword());
        System.out.println("Admin password is " + admin.getPassword());

        return request.getPassword().equals(admin.getPassword());
    }
}
