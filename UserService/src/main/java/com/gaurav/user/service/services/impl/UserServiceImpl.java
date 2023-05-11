package com.gaurav.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gaurav.user.service.entities.Hotel;
import com.gaurav.user.service.entities.Rating;
import com.gaurav.user.service.entities.User;
import com.gaurav.user.service.exceptions.ResourceNotFoundException;
import com.gaurav.user.service.externalservice.HotelService;
import com.gaurav.user.service.repositories.UserRepository;
import com.gaurav.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		// generate unique id in string
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	// get all users
	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	// get single user
	@Override
	public User getUser(String userId) {
		
		// get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
		// fetch rating of the above user from rating service
		// http://localhost:8083/users/fa0eb215-6a92-4131-a9df-9fabcd67e3b2
		Rating[] ratingofUser = restTemplate.getForObject("http://RATING-SERVICE/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingofUser);
		List<Rating> ratings = Arrays.stream(ratingofUser).toList();
		List<Rating> ratinglist = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/46e73877-d61e-4c16-a2b0-ea27b7855b47
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			logger.info("response status code: {}", forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating 
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratinglist);
		
		return user;
	}
	
}
