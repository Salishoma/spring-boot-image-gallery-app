package com.pixeltrice.springbootimagegalleryapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "media_gallery")
public class MediaGallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "price",nullable = false, precision = 10, scale = 2)
    private double price;
	
//	@Lob
    @Column(name = "media", length = Integer.MAX_VALUE, nullable = true)
    private String media;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

	@ManyToOne
	@JoinColumn(name = ("user"))
	private User user;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", media="
				+ media + ", createDate=" + createDate + "]";
	}
   
}


