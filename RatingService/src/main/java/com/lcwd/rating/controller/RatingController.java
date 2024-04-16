package com.lcwd.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService ratingServices;

	@PostMapping
	public Rating create(@RequestBody Rating rating) {
		return ratingServices.create(rating);
	}

	@GetMapping
	public List<Rating> getRating() {
		return ratingServices.getRating();
	}

	@GetMapping("/user/{userId}")
	public List<Rating> getRatingByUserId(@PathVariable String userId) {
		return ratingServices.getRatingByUserId(userId);
	}

	@GetMapping("/hotels/{hotelId}")
	public List<Rating> getRatingByHotelId(@PathVariable String hotelId) {
		return ratingServices.getRatingByHotelId(hotelId);
	}
}
