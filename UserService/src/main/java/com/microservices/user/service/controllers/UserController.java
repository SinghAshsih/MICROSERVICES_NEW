package com.microservices.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.service.entities.User;
import com.microservices.user.service.serv.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);

	}

	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		logger.info("Get single user handler :Usercontroller");
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);

	}

	public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
		logger.info("Fallback is executed because service is down : " + e.getMessage());
		User user = User.builder().email("dummy@gmail.com").name("Dummy")
				.about("This user is created because some service is down ").UserId("141234").build();
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = userService.getAllUser();
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable User userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("Successful");
	}

}
