package com.gaurav.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gaurav.user.service.entities.Rating;
import com.gaurav.user.service.externalservice.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
	@Test
	void createRating() {
		Rating  rating = new Rating();
		rating.setRating(10);
		rating.setUserId("");
		rating.setHotelId("");
		rating.setFeedback("this is created using feign client....!!");
		Rating saveRating = ratingService.createRating(rating);
		System.out.println("new rating created");
	}

}
