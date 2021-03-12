package com.pixeltrice.springbootimagegalleryapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;

    @OneToMany
    private List<MediaGallery> mediaGalleries;

    @Enumerated(EnumType.STRING)
    @Column(name="auth_provider")
    private AuthenticationProvider authenticationProvider;
}
