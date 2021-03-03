package com.pixeltrice.springbootimagegalleryapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pixeltrice.springbootimagegalleryapp.service.ImageGalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pixeltrice.springbootimagegalleryapp.model.ImageGallery;


@Controller
public class ImageGalleryController {
	@Autowired
	private ImageGalleryService imageGalleryService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path ={ "/", "home"})
	public String displayProductForm(Model model){

		model.addAttribute("imageGallery", new ImageGallery());
		return "index";
	}

	@PostMapping("/image/saveImageDetails")
	public String createProduct(@RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("description") String description
			,final @RequestParam("image") String imageUrl) {

			Date createDate = new Date();
			log.info("Name: " + name);
			log.info("description: " + description);
			log.info("price: " + price);

			ImageGallery imageGallery = new ImageGallery();
			imageGallery.setName(name);
			imageGallery.setImage(imageUrl);
			imageGallery.setPrice(price);
			imageGallery.setDescription(description);
			imageGallery.setCreateDate(createDate);
			imageGalleryService.saveImage(imageGallery);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return "redirect:/image/show";
	}
	
	@GetMapping("/image/display/{id}")
	public String showImage(@PathVariable("id") Long id, Model model) {
		log.info("Id :: " + id);
		Optional<ImageGallery> imageGallery = imageGalleryService.getImageById(id);
		String url = imageGallery.get().getImage();
		model.addAttribute("image", url);
		System.out.println("url to the link: " + url);
		return "view-image";
	}

	@GetMapping("/image/imageDetails/{id}")
	public String showProductDetails(@PathVariable("id") Long id, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				Optional<ImageGallery> imageGallery = imageGalleryService.getImageById(id);
			
				log.info("products :: " + imageGallery);
				if (imageGallery.isPresent()) {
					model.addAttribute("image", imageGallery.get().getImage());
					model.addAttribute("id", imageGallery.get().getId());
					model.addAttribute("description", imageGallery.get().getDescription());
					model.addAttribute("name", imageGallery.get().getName());
					model.addAttribute("price", imageGallery.get().getPrice());
					return "imagedetails";
				}
				return "redirect:/home";
			}
		return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}	
	}

	@GetMapping("/image/show")
	public String show(Model model) {
		List<ImageGallery> images = imageGalleryService.getAllActiveImages();
		model.addAttribute("images", images);
		return "images";
	}

	@RequestMapping("/delete-image/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		imageGalleryService.deleteImage(id);
		return "redirect:/image/show";
	}
}	

