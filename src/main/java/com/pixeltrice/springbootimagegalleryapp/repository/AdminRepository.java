package com.pixeltrice.springbootimagegalleryapp.repository;

import com.pixeltrice.springbootimagegalleryapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
