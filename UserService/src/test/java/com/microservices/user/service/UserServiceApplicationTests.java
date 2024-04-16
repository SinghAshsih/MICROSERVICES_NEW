package com.microservices.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.external.service.RatingService;

@Service
@SpringBootTest

class UserServiceApplicationTests {
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("").hotelId("")
				.feedback("this is created using feign client ").build();
		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);
		System.out.println("Hello");
		System.out.println(saveRating.getBody());

	}
}
