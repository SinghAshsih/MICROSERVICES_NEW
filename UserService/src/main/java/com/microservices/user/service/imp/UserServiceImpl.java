package com.microservices.user.service.imp;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.service.entities.Hotel;
import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exception.ResourceNotFoundException;
import com.microservices.user.service.external.service.HotelService;
import com.microservices.user.service.repository.UserRepository;
import com.microservices.user.service.serv.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;

	// private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();

	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !! " + userId));

		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);

		// logger.info("{} ", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// logger.info("response status code: {} ", forEntity.getStatusCode());

			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());
		user.setRatings(ratingList);

		return user;

	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
