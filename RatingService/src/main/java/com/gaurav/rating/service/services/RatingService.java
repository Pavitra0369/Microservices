package com.gaurav.rating.service.services;

import java.util.List;

import com.gaurav.rating.service.entities.Rating;

public interface RatingService {
	// create
	Rating create(Rating rating);
	
	// get all ratings
	List<Rating> getRatings();
	
	// get rating by user id
	List<Rating> getRatingByUserId(String userId);
	
	// get rating by hotel id 
	List<Rating> getRatingByHotelId(String hotelId);
}
