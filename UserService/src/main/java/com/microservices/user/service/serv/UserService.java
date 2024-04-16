package com.microservices.user.service.serv;

import java.util.List;

import com.microservices.user.service.entities.User;

public interface UserService {

	// Create
	User saveUser(User user);

// get all user
	List<User> getAllUser();

	// Get single user of given id
	User getUser(String userId);

	User updateUser(User user);

	User deleteUser(User userId);

}
