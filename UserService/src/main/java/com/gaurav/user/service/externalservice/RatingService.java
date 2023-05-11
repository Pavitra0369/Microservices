package com.gaurav.user.service.externalservice;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.gaurav.user.service.entities.Rating;
@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	// get rating 
	
	// post rating
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	
	
	
	// put rating 
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId ,Rating rating);
	
	// delete rating
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
