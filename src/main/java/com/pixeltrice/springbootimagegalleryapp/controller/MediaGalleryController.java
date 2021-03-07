package com.pixeltrice.springbootimagegalleryapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pixeltrice.springbootimagegalleryapp.model.MediaGallery;
import com.pixeltrice.springbootimagegalleryapp.model.User;
import com.pixeltrice.springbootimagegalleryapp.service.MediaGalleryService;
import com.pixeltrice.springbootimagegalleryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class MediaGalleryController {

	private MediaGalleryService mediaGalleryService;
	private UserService userService;

	@Autowired
	public MediaGalleryController(MediaGalleryService mediaGalleryService, UserService userService) {
		this.mediaGalleryService = mediaGalleryService;
		this.userService = userService;
	}

	@GetMapping( "/media/add")
	public String displayMediaFormAdmin(Model model, HttpSession session){
		Object adminObj = session.getAttribute("admin");
		return displayMediaForm(0, model, adminObj, "");
	}

	@GetMapping( "/media/{userid}/add")
	public String displayMediaFormUser(@PathVariable("userid") long userId, Model model, HttpSession session){

		Object userObj = session.getAttribute("user");
		return displayMediaForm(userId, model, userObj, "user");
	}

	public String displayMediaForm(long userId, Model model, Object obj, String user){
		if (obj == null) return "redirect:/login";

		model.addAttribute("mediaGallery", new MediaGallery());
		if(userId != 0) {
			model.addAttribute("userid", userId);
		}
		return user + "Index";
	}

	@PostMapping("/media/save-media-details")
	public String createMediaAdmin(@ModelAttribute("mediaGallery") MediaGallery mediaGallery, HttpSession session) {
		Object adminObj = session.getAttribute("admin");
		return createMedia(0, mediaGallery, adminObj);
	}

	@PostMapping("/media/{userid}/save-media-details")
	public String createMediaUser(@PathVariable("userid") long userId, @ModelAttribute("mediaGallery") MediaGallery mediaGallery, HttpSession session) {
		Object userObj = session.getAttribute("user");
		return createMedia(userId, mediaGallery, userObj);
	}

	private String createMedia(long userId, MediaGallery mediaGallery, Object obj) {
		if (obj == null) return "redirect:/login";
		Date createDate = new Date();
		mediaGallery.setCreateDate(createDate);
		mediaGalleryService.saveImage(mediaGallery);
		if(userId != 0){
			User user = userService.findUser(userId);
			List<MediaGallery> userMediaList = user.getMediaGalleries();
			userMediaList.add(mediaGallery);
			user.setMediaGalleries(userMediaList);
			userService.createUser(user);
			return "redirect:/media/" + userId;
		}
		return "redirect:/media/";
	}

	@GetMapping("/media/display/{id}")
	public String showImageAdmin(@PathVariable("id") Long id, Model model, HttpSession session) {
		return showImage(0L, id, model, session, "");
	}


	@GetMapping("/media/{userId}/display/{id}")
	public String showImageUser(@PathVariable("userId") Long userId, @PathVariable("id") Long id, Model model, HttpSession session) {
		return showImage(userId, id, model, session, "User");
	}

	private String showImage(long userId, Long id, Model model, HttpSession session, String str) {
		Object userObj = session.getAttribute("user");
		Object adminObj = session.getAttribute("admin");
		if (userObj == null && adminObj == null) return "redirect:/login";
		Optional<MediaGallery> imageGallery = mediaGalleryService.getMediaById(id);
		String url = imageGallery.get().getMedia();
		model.addAttribute("media", url);
		if(userId != 0){
			model.addAttribute("id", userId);
		}
		return "view" + str + "Media";
	}

	@GetMapping("/media/{userId}/media-details/{id}")
	public String showMediaDetailsUser(@PathVariable("userId") Long userId, @PathVariable("id") Long id, Model model, HttpSession session) {

		Object userObj = session.getAttribute("user");
		return showMediaDetails(userId, id, model, userObj, "user");
	}

	@GetMapping("/media/media-details/{id}")
	public String showMediaDetailsAdmin(@PathVariable("id") Long id, Model model, HttpSession session) {

		Object adminObj = session.getAttribute("admin");
		return showMediaDetails(0L, id, model, adminObj, "");
	}


	public String showMediaDetails(Long userId, Long id, Model model, Object obj, String str) {
		if (obj == null) return "redirect:/login";
		try {
			if (id != 0) {
				MediaGallery mediaGallery = mediaGalleryService.getMediaById(id).orElse(null);

				assert mediaGallery != null;
				model.addAttribute("mediaGallery", mediaGallery);
				if(userId != 0){
					model.addAttribute("userid", userId);
				}
				return str + "MediaDetails";
			}
			return "redirect:/media/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/media/add";
		}
	}



	@GetMapping("/media")
	public String displayMediaAdmin(Model model, HttpSession session) {
		return displayMedia(0L, model, session, "");
	}

	@GetMapping("/media/{userid}")
	public String displayMediaUser(@PathVariable("userid") Long userId, Model model, HttpSession session) {
		return displayMedia(userId, model, session, "user");
	}

	private String displayMedia(long userId, Model model, HttpSession session, String str) {
		Object userObj = session.getAttribute("user");
		Object adminObj = session.getAttribute("admin");
		if (userObj == null && adminObj == null) return "redirect:/login";
		User user = userService.findUser(userId);
		if(user != null){
			model.addAttribute("userId", userId);
		}
		List<MediaGallery> media = userId != 0 ?  userService.getUserMedia(user) :mediaGalleryService.getAllActiveMedia();
		model.addAttribute("media", media);
		return str + "Media";
	}

	@RequestMapping("/delete-media/{id}")
	public String deleteMediaAdmin(@PathVariable(value = "id") long id, HttpSession session) {
		return deleteMedia(0L, id, session, "admin");
	}

	@RequestMapping("/delete-media/{userId}/{id}")
	public String deleterMediaUser(@PathVariable(value = "userId") long userId, @PathVariable(value = "id") long id, HttpSession session) {
		return deleteMedia(userId, id, session, "user");
	}

	private String deleteMedia(long userId, long id, HttpSession session, String str) {
		Object obj = session.getAttribute(str);
		if (obj == null) return "redirect:/login";

		mediaGalleryService.deleteMedia(id);
		return userId == 0 ?  "redirect:/media/" : "redirect:/media/" + userId;
	}
}